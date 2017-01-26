package com.sda.controller;

import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sda.dto.PasswordDto;
import com.sda.persistence.model.User;
import com.sda.service.UserSecurityService;
import com.sda.service.UserService;
import com.sda.utilities.PrincipalUtil;

@Controller
public class RegistrationController {

	@Autowired
	UserService userService;

	@Autowired
	UserSecurityService userSecurityService;

	@Autowired
	PrincipalUtil util;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	MessageSource messageSource;

	@RequestMapping(value = "/sendResetToken", method = RequestMethod.GET)
	public String resendPassword() {
		return "sendResetToken";
	}

	@RequestMapping(value = "/sendResetToken", method = RequestMethod.POST)
	public String generateResetMessage(final HttpServletRequest request, @RequestParam("email") final String email,
			RedirectAttributes redirectAttributes) {
		final Optional<User> maybeUser = userService.findByEmail(email);
		if (maybeUser.isPresent()) {
			final User user = maybeUser.get();
			final String token = UUID.randomUUID().toString();
			userService.createPasswordResetTokenForUser(user, token);
			SimpleMailMessage message = constructResetTokenEmail(getAppUrl(request), Locale.getDefault(), token, user);
			// mailSender.send(message);
			System.out.println("TOKEN: " + message);
			redirectAttributes.addFlashAttribute("mailSent",
					messageSource.getMessage("resetPassword", null, Locale.getDefault()));
		} else {
			redirectAttributes.addFlashAttribute("noSuchEmail",
					messageSource.getMessage("noSuchEmail", null, Locale.getDefault()));
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String changePassword(@RequestParam("id") final int userId, @RequestParam("token") final String token,
			ModelMap model) {
		final String result = userSecurityService.validatePasswordResetToken(userId, token);
		if (null != result) {
			model.addAttribute("tokenmsg", result);
			return "redirect:/login";
		}
		model.addAttribute("passDto", new PasswordDto());
		return "updatePassword";
	}

	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public String savePassword(@Valid PasswordDto passwordDto, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("passDto", passwordDto);
			return "updatePassword";
		}
		if (!userService.isPasswordMatching(passwordDto.getPassword(), passwordDto.getConfirmPassword())) {
			FieldError passwordError = new FieldError("passwordDto", "password", messageSource
					.getMessage("passwordsDontMatch", new String[] { passwordDto.getPassword() }, Locale.getDefault()));
			result.addError(passwordError);
			model.addAttribute("passDto", passwordDto);
			return "updatePassword";
		}
		final User user = (User) util.getPrincipal();
		userService.changePassword(user, passwordDto.getPassword());
		return "login";
	}

	private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale,
			final String token, final User user) {
		final String url = contextPath + "/changePassword?id=" + user.getId() + "&token=" + token;
		final String message = messageSource.getMessage("resetPassword", null, Locale.getDefault());
		return constructEmail("Reset Password", message + " \r\n" + url, user);
	}

	private SimpleMailMessage constructEmail(String subject, String body, User user) {
		final SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(body);
		email.setTo(user.getEmail());
		email.setFrom("support@todolist.com");
		return email;
	}

	private String getAppUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

}
