
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
    "command_line",
    "scaninfo",
    "scanstats"
})
@Generated("jsonschema2pojo")
public class Nmap {

    @JsonProperty("command_line")
    private String commandLine;
    @JsonProperty("scaninfo")
    private Scaninfo scaninfo;
    @JsonProperty("scanstats")
    private Scanstats scanstats;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("command_line")
    public String getCommandLine() {
        return commandLine;
    }

    @JsonProperty("command_line")
    public void setCommandLine(String commandLine) {
        this.commandLine = commandLine;
    }

    @JsonProperty("scaninfo")
    public Scaninfo getScaninfo() {
        return scaninfo;
    }

    @JsonProperty("scaninfo")
    public void setScaninfo(Scaninfo scaninfo) {
        this.scaninfo = scaninfo;
    }

    @JsonProperty("scanstats")
    public Scanstats getScanstats() {
        return scanstats;
    }

    @JsonProperty("scanstats")
    public void setScanstats(Scanstats scanstats) {
        this.scanstats = scanstats;
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
