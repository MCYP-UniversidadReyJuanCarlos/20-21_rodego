package com.mcyp.tfm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcyp.tfm.domain.exceptions.MapperException;
import com.mcyp.tfm.domain.service.ServiceService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ServiceController {

	@Autowired
	private ServiceService serviceService;
	
	@GetMapping("/service")
    public ModelAndView displayArticle(Map<String, Object> model) {
        try {
			model.put("services", serviceService.getServices());
		} catch (MapperException e) {
			 log.error(e.getMessage());
		}

        return new ModelAndView("services", model);
    }

}
