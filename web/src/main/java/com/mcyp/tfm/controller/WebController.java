package com.mcyp.tfm.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

	@GetMapping("/home")
    public ModelAndView index(Map<String, Object> model) {        
        return new ModelAndView("home", model);
    }
	
}
