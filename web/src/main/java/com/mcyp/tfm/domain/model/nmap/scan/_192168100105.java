
package com.mcyp.tfm.domain.model.nmap.scan;

import java.util.HashMap;
import java.util.List;
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
    "hostnames",
    "addresses",
    "vendor",
    "status",
    "tcp"
})
@Generated("jsonschema2pojo")
public class _192168100105 {

    @JsonProperty("hostnames")
    private List<Hostname> hostnames = null;
    @JsonProperty("addresses")
    private Addresses addresses;
    @JsonProperty("vendor")
    private Vendor vendor;
    @JsonProperty("status")
    private Status status;
    @JsonProperty("tcp")
    private Tcp__1 tcp;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("hostnames")
    public List<Hostname> getHostnames() {
        return hostnames;
    }

    @JsonProperty("hostnames")
    public void setHostnames(List<Hostname> hostnames) {
        this.hostnames = hostnames;
    }

    @JsonProperty("addresses")
    public Addresses getAddresses() {
        return addresses;
    }

    @JsonProperty("addresses")
    public void setAddresses(Addresses addresses) {
        this.addresses = addresses;
    }

    @JsonProperty("vendor")
    public Vendor getVendor() {
        return vendor;
    }

    @JsonProperty("vendor")
    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    @JsonProperty("status")
    public Status getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonProperty("tcp")
    public Tcp__1 getTcp() {
        return tcp;
    }

    @JsonProperty("tcp")
    public void setTcp(Tcp__1 tcp) {
        this.tcp = tcp;
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
