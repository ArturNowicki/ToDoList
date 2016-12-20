package com.sda.controller;

import java.util.List;

import com.sda.model.Item;
import com.sda.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sda.model.User;
import com.sda.service.UserService;

@Controller
@RequestMapping("/")
public class AppController {

    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

    @RequestMapping(value = {"/list"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        List<User> users = userService.findAllUsers();
        model.addAttribute("user", users);
        return "allusers";
    }

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String listDashboard(ModelMap model) {
        List<Item> items = itemService.listItems();
        model.addAttribute("item", items);
        return "dashboard";
    }
}
