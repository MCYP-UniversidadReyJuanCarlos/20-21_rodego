package com.mcyp.tfm.controller;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.mcyp.tfm.domain.exceptions.HostNotFoundException;
import com.mcyp.tfm.domain.exceptions.MapperException;
import com.mcyp.tfm.domain.service.AnalysisService;

@Controller
public class WebController {

	@Autowired
	private AnalysisService analysisService;
	
	@GetMapping("/home")
	public ModelAndView home(Map<String, Object> model) throws MapperException {
		
		model.put(
			"hosts",
			this.analysisService.getHosts().stream()
				.filter(host -> !"down".equals(host.getStatus().getState()))
				.collect(Collectors.toList())
		);
		return new ModelAndView("home", model);
	}

	@GetMapping("/host/{ip}")
	public ModelAndView detail(Map<String, Object> model, @PathVariable String ip) throws MapperException, HostNotFoundException {
		
		model.put(
			"host",
			this.analysisService.getHost(ip)
		);
		return new ModelAndView("device", model);
	}

}
