package com.mcyp.tfm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcyp.tfm.domain.exceptions.MapperException;
import com.mcyp.tfm.domain.service.ScanMapper;

@Controller
public class WebController {

	@Autowired
	private ScanMapper mapper;
	
	@GetMapping("/home")
    public ModelAndView index(Map<String, Object> model) throws MapperException {     
		model.put("hosts", this.mapper.map("result_.json"));
        return new ModelAndView("home", model);
    }
	
}
