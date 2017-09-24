package com.hubsport.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hubsport.domain.Users;
import com.hubsport.service.CurrentTimeFormatedService;
import com.hubsport.service.MailService;
import com.hubsport.service.UserService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = "/admin")
@SessionAttributes(value = { "loggedInUser", "dateWeek" })
public class AdminController {

	@Autowired
	CurrentTimeFormatedService timeService;

	@Autowired
	UserService userService;

	@Autowired
	private MailService emailService;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/*", method = { RequestMethod.GET, RequestMethod.POST })
	public String handleHttpError() {
		return "errorHandler";
	}

	@RequestMapping("/")
	public String homePage(Model model) {
		model.addAttribute("partial", "welcomeAdmin");
		model.addAttribute("pageTitle", "Admin");
		model.addAttribute("loggedInUser", getPrincipal());
		return "index";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginPage(Model model) {
		model.addAttribute("pageTitle", "Dashbord");
		return "loginPage";
	}

	@RequestMapping(value = "/password/recover", method = RequestMethod.GET)
	public String forgotPassword(Model model) {
		model.addAttribute("userForm", new Users());
		return "recover";
	}

	@RequestMapping(value = "/password/recover", method = RequestMethod.POST)
	public String processForgotPasswordForm(Model model, HttpServletRequest request,
			@RequestParam("email") String userEmail, final RedirectAttributes redirectAttributes)
			throws NotFoundException {
		Users user = userService.findUserByEmail(userEmail);
		if (user == null) {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Check your inbox for the next steps."
					+ "If you don't receive an email, and it's not in your spam folder this could mean you signed up with a different address.");

		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Check your inbox for the next steps."
					+ "If you don't receive an email, and it's not in your spam folder this could mean you signed up with a different address.");
			createTokenAndSendEmail(user, request);
		}
		return "redirect:/admin/password/recover";
	}

	private void createTokenAndSendEmail(Users user, HttpServletRequest request) {
		String token = UUID.randomUUID().toString();
		userService.saveToken(token, user);

		String appUrl = request.getScheme() + "://" + request.getServerName();

		SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
		passwordResetEmail.setFrom("support@demo.com");
		passwordResetEmail.setTo(user.getEmail());
		passwordResetEmail.setSubject("Password Reset Request");
		passwordResetEmail
				.setText("To reset your password, click the link below:\n" + appUrl + "/admin/password/reset/" + token);
		emailService.sendEmail(passwordResetEmail);
	}

	@RequestMapping(value = "/password/reset/{token}", method = RequestMethod.GET)
	public String validatePasswordResetToken(@PathVariable("token") String token, Model model,
			final RedirectAttributes redirectAttributes) {
		if (userService.validatePasswordResetToken(token)) {
			Users users = userService.findUserByResetToken(token);
			model.addAttribute("userPasswordForm", users);
			return "resetPassword";
		} else {
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Email has expired. Please try again!");
			return "redirect:/admin/password/recover";
		}
	}

	@RequestMapping(value = "/password/reset/{token}", method = RequestMethod.POST)
	public String saveOrUpdateUserPassword(@PathVariable("token") String token,
			@ModelAttribute("userPasswordForm") Users users, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		model.addAttribute("token", token);
		Users usersToken = userService.findUserByResetToken(token);
		userService.updatePassword(usersToken.getId(), users.getPassword());
		return "passUpdateSuccess";
	}

	@ModelAttribute("loggedInUser")
	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	@ModelAttribute("dateWeek")
	private String getTime() {
		String time = timeService.time();
		return time;
	}

}
