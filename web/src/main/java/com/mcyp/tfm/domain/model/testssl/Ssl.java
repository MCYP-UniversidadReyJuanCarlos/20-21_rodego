
package com.mcyp.tfm.domain.model.testssl;

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
    "Invocation",
    "at",
    "version",
    "openssl",
    "startTime",
    "scanResult",
    "scanTime"
})
@Generated("jsonschema2pojo")
public class Ssl {

    @JsonProperty("Invocation")
    private String invocation;
    @JsonProperty("at")
    private String at;
    @JsonProperty("version")
    private String version;
    @JsonProperty("openssl")
    private String openssl;
    @JsonProperty("startTime")
    private String startTime;
    @JsonProperty("scanResult")
    private List<ScanResult> scanResult = null;
    @JsonProperty("scanTime")
    private Integer scanTime;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Invocation")
    public String getInvocation() {
        return invocation;
    }

    @JsonProperty("Invocation")
    public void setInvocation(String invocation) {
        this.invocation = invocation;
    }

    @JsonProperty("at")
    public String getAt() {
        return at;
    }

    @JsonProperty("at")
    public void setAt(String at) {
        this.at = at;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("openssl")
    public String getOpenssl() {
        return openssl;
    }

    @JsonProperty("openssl")
    public void setOpenssl(String openssl) {
        this.openssl = openssl;
    }

    @JsonProperty("startTime")
    public String getStartTime() {
        return startTime;
    }

    @JsonProperty("startTime")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("scanResult")
    public List<ScanResult> getScanResult() {
        return scanResult;
    }

    @JsonProperty("scanResult")
    public void setScanResult(List<ScanResult> scanResult) {
        this.scanResult = scanResult;
    }

    @JsonProperty("scanTime")
    public Integer getScanTime() {
        return scanTime;
    }

    @JsonProperty("scanTime")
    public void setScanTime(Integer scanTime) {
        this.scanTime = scanTime;
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
