package com.mcyp.tfm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcyp.tfm.domain.exceptions.MapperException;
import com.mcyp.tfm.domain.service.DeviceService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DeviceController {

	@Autowired
	private DeviceService deviceService;
	
	@GetMapping("/devices")
    public ModelAndView displayArticle(Map<String, Object> model) {
        try {
			model.put("devices", deviceService.getDevices());
		} catch (MapperException e) {
			log.error(e.getMessage());
		}

        return new ModelAndView("devices", model);
    }

}
