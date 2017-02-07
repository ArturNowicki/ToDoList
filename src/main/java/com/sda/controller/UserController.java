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

import com.sda.dto.EditUserDto;
import com.sda.dto.NewUserDto;
import com.sda.persistence.model.User;
import com.sda.service.UserService;
import com.sda.utilities.PrincipalUtil;

@Controller
public class UserController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	UserService userService;

	@Autowired
	PrincipalUtil util;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		List<User> users = userService.listAll();
		model.addAttribute("users", users);
		model.addAttribute("loggedUser", util.getPrincipalName());
		return "allusers";
	}

	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public String addUser(ModelMap model) {
		model.addAttribute("newUserDto", new NewUserDto());
		model.addAttribute("loggedUser", util.getPrincipalName());
		return "adduser";
	}
	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String saveUser(@Valid NewUserDto newUserDto, BindingResult result, RedirectAttributes redirectAttributes, ModelMap model) {
		if (!userService.isUserUnique(newUserDto.getLogin())) {
			FieldError loginError = new FieldError("newUserDto", "login", messageSource.getMessage("non.unique.login",
					new String[] { newUserDto.getLogin() }, Locale.getDefault()));
			result.addError(loginError);
		}
		if (!userService.isPasswordMatching(newUserDto.getPassword(), newUserDto.getConfirmPassword())) {
			FieldError passwordError = new FieldError("newUserDto", "password", messageSource
					.getMessage("passwords.not.match", null, Locale.getDefault()));
			result.addError(passwordError);
		}
		if (result.hasErrors()) {
			model.addAttribute("loggedUser", util.getPrincipalName());
			return "adduser";
		}
		userService.save(newUserDtoToUser(newUserDto));
		redirectAttributes.addFlashAttribute("message", "User " + newUserDto.getLogin() + " added successfully");
		return "redirect:/users";
	}

	@RequestMapping(value = "/edit-{id}-user", method = RequestMethod.GET)
	public String editUser(@PathVariable int id, ModelMap model) {
		model.addAttribute("editUserDto", userService.getAsEditUserDto(id));
		model.addAttribute("loggedUser", util.getPrincipalName());
		return "edituser";
	}

	@RequestMapping(value = "/edit-{id}-user", method = RequestMethod.POST)
	public String updateUser(@PathVariable int id, @Valid EditUserDto editUserDto, BindingResult result,
			RedirectAttributes redirectAttributes, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("editUserDto", editUserDto);
			model.addAttribute("loggedUser", util.getPrincipalName());
			return "edituser";
		}
		userService.update(editUserDtoToUser(editUserDto));
		redirectAttributes.addFlashAttribute("message", "User " + editUserDto.getLogin() + " updated successfully");
		return "redirect:/users";
	}

	@RequestMapping(value = "/delete-{id}-user", method = RequestMethod.GET)
	public String deleteUser(@PathVariable int id, RedirectAttributes redirectAttributes) {
		try {		userService.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			redirectAttributes.addFlashAttribute("error", "Could not delete user!");
		}
		return "redirect:/users";
	}

	
	private User editUserDtoToUser(EditUserDto editUserDto) {
		return new User(editUserDto.getId(), editUserDto.getLogin(), editUserDto.getEmail(), editUserDto.getUserType());
	}
	private User newUserDtoToUser(NewUserDto newUserDto) {
		User user = new User(newUserDto.getLogin());
		user.setEmail(newUserDto.getEmail());
		user.setPassword(newUserDto.getPassword());
		user.setUserType(newUserDto.getUserType());
		return user;
	}
}
