package com.sda.controller;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sda.model.User;
import com.sda.service.UserService;

@Controller
public class UserController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	UserService userService;

	@Autowired
	UserDetailsService customUserDetailsService;

//	@Autowired
//	private JavaMailSender mailSender;

    @Autowired
    private Environment environment;

    @Autowired
	PrincipalUtil util;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		List<User> users = userService.listAll();
		model.addAttribute("users", users);
		model.addAttribute("loggedUser", util.getPrincipal());
		return "allusers";
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public String addUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("loggedUser", util.getPrincipal());
		return "adduser";
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "adduser";
		}
		if (!userService.isUserUnique(user.getLogin())) {
			FieldError loginError = new FieldError("user", "login", messageSource.getMessage("non.unique.login",
					new String[] { user.getLogin() }, Locale.getDefault()));
			result.addError(loginError);
			return "adduser";
		}
		userService.save(user);
		redirectAttributes.addFlashAttribute("message", "User " + user.getLogin() + " added successfully");
		return "redirect:/users";
	}

	@RequestMapping(value = "/edit-{id}-user", method = RequestMethod.GET)
	public String editUser(@PathVariable String id, ModelMap model) {
		User user = userService.findById(Integer.valueOf(id));
		model.addAttribute("user", user);
		model.addAttribute("loggedUser", util.getPrincipal());
		return "edituser";
	}

	@RequestMapping(value = "/edit-{id}-user", method = RequestMethod.POST)
	public String updateUser(@PathVariable String id, @Valid User user, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "edituser";
		}
		User entity = userService.findById(Integer.valueOf(id));
		if (!userService.isUserUnique(user.getLogin()) && !user.getLogin().equals(entity.getLogin())) {
			FieldError loginError = new FieldError("user", "login", messageSource.getMessage("non.unique.login",
					new String[] { user.getLogin() }, Locale.getDefault()));
			result.addError(loginError);
			return "edituser";
		}
		userService.update(user);
		redirectAttributes.addFlashAttribute("message", "User " + user.getLogin() + " updated successfully");
		return "redirect:/users";
	}

	// TODO change/reset password

	@RequestMapping(value = "/delete-{id}-user", method = RequestMethod.GET)
	public String deleteUser(@PathVariable String id, RedirectAttributes redirectAttributes) {
		try {
			userService.deleteById(Integer.valueOf(id));
		} catch (DataIntegrityViolationException e) {
			redirectAttributes.addFlashAttribute("error", "Could not delete user!");
		}
		return "redirect:/users";
	}

	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPassword() {
		// model.addAttribute("user", new User());
		return "resetPassword";
	}

	@RequestMapping(value = "/resetPass", method = RequestMethod.GET)
	public String generateResetMessage(final HttpServletRequest request, @RequestParam("email") final String email, RedirectAttributes redirectAttributes) {
		final Optional<User> maybeUser = userService.findByEmail(email);
		if (maybeUser.isPresent()) {
			final User user = maybeUser.get();
			final String token = UUID.randomUUID().toString();
			userService.createPasswordResetTokenForUser(user, token);
//	        mailSender.send(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));
	        SimpleMailMessage message = constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user);
	        System.out.println("Message" + message.toString());
	        redirectAttributes.addFlashAttribute("mailSent", messageSource.getMessage("resetPassword", null, Locale.getDefault()));
		} else {
			redirectAttributes.addFlashAttribute("noSuchEmail", messageSource.getMessage("noSuchEmail", null, Locale.getDefault()));
		}
		return "redirect:/login";
	}

	
	
	private SimpleMailMessage constructResetTokenEmail(final String contextPath, final Locale locale, final String token, final User user) {
        final String url = contextPath + "/changePassword?id=" + user.getId() + "&token=" + token;
        final String message = messageSource.getMessage("resetPassword", null, Locale.getDefault());
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, User user) {
        final SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(environment.getProperty("support.email"));
        return email;
    }

    private String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
