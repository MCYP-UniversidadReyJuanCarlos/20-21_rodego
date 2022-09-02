
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
    "targetHost",
    "ip",
    "port",
    "rDNS",
    "service",
    "pretest",
    "protocols",
    "grease",
    "ciphers",
    "pfs",
    "serverPreferences",
    "serverDefaults",
    "headerResponse",
    "vulnerabilities",
    "cipherTests",
    "browserSimulations"
})
@Generated("jsonschema2pojo")
public class ScanResult {

    @JsonProperty("targetHost")
    private String targetHost;
    @JsonProperty("ip")
    private String ip;
    @JsonProperty("port")
    private String port;
    @JsonProperty("rDNS")
    private String rDNS;
    @JsonProperty("service")
    private String service;
    @JsonProperty("pretest")
    private List<Pretest> pretest = null;
    @JsonProperty("protocols")
    private List<Protocol> protocols = null;
    @JsonProperty("grease")
    private List<Grease> grease = null;
    @JsonProperty("ciphers")
    private List<Cipher> ciphers = null;
    @JsonProperty("pfs")
    private List<Pf> pfs = null;
    @JsonProperty("serverPreferences")
    private List<ServerPreference> serverPreferences = null;
    @JsonProperty("serverDefaults")
    private List<ServerDefault> serverDefaults = null;
    @JsonProperty("headerResponse")
    private List<HeaderResponse> headerResponse = null;
    @JsonProperty("vulnerabilities")
    private List<Vulnerability> vulnerabilities = null;
    @JsonProperty("cipherTests")
    private List<CipherTest> cipherTests = null;
    @JsonProperty("browserSimulations")
    private List<BrowserSimulation> browserSimulations = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("targetHost")
    public String getTargetHost() {
        return targetHost;
    }

    @JsonProperty("targetHost")
    public void setTargetHost(String targetHost) {
        this.targetHost = targetHost;
    }

    @JsonProperty("ip")
    public String getIp() {
        return ip;
    }

    @JsonProperty("ip")
    public void setIp(String ip) {
        this.ip = ip;
    }

    @JsonProperty("port")
    public String getPort() {
        return port;
    }

    @JsonProperty("port")
    public void setPort(String port) {
        this.port = port;
    }

    @JsonProperty("rDNS")
    public String getrDNS() {
        return rDNS;
    }

    @JsonProperty("rDNS")
    public void setrDNS(String rDNS) {
        this.rDNS = rDNS;
    }

    @JsonProperty("service")
    public String getService() {
        return service;
    }

    @JsonProperty("service")
    public void setService(String service) {
        this.service = service;
    }

    @JsonProperty("pretest")
    public List<Pretest> getPretest() {
        return pretest;
    }

    @JsonProperty("pretest")
    public void setPretest(List<Pretest> pretest) {
        this.pretest = pretest;
    }

    @JsonProperty("protocols")
    public List<Protocol> getProtocols() {
        return protocols;
    }

    @JsonProperty("protocols")
    public void setProtocols(List<Protocol> protocols) {
        this.protocols = protocols;
    }

    @JsonProperty("grease")
    public List<Grease> getGrease() {
        return grease;
    }

    @JsonProperty("grease")
    public void setGrease(List<Grease> grease) {
        this.grease = grease;
    }

    @JsonProperty("ciphers")
    public List<Cipher> getCiphers() {
        return ciphers;
    }

    @JsonProperty("ciphers")
    public void setCiphers(List<Cipher> ciphers) {
        this.ciphers = ciphers;
    }

    @JsonProperty("pfs")
    public List<Pf> getPfs() {
        return pfs;
    }

    @JsonProperty("pfs")
    public void setPfs(List<Pf> pfs) {
        this.pfs = pfs;
    }

    @JsonProperty("serverPreferences")
    public List<ServerPreference> getServerPreferences() {
        return serverPreferences;
    }

    @JsonProperty("serverPreferences")
    public void setServerPreferences(List<ServerPreference> serverPreferences) {
        this.serverPreferences = serverPreferences;
    }

    @JsonProperty("serverDefaults")
    public List<ServerDefault> getServerDefaults() {
        return serverDefaults;
    }

    @JsonProperty("serverDefaults")
    public void setServerDefaults(List<ServerDefault> serverDefaults) {
        this.serverDefaults = serverDefaults;
    }

    @JsonProperty("headerResponse")
    public List<HeaderResponse> getHeaderResponse() {
        return headerResponse;
    }

    @JsonProperty("headerResponse")
    public void setHeaderResponse(List<HeaderResponse> headerResponse) {
        this.headerResponse = headerResponse;
    }

    @JsonProperty("vulnerabilities")
    public List<Vulnerability> getVulnerabilities() {
        return vulnerabilities;
    }

    @JsonProperty("vulnerabilities")
    public void setVulnerabilities(List<Vulnerability> vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }

    @JsonProperty("cipherTests")
    public List<CipherTest> getCipherTests() {
        return cipherTests;
    }

    @JsonProperty("cipherTests")
    public void setCipherTests(List<CipherTest> cipherTests) {
        this.cipherTests = cipherTests;
    }

    @JsonProperty("browserSimulations")
    public List<BrowserSimulation> getBrowserSimulations() {
        return browserSimulations;
    }

    @JsonProperty("browserSimulations")
    public void setBrowserSimulations(List<BrowserSimulation> browserSimulations) {
        this.browserSimulations = browserSimulations;
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
