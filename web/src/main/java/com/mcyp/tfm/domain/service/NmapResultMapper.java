package com.mcyp.tfm.domain.service;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcyp.tfm.domain.exceptions.MapperException;
import com.mcyp.tfm.domain.model.nmap.NmapResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NmapResultMapper {
	
	@Value("${tfm.data}")
	private String tfmData;

	public NmapResult map(String fileName) throws MapperException {
		ObjectMapper mapper = new ObjectMapper();		
		NmapResult nmapResult;
		try {
			FileInputStream input =  new FileInputStream(tfmData + "/nmap/" + fileName);
			mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			nmapResult = mapper.readValue(input, NmapResult.class);
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new MapperException("Cannot map or read file", e);
		}
		return nmapResult;
	}
}
