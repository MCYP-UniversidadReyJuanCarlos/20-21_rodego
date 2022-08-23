
package com.mcyp.tfm.domain.model.nmap.scan;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ipv4"
})
@Generated("jsonschema2pojo")
public class Addresses {

    @JsonProperty("ipv4")
    private String ipv4;
    @JsonProperty("mac")
    private String mac;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ipv4")
    public String getIpv4() {
        return ipv4;
    }

    @JsonProperty("ipv4")
    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }

    @JsonProperty("mac")
    public String getMac() {
		return mac;
	}

    @JsonProperty("mac")
	public void setMac(String mac) {
		this.mac = mac;
	}

	@JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
