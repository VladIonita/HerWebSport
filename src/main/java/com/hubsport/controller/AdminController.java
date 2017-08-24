package com.hubsport.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hubsport.domain.Users;
import com.hubsport.service.CurrentTimeFormated;
import com.hubsport.service.MailService;
import com.hubsport.service.UserService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = "/admin")
@SessionAttributes(value = { "loggedinuser", "dateweek" })
public class AdminController {

	@Autowired
	CurrentTimeFormated timeService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private MailService emailService;
	
	// handling 404 error
	@RequestMapping(value = "*", method = { RequestMethod.GET, RequestMethod.POST })
	public String fallback() {
		return "fallback";
	}
	
	@RequestMapping("/")
	public String defaultPage(Model model) {
		model.addAttribute("partial", "bash");
		model.addAttribute("pageTitle", "Admin");
		model.addAttribute("loggedinuser", getPrincipal());
		return "index";
	}

	// return login
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String loginPage(Model model) {
		model.addAttribute("pageTitle", "Dashbord");
		return "loginPage";
	}

	@ModelAttribute("dateweek")
	private String getTime() {
		String time = timeService.time();
		return time;
	}

	// returning current user
	@ModelAttribute("loggedinuser")
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

	@RequestMapping(value = "/recover", method = RequestMethod.GET)
	public String forgotPassword(Model model) {
		Users users = new Users();
		model.addAttribute("userForm", users);
		return "recover";
	}

	// Process reset password form
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String processForgotPasswordForm(Model model, HttpServletRequest request,
			@RequestParam("email") String userEmail) throws NotFoundException {
		Users users = userService.findByEmail(userEmail);

		if (users == null) {
			throw new NotFoundException("We didn't find an account for that e-mail address.");
		} else {
			String token = UUID.randomUUID().toString();

			userService.saveToken(token, users);

			String appUrl = request.getScheme() + "://" + request.getServerName() + ":8080/HerWebSport";

			SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
			passwordResetEmail.setFrom("support@demo.com");
			passwordResetEmail.setTo(users.getEmail());
			passwordResetEmail.setSubject("Password Reset Request");
			passwordResetEmail
					.setText("To reset your password, click the link below:\n" + appUrl + "/reset?token=" + token);

			emailService.sendEmail(passwordResetEmail);

			model.addAttribute("successMessage", "A password reset link has been sent to " + userEmail);

		}
		return "recover";
	}

}