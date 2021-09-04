package com.mcyp.tfm.domain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcyp.tfm.domain.model.FileLine;
import com.mcyp.tfm.domain.model.FileLines;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FirmwareService {
	
	@Autowired
	private FirmwareResultMapper firmwareResultMapper;
	
	public List<FileLine> getFileLine() {
		return firmwareResultMapper.getLines().stream()
			.map(line -> new FileLine().setLine(line))
			.collect(Collectors.toList());
	}
	
	public Map<String, List<String>> textToMap() {
		List<String> lines = firmwareResultMapper.getLines();
		Map<String, List<String>> map = new HashMap<>();
		
		FileLines fileLines = new FileLines();
		String key = "";
		List<String> values = null;
		for(String line: lines) {
			
			if(line.startsWith("**") || line.contains("extracted")) {
				continue;
			}
			if(line.startsWith("###")) {
				log.info("type: " +line.substring(line.lastIndexOf("#")+1, line.length()));
				key = line.substring(line.lastIndexOf("#")+1, line.length()).trim();
				values = new ArrayList<String>();
				continue;
			} else if(line.startsWith("---")) {
				key = line.substring(line.indexOf(" "), line.lastIndexOf(" ")).trim();
				values = new ArrayList<String>();
				continue;
			}
			if(map.containsKey(key)) {
				map.get(key).add(line);
			}else {
				values.add(line);
				map.put(key, values);
			}
			
		}
		return map;
	}
	
	public List<FileLines> getFileLines(){
		return mapToFileLines(textToMap());
	}
	
	public List<FileLines> mapToFileLines(Map<String, List<String>> map) {
		return map.entrySet().stream()
			.map(entry -> new FileLines().setType(entry.getKey()).setLine(entry.getValue()))
			.collect(Collectors.toList());
	}
	
}
