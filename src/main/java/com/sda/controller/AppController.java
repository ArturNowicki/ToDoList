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

import com.sda.model.Item;
import com.sda.model.User;
import com.sda.service.ItemService;
import com.sda.service.UserService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	UserService userService;

	@Autowired
	ItemService itemService;

	// DASHBOARD

	@RequestMapping(value = { "/", "/dashboard" }, method = RequestMethod.GET)
	public String listDashboard(ModelMap model) {
		List<Item> items = itemService.listAll();
		model.addAttribute("item", items);
		return "dashboard";
	}

	// USER

	@RequestMapping(value = { "/userslist" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {
		List<User> users = userService.listAll();
		model.addAttribute("user", users);
		return "allusers";
	}

	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String addUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		return "adduser";
	}

	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {
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
		model.addAttribute("success", "User " + user.getLogin() + " added successfully");
//		return "usersuccess";
// 		TODO previous version showed success page. now i want to replace it with popup
		return "redirect:/userslist";
	}

	@RequestMapping(value = { "/edit-{id}-user" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String id, ModelMap model) {
		User user = userService.findById(Integer.valueOf(id));
		model.addAttribute("user", user);
		return "adduser";
	}

	@RequestMapping(value = { "/edit-{id}-user" }, method = RequestMethod.POST)
	public String updateUser(@PathVariable String id, @Valid User user, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "adduser";
		}
		User entity = userService.findById(Integer.valueOf(id));
		if (!userService.isUserUnique(user.getLogin()) && !user.getLogin().equals(entity.getLogin())) {
			FieldError loginError = new FieldError("user", "login", messageSource.getMessage("non.unique.login",
					new String[] { user.getLogin() }, Locale.getDefault()));
			result.addError(loginError);
			return "adduser";
		}
		userService.update(user);
		model.addAttribute("success", "User " + user.getLogin() + " updated successfully");
//		return "usersuccess";
// 		TODO previous version showed success page. now i want to replace it with popup
		return "redirect:/userslist";
	}

	@RequestMapping(value = { "/delete-{id}-user" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String id) {
		try {
			userService.deleteById(Integer.valueOf(id));
		} catch ( DataIntegrityViolationException e) {
//			TODO implement some warning about constraints!
		}
		return "redirect:/userslist";
	}

	// ITEM

	@RequestMapping(value = { "/itemslist" }, method = RequestMethod.GET)
	public String listItems(ModelMap model) {
		List<Item> items = itemService.listAll();
		model.addAttribute("item", items);
		return "allitems";
	}

	@RequestMapping(value = { "/item-{id}" }, method = RequestMethod.GET)
	public String itemDetails(@PathVariable String id, ModelMap model) {
		Item item = itemService.findById(Integer.valueOf(id));
		System.out.println(id);
		System.out.println("item info: " + item);
		model.addAttribute("item", item);
		return "item";
	}
}