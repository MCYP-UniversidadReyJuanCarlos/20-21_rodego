package com.mcyp.tfm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcyp.tfm.domain.service.FirmwareService;

@Controller
public class FirmwareController {

	@Autowired
	private FirmwareService firmwareService;
	
	@GetMapping("/firmware")
    public ModelAndView displayArticle(Map<String, Object> model) {
        model.put("lines", firmwareService.getFileLine());
        
        return new ModelAndView("firmware", model);
    }
}
