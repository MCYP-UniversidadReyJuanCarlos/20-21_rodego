
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
    "timestr",
    "elapsed",
    "uphosts",
    "downhosts",
    "totalhosts"
})
@Generated("jsonschema2pojo")
public class Scanstats {

    @JsonProperty("timestr")
    private String timestr;
    @JsonProperty("elapsed")
    private String elapsed;
    @JsonProperty("uphosts")
    private String uphosts;
    @JsonProperty("downhosts")
    private String downhosts;
    @JsonProperty("totalhosts")
    private String totalhosts;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("timestr")
    public String getTimestr() {
        return timestr;
    }

    @JsonProperty("timestr")
    public void setTimestr(String timestr) {
        this.timestr = timestr;
    }

    @JsonProperty("elapsed")
    public String getElapsed() {
        return elapsed;
    }

    @JsonProperty("elapsed")
    public void setElapsed(String elapsed) {
        this.elapsed = elapsed;
    }

    @JsonProperty("uphosts")
    public String getUphosts() {
        return uphosts;
    }

    @JsonProperty("uphosts")
    public void setUphosts(String uphosts) {
        this.uphosts = uphosts;
    }

    @JsonProperty("downhosts")
    public String getDownhosts() {
        return downhosts;
    }

    @JsonProperty("downhosts")
    public void setDownhosts(String downhosts) {
        this.downhosts = downhosts;
    }

    @JsonProperty("totalhosts")
    public String getTotalhosts() {
        return totalhosts;
    }

    @JsonProperty("totalhosts")
    public void setTotalhosts(String totalhosts) {
        this.totalhosts = totalhosts;
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
