package com.mcyp.tfm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AnalysisController {

	@GetMapping("/analysis")
    public ModelAndView displayArticle(Map<String, Object> model) {
        
		model.put("devices", List.of());
		
        return new ModelAndView("devices", model);
    }
	
}
