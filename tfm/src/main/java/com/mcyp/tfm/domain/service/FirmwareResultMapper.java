package com.mcyp.tfm.domain.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcyp.tfm.domain.model.nmap.NmapResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FirmwareResultMapper {
	
	@Value("${tfm.data}")
	private String tfmData;

	public List<String> getLines() {
		try {
			return readFile().collect(Collectors.toList());
		} catch (IOException e) {
			log.error(e.getMessage());
			return Arrays.asList(new String());
		}
	}
	
	private File[] listFiles() {
		File file = new File(tfmData);
		return file.listFiles((f, name) -> name.startsWith("firmwalker"));	
	}
	
	private Stream<String> readFile() throws IOException {
		return Files.lines(listFiles()[0].toPath());
	}
}
