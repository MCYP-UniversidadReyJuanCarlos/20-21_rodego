package com.mcyp.tfm.domain.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mcyp.tfm.domain.exceptions.MapperException;
import com.mcyp.tfm.domain.model.ssh.Recommendation;
import com.mcyp.tfm.domain.model.ssh.Ssh;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SshMapper {

	@Value("${tfm.data}")
	private String tfmData;
	
	public Ssh map(String fileName) throws MapperException {
		Path path = Paths.get(tfmData + "/nmap/ssh_audit_" + fileName + ".txt");
			         
		try(Stream<String> lines = Files.lines(path)){
			List<String> recommendations = lines.filter(line -> line.contains("(rec)")).collect(Collectors.toList());
			return new Ssh().setRecommendations(recommendations.stream().map(line -> this.create(line)).collect(Collectors.toList()));
			
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new MapperException("Cannot map or read file", e);
		}
	}
	
	private Recommendation create(String recommendation) {
		int firstIndex = recommendation.indexOf("-");
		int secondIndex = recommendation.indexOf("--");
		int lastSpace = recommendation.lastIndexOf(" ");
		
		return new Recommendation()
			.setAlgorithm(recommendation.substring(firstIndex, secondIndex).trim())
			.setRecommendation(recommendation.substring(secondIndex + 3, lastSpace).trim());
	}
}
