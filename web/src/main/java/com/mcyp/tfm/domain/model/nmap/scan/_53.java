
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
    "state",
    "reason",
    "name",
    "product",
    "version",
    "extrainfo",
    "conf",
    "cpe"
})
@Generated("jsonschema2pojo")
public class _53 {

    @JsonProperty("state")
    private String state;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("name")
    private String name;
    @JsonProperty("product")
    private String product;
    @JsonProperty("version")
    private String version;
    @JsonProperty("extrainfo")
    private String extrainfo;
    @JsonProperty("conf")
    private String conf;
    @JsonProperty("cpe")
    private String cpe;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("product")
    public String getProduct() {
        return product;
    }

    @JsonProperty("product")
    public void setProduct(String product) {
        this.product = product;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("extrainfo")
    public String getExtrainfo() {
        return extrainfo;
    }

    @JsonProperty("extrainfo")
    public void setExtrainfo(String extrainfo) {
        this.extrainfo = extrainfo;
    }

    @JsonProperty("conf")
    public String getConf() {
        return conf;
    }

    @JsonProperty("conf")
    public void setConf(String conf) {
        this.conf = conf;
    }

    @JsonProperty("cpe")
    public String getCpe() {
        return cpe;
    }

    @JsonProperty("cpe")
    public void setCpe(String cpe) {
        this.cpe = cpe;
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
