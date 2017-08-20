package com.hubsport.controller;

import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hubsport.dao.PasswordTokenDao;
import com.hubsport.domain.PasswordResetToken;
import com.hubsport.domain.Users;
import com.hubsport.service.GenericResponse;
import com.hubsport.service.MailService;
import com.hubsport.service.UserService;

import javassist.NotFoundException;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	@Autowired
	UserService userService;
	
	@Autowired
	private PasswordTokenDao passwordTokenDao;

	@Autowired
	MessageSource messageSource;

	@Autowired
	private MailService emailService;

	@RequestMapping(value = { "/login", "/admin" }, method = RequestMethod.GET)
	public String loginPage() {
		return "loginPage";
	}

	// handling 404 error
	@RequestMapping(value = "*", method = { RequestMethod.GET, RequestMethod.POST })
	public String fallback() {
		return "fallback";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String forgotPassword(Model model) {
		Users users = new Users();
		model.addAttribute("userForm", users);
		return "forgotPassword";
	}

	// Process reset password form
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
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
			passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl + "/reset?token="
					+ token);

			emailService.sendEmail(passwordResetEmail);

			model.addAttribute("successMessage", "A password reset link has been sent to " + userEmail);

		}
		return "checkMail";
	}
	
	
	// Display form to reset password
		@RequestMapping(value = "/reset", method = RequestMethod.GET)
		public String displayResetPasswordPage(Model model, @RequestParam("token") String token) {
			
			Users users = userService.findUserByResetToken(token);

			if (users != null) { // Token found in DB
				model.addAttribute("resetToken", token);
			} else { // Token not found in DB
				model.addAttribute("errorMessage", "Oops!  This is an invalid password reset link.");
			}

			return "newPassword";
		}
}
