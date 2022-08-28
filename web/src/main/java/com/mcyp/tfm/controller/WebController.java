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
import com.mcyp.tfm.domain.service.DirectoriesMapper;
import com.mcyp.tfm.domain.service.ScanMapper;
import com.mcyp.tfm.domain.service.SshMapper;
import com.mcyp.tfm.domain.service.SslMapper;

@Controller
public class WebController {

	@Autowired
	private ScanMapper scanMapper;

	@Autowired
	private DirectoriesMapper directoriesMapper;
	
	@Autowired
	private SslMapper sslMapper;
	
	@Autowired
	private SshMapper sshMapper;
	
	@GetMapping("/home")
	public ModelAndView index(Map<String, Object> model) throws MapperException {
		List<HostResult> hosts = this.scanMapper.map("result.json");
		Ssh ssh = this.sshMapper.map("192.168.100.105");
		Dirsearch directories = this.directoriesMapper.map("192.168.100.105.json");
		Ssl ssl = this.sslMapper.map("192.168.102.1.json");
		model.put(
			"hosts",
			hosts.stream().filter(host -> !"down".equals(host.getStatus().getState())).collect(Collectors.toList())
		);
		return new ModelAndView("home", model);
	}

}
