package com.sda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sda.model.Item;
import com.sda.service.ItemService;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	ItemService itemService;

	// DASHBOARD

	@RequestMapping(value = { "/", "/dashboard" }, method = RequestMethod.GET)
	public String listDashboard(ModelMap model) {
		List<Item> items = itemService.listAll();
		model.addAttribute("item", items);
		return "dashboard";
	}
	
	@RequestMapping(value = {"/login" }, method = RequestMethod.GET)
	public String displayLoginPage(ModelMap model) {
		return "login";
	}
}