
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
    "New item",
    "nmap",
    "scan"
})
@Generated("jsonschema2pojo")
public class Scan {

    @JsonProperty("New item")
    private String newItem;
    @JsonProperty("nmap")
    private Nmap nmap;
    @JsonProperty("scan")
    private Scan__1 scan;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("New item")
    public String getNewItem() {
        return newItem;
    }

    @JsonProperty("New item")
    public void setNewItem(String newItem) {
        this.newItem = newItem;
    }

    @JsonProperty("nmap")
    public Nmap getNmap() {
        return nmap;
    }

    @JsonProperty("nmap")
    public void setNmap(Nmap nmap) {
        this.nmap = nmap;
    }

    @JsonProperty("scan")
    public Scan__1 getScan() {
        return scan;
    }

    @JsonProperty("scan")
    public void setScan(Scan__1 scan) {
        this.scan = scan;
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
