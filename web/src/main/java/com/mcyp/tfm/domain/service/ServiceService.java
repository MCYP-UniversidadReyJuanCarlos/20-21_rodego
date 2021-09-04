package com.mcyp.tfm.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcyp.tfm.domain.exceptions.MapperException;
import com.mcyp.tfm.domain.model.ServiceInfo;
import com.mcyp.tfm.domain.model.ServicePort;
import com.mcyp.tfm.domain.model.nmap.Address;
import com.mcyp.tfm.domain.model.nmap.Host;
import com.mcyp.tfm.domain.model.nmap.NmapResult;
import com.mcyp.tfm.domain.model.nmap.OperativeSystem;
import com.mcyp.tfm.domain.model.nmap.OperativeSystemMatch;
import com.mcyp.tfm.domain.model.nmap.Port;

import lombok.extern.slf4j.Slf4j;
import static java.util.Objects.isNull;

@Service
@Slf4j
public class ServiceService {

	@Autowired
	private NmapResultMapper nmapResultMapper;
	
	public List<ServiceInfo> getServices() throws MapperException {
		return nmapResultMapper.map("services.json").getNmapRun().getHost().stream()
			.filter(host -> host.getStatus().getState().equals("up"))
			.map(host -> this.createService(host))
			.collect(Collectors.toList());
	}
    
	private ServiceInfo createService(Host host) {
		return new ServiceInfo()
			//.setConnectedFrom(host.getOperativeSystem().getUptime().getLastBoot())
			.setIp(getIp(host.getAddresses()))
			.setMac(getMac(host.getAddresses()))
			.setOpenPorts(getPorts(host.getPorts().getPorts()))
			.setOperativeSystem(getOperativeSystemName(host.getOperativeSystem().getOperativeSystemMatch()))
			.setVendor(getVendor(host.getAddresses()));
	}
	
	private List<ServicePort> getPorts(List<Port> ports) {
		if(isNull(ports)) {
			return null;
		}
		return ports.stream().map(port -> this.createPortInfo(port)).collect(Collectors.toList());
	}
	
	private ServicePort createPortInfo(Port port) {
		log.error("portid: " + port.getPortId());
		return new ServicePort()
			.setPort(port.getPortId())
			.setProtocol(port.getProtocol())
			.setService(port.getService().getName());
	}
	
	private String getOperativeSystemName(List<OperativeSystemMatch> operativeSystemMatch) {
		if(isNull(operativeSystemMatch)) {
			return "Unknown";
		}
		return operativeSystemMatch.stream().findFirst().map(OperativeSystemMatch::getName).orElse("Unknown");
	}
    
    private String getIp(List<Address> addresses) {
    	return filterAddresses(addresses, "ipv").map(Address::getAddr).orElse("Unknown");
    }
    
    private String getMac(List<Address> addresses) {
    	return filterAddresses(addresses, "mac").map(Address::getAddr).orElse("Unknown");
    }

    private String getVendor(List<Address> addresses) {
    	return filterAddresses(addresses, "mac").map(Address::getVendor).orElse("Unknown");	
    }
    
    private Optional<Address> filterAddresses(List<Address> addresses, String filter) {
    	return addresses.stream().filter(address -> address.getAddrtype().contains(filter)).findFirst();
    }
    
}
