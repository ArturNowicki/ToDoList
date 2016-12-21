package com.sda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sda.model.User;
import com.sda.service.UserService;
import com.sda.model.Item;
import com.sda.service.ItemService;

@Controller
@RequestMapping("/")
public class AppController {


    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

    @RequestMapping(value = {"/userslist"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        List<User> users = userService.listAll();
        model.addAttribute("user", users);
        return "allusers";
    }

    @RequestMapping(value = {"/itemslist"}, method = RequestMethod.GET)
    public String listItems(ModelMap model) {
        List<Item> items = itemService.listAll();
        model.addAttribute("item", items);
        return "allitems";
    }

    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public String listDashboard(ModelMap model) {
        List<Item> items = itemService.listAll();
        model.addAttribute("item", items);
        return "dashboard";
    }
}