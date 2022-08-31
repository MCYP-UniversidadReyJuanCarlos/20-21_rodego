package com.mcyp.tfm.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcyp.tfm.domain.exceptions.HostNotFoundException;
import com.mcyp.tfm.domain.exceptions.MapperException;
import com.mcyp.tfm.domain.model.nmap.scan.response.HostResult;

@Service
public class AnalysisService {

	@Autowired
	private ScanMapper scanMapper;

	@Autowired
	private DirectoriesMapper directoriesMapper;
	
	@Autowired
	private SslMapper sslMapper;
	
	@Autowired
	private SshMapper sshMapper;
	
	public List<HostResult> getHosts() throws MapperException{
		return this.scanMapper.map("result.json");
	}
	
	public HostResult getHost(String ip) throws MapperException, HostNotFoundException{
		List<HostResult> hosts = this.scanMapper.map("result.json");
		
		Optional<HostResult> hostResult = hosts.stream().filter(h -> h.getAddresses().getIpv4().equals(ip)).findFirst();
		if(hostResult.isPresent()) {
			HostResult host = hostResult.get();
			if(host.hasProtocol("ssh")) {
				host.setSsh(this.sshMapper.map(host.getAddresses().getIpv4()));
			}
			
			if(host.hasProtocol("http") || host.hasProtocol("https") ) {
				host.setDirectories(this.directoriesMapper.map(host.getAddresses().getIpv4() + ".json"));
			}			
			
			if(host.hasProtocol("ssl") || host.hasProtocol("tls") || host.hasProtocol("https")) {
				host.setSsl(this.sslMapper.map(host.getAddresses().getIpv4() + ".json"));
			}
			
			return host;
		}
		
		throw new HostNotFoundException(String.format("Cannot find host: %s", ip));
	}
	
}
