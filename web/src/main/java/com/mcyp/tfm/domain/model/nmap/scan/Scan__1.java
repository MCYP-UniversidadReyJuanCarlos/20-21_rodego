
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
    "192.168.100.105"
})
@Generated("jsonschema2pojo")
public class Scan__1 {

    @JsonProperty("192.168.100.105")
    private com.mcyp.tfm.domain.model.nmap.scan._192168100105 _192168100105;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("192.168.100.105")
    public com.mcyp.tfm.domain.model.nmap.scan._192168100105 get192168100105() {
        return _192168100105;
    }

    @JsonProperty("192.168.100.105")
    public void set192168100105(com.mcyp.tfm.domain.model.nmap.scan._192168100105 _192168100105) {
        this._192168100105 = _192168100105;
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
