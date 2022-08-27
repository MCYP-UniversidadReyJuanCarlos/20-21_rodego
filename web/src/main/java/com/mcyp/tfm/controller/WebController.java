package com.mcyp.tfm.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcyp.tfm.domain.exceptions.MapperException;
import com.mcyp.tfm.domain.model.nmap.scan.response.HostResult;
import com.mcyp.tfm.domain.service.ScanMapper;

@Controller
public class WebController {

	@Autowired
	private ScanMapper mapper;

	@GetMapping("/home")
	public ModelAndView index(Map<String, Object> model) throws MapperException {
		List<HostResult> hosts = this.mapper.map("result.json");
		model.put(
			"hosts",
			hosts.stream().filter(host -> !"down".equals(host.getStatus().getState())).collect(Collectors.toList())
		);
		return new ModelAndView("home", model);
	}

}
