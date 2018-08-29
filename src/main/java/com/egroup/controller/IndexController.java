package com.egroup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "text/html; charset=UTF-8" })
    public @ModelAttribute ModelAndView Index() {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
}
