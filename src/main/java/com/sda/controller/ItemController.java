package com.sda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sda.model.Item;
import com.sda.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	ItemService itemService;

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
