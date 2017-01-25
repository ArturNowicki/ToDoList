package com.sda.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sda.persistence.model.Item;
import com.sda.service.ItemService;

@Controller
public class AppController {

	@Autowired
	ItemService itemService;

	@Autowired
	PrincipalUtil util;	
	// DASHBOARD

	@RequestMapping(value = {"/", "/dashboard"}, method = RequestMethod.GET)
	public String listDashboard(ModelMap model) {
		List<Item> items = itemService.listAll();
		model.addAttribute("item", items);
		model.addAttribute("loggedUser", util.getPrincipal());
		return "dashboard";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String displayLoginPage(ModelMap model) {
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "/access_denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedUser", util.getPrincipal());
		return "accessDenied";
	}

}