
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
    "22",
    "53",
    "80",
    "9000"
})
@Generated("jsonschema2pojo")
public class Tcp__1 {

    @JsonProperty("22")
    private com.mcyp.tfm.domain.model.nmap.scan._22 _22;
    @JsonProperty("53")
    private com.mcyp.tfm.domain.model.nmap.scan._53 _53;
    @JsonProperty("80")
    private com.mcyp.tfm.domain.model.nmap.scan._80 _80;
    @JsonProperty("9000")
    private com.mcyp.tfm.domain.model.nmap.scan._9000 _9000;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("22")
    public com.mcyp.tfm.domain.model.nmap.scan._22 get22() {
        return _22;
    }

    @JsonProperty("22")
    public void set22(com.mcyp.tfm.domain.model.nmap.scan._22 _22) {
        this._22 = _22;
    }

    @JsonProperty("53")
    public com.mcyp.tfm.domain.model.nmap.scan._53 get53() {
        return _53;
    }

    @JsonProperty("53")
    public void set53(com.mcyp.tfm.domain.model.nmap.scan._53 _53) {
        this._53 = _53;
    }

    @JsonProperty("80")
    public com.mcyp.tfm.domain.model.nmap.scan._80 get80() {
        return _80;
    }

    @JsonProperty("80")
    public void set80(com.mcyp.tfm.domain.model.nmap.scan._80 _80) {
        this._80 = _80;
    }

    @JsonProperty("9000")
    public com.mcyp.tfm.domain.model.nmap.scan._9000 get9000() {
        return _9000;
    }

    @JsonProperty("9000")
    public void set9000(com.mcyp.tfm.domain.model.nmap.scan._9000 _9000) {
        this._9000 = _9000;
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
