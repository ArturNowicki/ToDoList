package com.sda.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sda.persistence.model.Item;
import com.sda.persistence.model.User;
import com.sda.service.ItemService;
import com.sda.service.TagsService;
import com.sda.service.UserService;
import com.sda.utilities.PrincipalUtil;

@Controller
public class ItemController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@Autowired
	private TagsService tagsService;

	@Autowired
	private PrincipalUtil util;

	@RequestMapping(value = "/item-{id}", method = RequestMethod.GET)
	public String itemDetails(@PathVariable final int id, ModelMap model) {
		Item item = itemService.findById(id);
		model.addAttribute("item", item);
		model.addAttribute("loggedUser", util.getPrincipalName());
		return "item";
	}

	@RequestMapping(value = "/newitem", method = RequestMethod.GET)
	public String addItem(ModelMap model) {
		Item item = new Item();
		model.addAttribute("item", item);
		model.addAttribute("users", userService.listAll());
		model.addAttribute("loggedUser", util.getPrincipalName());
		return "additem";
	}

	@RequestMapping(value = "/newitem", method = RequestMethod.POST)
	public String saveItem(@Valid Item item, BindingResult result, RedirectAttributes redirectAttributes,
			ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("users", userService.listAll());
			model.addAttribute("tags", tagsService.listAll());
			return "additem";
		}
		Optional<User> user = userService.findByLogin(item.getAssignedUser().getLogin());
		item.setAssignedUser(user.get());
		user = userService.findByLogin(util.getPrincipalName());
		item.setCreatedBy(user.get());
		itemService.save(item);
		redirectAttributes.addFlashAttribute("message", "Item " + item.getTitle() + " added successfully");
		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/edit-{id}-item", method = RequestMethod.GET)
	public String editItem(@PathVariable final int id, ModelMap model) {
		Item item = itemService.findById(id);
		model.addAttribute("item", item);
		model.addAttribute("users", userService.listAll());
		model.addAttribute("loggedUser", util.getPrincipalName());
		return "edititem";
	}

	@RequestMapping(value = "/edit-{id}-item", method = RequestMethod.POST)
	public String updateItem(@Valid Item item, BindingResult result,
			RedirectAttributes redirectAttributes, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("users", userService.listAll());
			model.addAttribute("tags", tagsService.listAll());
			return "edititem";
		}
		Optional<User> user = userService.findByLogin(item.getAssignedUser().getLogin());
		item.setAssignedUser(user.get());
		itemService.update(item);
		redirectAttributes.addFlashAttribute("message", "Item " + item.getTitle() + " updated successfully");
		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/delete-{id}-item", method = RequestMethod.GET)
	public String deleteItem(@PathVariable final int id, RedirectAttributes redirectAttributes) {
		try {
			itemService.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			redirectAttributes.addFlashAttribute("error", "Could not delete item!");
		}
		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/item-{id}-forward", method = RequestMethod.GET)
	public String advanceItemState(@PathVariable final int id, RedirectAttributes redirectAttributes) {
		itemService.itemStateForward(id);
		return "redirect:/dashboard";
	}

	@RequestMapping(value = "/item-{id}-backward", method = RequestMethod.GET)
	public String revertItemState(@PathVariable final int id, RedirectAttributes redirectAttributes) {
		itemService.itemStateBack(id);
		return "redirect:/dashboard";
	}

}
