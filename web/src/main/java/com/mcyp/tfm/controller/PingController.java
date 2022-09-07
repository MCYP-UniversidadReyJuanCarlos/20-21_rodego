package com.mcyp.tfm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PingController {

	@GetMapping("/ping")
	public ResponseEntity<Void> ping(){
		log.debug("request");
		return ResponseEntity.ok().build();
	}
}
