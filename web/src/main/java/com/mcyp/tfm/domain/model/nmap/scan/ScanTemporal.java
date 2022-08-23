package com.mcyp.tfm.domain.model.nmap.scan;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.mcyp.tfm.domain.exceptions.MapperException;

import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ScanTemporal {

	@JsonIgnore
	private Map<String, Host> hosts = new HashMap<String, Host>();
	
	 @JsonAnyGetter
	  public Map<String, Host> getHosts() {
	      return this.hosts;
	  }

	  @JsonAnySetter
	  public void setHosts(String name, Host value) {
	      this.hosts.put(name, value);
	  }
/**
 * try {
			FileInputStream input =  new FileInputStream(tfmData + "/nmap/" + fileName);
			mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
			ScanResult readValue = mapper.readValue(input, ScanResult.class);
			readValue.getScan().getHosts().entrySet().stream()
				.forEach(host -> log.debug("key: " + host.getKey() + " value: " + host.getValue()));
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new MapperException("Cannot map or read file", e);
		}	    
 */
}
