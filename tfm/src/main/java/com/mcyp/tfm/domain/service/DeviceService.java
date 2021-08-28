package com.mcyp.tfm.domain.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcyp.tfm.domain.exceptions.MapperException;
import com.mcyp.tfm.domain.model.Device;
import com.mcyp.tfm.domain.model.nmap.Address;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DeviceService {
	
	@Autowired
	private NmapResultMapper nmapResultMapper;

	public List<Device> getDevices() throws MapperException {
		return nmapResultMapper.map("devices.json").getNmapRun().getHost().stream()
				.map(host -> this.generateDevice(host.getAddresses()))
				.collect(Collectors.toList());
	}
    
    private Device generateDevice(List<Address> addresses) {
    	return new Device()
    		.setIp(getIp(addresses))
    		.setMac(getMac(addresses))
    		.setVendor(getVendor(addresses));
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
