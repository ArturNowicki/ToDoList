package com.sda.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sda.enums.ItemType;
import com.sda.enums.State;
import com.sda.model.Item;
import com.sda.service.ItemService;
import com.sda.service.UserService;

@Controller
public class ItemController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	ItemService itemService;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = { "/item-{id}" }, method = RequestMethod.GET)
	public String itemDetails(@PathVariable String id, ModelMap model) {
		Item item = itemService.findById(Integer.valueOf(id));
		model.addAttribute("item", item);
		return "item";
	}
	
	@RequestMapping(value = {"/edit-{id}-item"}, method = RequestMethod.GET)
	public String editItem(@PathVariable String id, ModelMap model) {
		Item item = itemService.findById(Integer.valueOf(id));
		System.out.println("item: " + item);
		model.addAttribute("item", item);
		model.addAttribute("user", item.getAssignedUser());
//		model.addAttribute("types", ItemType.values());
//		model.addAttribute("states", State.values());
//		model.addAttribute("users", userService.listAll());
		return "additem";
	}

	@RequestMapping(value = { "/edit-{id}-item" }, method = RequestMethod.POST)
	public String updateItem(@PathVariable String id, @Valid Item item, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "additem";
		}
		itemService.update(item);
		redirectAttributes.addFlashAttribute("message", "Item " + item.getId() + ": " + item.getTitle() + " updated successfully");
		return "redirect:/dashboard";
	}

}

