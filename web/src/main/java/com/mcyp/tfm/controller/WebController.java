package com.mcyp.tfm.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mcyp.tfm.domain.exceptions.MapperException;
import com.mcyp.tfm.domain.model.dirsearch.Dirsearch;
import com.mcyp.tfm.domain.model.nmap.scan.response.HostResult;
import com.mcyp.tfm.domain.model.ssh.Ssh;
import com.mcyp.tfm.domain.model.testssl.Ssl;
import com.mcyp.tfm.domain.service.AnalysisService;
import com.mcyp.tfm.domain.service.DirectoriesMapper;
import com.mcyp.tfm.domain.service.ScanMapper;
import com.mcyp.tfm.domain.service.SshMapper;
import com.mcyp.tfm.domain.service.SslMapper;

@Controller
public class WebController {

	@Autowired
	private AnalysisService analysisService;
	
	@GetMapping("/home")
	public ModelAndView index(Map<String, Object> model) throws MapperException {
		
		model.put(
			"hosts",
			this.analysisService.execute().stream()
				.filter(host -> !"down".equals(host.getStatus().getState()))
				.collect(Collectors.toList())
		);
		return new ModelAndView("home", model);
	}

}
