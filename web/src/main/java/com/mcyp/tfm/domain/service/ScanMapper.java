package com.mcyp.tfm.domain.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcyp.tfm.domain.exceptions.MapperException;
import com.mcyp.tfm.domain.model.nmap.scan.Host;
import com.mcyp.tfm.domain.model.nmap.scan.ScanResult;
import com.mcyp.tfm.domain.model.nmap.scan.response.AddressesResult;
import com.mcyp.tfm.domain.model.nmap.scan.response.HostResult;
import com.mcyp.tfm.domain.model.nmap.scan.response.StatusResult;
import com.mcyp.tfm.domain.model.nmap.scan.response.TcpResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ScanMapper {

	@Value("${tfm.data}")
	private String tfmData;

	public List<HostResult> map(String fileName) throws MapperException {
		ObjectMapper mapper = new ObjectMapper();		
		List<HostResult> hosts = new ArrayList<HostResult>();
		
		try {
			FileInputStream input =  new FileInputStream(tfmData + "/nmap/" + fileName);
			mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			ScanResult result = mapper.readValue(input, ScanResult.class);
			
			for(Entry<String, Object> entry: result.getScan().getHosts().entrySet()) {
				hosts.add(this.map(mapper, mapper.treeToValue(mapper.valueToTree(entry.getValue()), Host.class)));
			}
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new MapperException("Cannot map or read file", e);
		}
		return hosts;

	}
	
	private HostResult map(ObjectMapper mapper, Host host) {
		List<TcpResult> tcps = new ArrayList<TcpResult>();
		
		host.getTcp().getAdditionalProperties().entrySet().stream()
			.forEach(e -> {
				tcps.add(this.mapTcp(mapper, e.getKey(), e.getValue()));
			}
		);
		
		return new HostResult()
			.setTcp(tcps)
			.setAddresses(new AddressesResult().setIpv4(host.getAddresses().getIpv4()).setMac(host.getAddresses().getMac()))
			.setHostnames(null)
			.setStatus(new StatusResult().setReason(host.getStatus().getReason()).setState(host.getStatus().getState()))
			.setVendor(null);
	}
	
	private TcpResult mapTcp(ObjectMapper mapper, String port, Object tcp) {
		JsonNode node = mapper.valueToTree(tcp);
		try {
			return mapper.treeToValue(node, TcpResult.class).setPort(port);
		} catch (JsonProcessingException e) {
			return null;
		} catch (IllegalArgumentException e) {
			return null;
		}
	}
}
