package com.sda.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sda.dto.UserDto;
import com.sda.persistence.model.User;
import com.sda.service.UserService;

@Controller
public class UserController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	UserService userService;

//	@Autowired
//	private JavaMailSender mailSender;

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
	public String editUser(@PathVariable int id, ModelMap model) {
		UserDto userDto = userService.getAsDto(id);
		model.addAttribute("userDto", userDto);
		model.addAttribute("loggedUser", util.getPrincipal());
		return "edituser";
	}

	@RequestMapping(value = "/edit-{id}-user", method = RequestMethod.POST)
	public String updateUser(@PathVariable int id, @Valid UserDto userDto, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "edituser";
		}
		User entity = userService.findById(id);
		if (!userService.isUserUnique(userDto.getLogin()) && !userDto.getLogin().equals(entity.getLogin())) {
			FieldError loginError = new FieldError("user", "login", messageSource.getMessage("non.unique.login",
					new String[] { userDto.getLogin() }, Locale.getDefault()));
			result.addError(loginError);
			return "edituser";
		}
		
		userService.update(userDto);
		redirectAttributes.addFlashAttribute("message", "User " + userDto.getLogin() + " updated successfully");
		return "redirect:/users";
	}

	// TODO change/reset password

	@RequestMapping(value = "/delete-{id}-user", method = RequestMethod.GET)
	public String deleteUser(@PathVariable int id, RedirectAttributes redirectAttributes) {
		try {
			userService.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			redirectAttributes.addFlashAttribute("error", "Could not delete user!");
		}
		return "redirect:/users";
	}

}
