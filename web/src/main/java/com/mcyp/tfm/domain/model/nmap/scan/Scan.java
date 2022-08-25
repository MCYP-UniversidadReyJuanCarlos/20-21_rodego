
package com.mcyp.tfm.domain.model.nmap.scan;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Scan {

    @JsonIgnore
    private Map<String, Object> hosts = new HashMap<String, Object>();

    @JsonAnyGetter
    public Map<String, Object> getHosts() {
		return this.hosts;
	}

    @JsonAnySetter
	public void setHosts(String name, Object value) {
		this.hosts.put(name, value);
	}

}
