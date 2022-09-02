package com.mcyp.tfm.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcyp.tfm.domain.exceptions.HostNotFoundException;
import com.mcyp.tfm.domain.exceptions.MapperException;
import com.mcyp.tfm.domain.model.nmap.scan.response.HostResult;
import com.mcyp.tfm.domain.model.testssl.ScanResult;
import com.mcyp.tfm.domain.model.testssl.Ssl;
import com.mcyp.tfm.domain.model.testssl.SslResponse;
import com.mcyp.tfm.domain.model.testssl.VulnerabilityResponse;

@Service
public class AnalysisService {

	private static final List<String> severities = List.of("LOW", "MEDIUM", "HIGH", "CRITICAL");;
	
	@Autowired
	private ScanMapper scanMapper;

	@Autowired
	private DirectoriesMapper directoriesMapper;
	
	@Autowired
	private SslMapper sslMapper;
	
	@Autowired
	private SshMapper sshMapper;
	
	public List<HostResult> getHosts() throws MapperException{
		return this.scanMapper.map("result.json");
	}
	
	public HostResult getHost(String ip) throws MapperException, HostNotFoundException{
		List<HostResult> hosts = this.scanMapper.map("result.json");
		
		Optional<HostResult> hostResult = hosts.stream().filter(h -> h.getAddresses().getIpv4().equals(ip)).findFirst();
		if(hostResult.isPresent()) {
			HostResult host = hostResult.get();
			if(host.hasProtocol("ssh")) {
				host.setSsh(this.sshMapper.map(host.getAddresses().getIpv4()));
			}
			
			if(host.hasProtocol("http") || host.hasProtocol("https") ) {
				host.setDirectories(this.directoriesMapper.map(host.getAddresses().getIpv4() + ".json"));
			}			
			
			if(host.hasProtocol("ssl") || host.hasProtocol("tls") || host.hasProtocol("https")) {
				Ssl ssl = this.sslMapper.map(host.getAddresses().getIpv4() + ".json");
				this.filterResult(ssl);
				host.setSsl(this.createSslResponse(ssl));
			}
			
			return host;
		}
		
		throw new HostNotFoundException(String.format("Cannot find host: %s", ip));
	}
	
	private SslResponse createSslResponse(Ssl ssl) {
		List<VulnerabilityResponse> vulns = this.filterResult(ssl);
		return new SslResponse()
			.setAt(ssl.getAt())
			.setInvocation(ssl.getInvocation())
			.setOpenssl(ssl.getOpenssl())
			.setScanResult(ssl.getScanResult().stream().findFirst().orElse(null))
			.setScanTime(ssl.getScanTime())
			.setStartTime(ssl.getStartTime())
			.setVersion(ssl.getVersion())
			.setCriticalVulns(this.getVulns(vulns, "CRITICAL"))
			.setHighVulns(this.getVulns(vulns, "HIGH"))
			.setMediumVulns(this.getVulns(vulns, "MEDIUM"))
			.setLowVulns(this.getVulns(vulns, "LOW"));
	}
	
	private List<VulnerabilityResponse> getVulns(List<VulnerabilityResponse> vulns, String severity){
		return vulns.stream().filter(v -> severity.equals(v.getSeverity())).collect(Collectors.toList());
	}
	
	private List<VulnerabilityResponse> filterResult(Ssl ssl) {
		
		ScanResult scanResult = ssl.getScanResult().stream().findFirst().get();	
//		scanResult.setProtocols(scanResult.getProtocols().stream()
//			.filter(c -> severities.contains(c.getSeverity()))
//			.collect(Collectors.toList()));
//		scanResult.setGrease(scanResult.getGrease().stream().filter(c -> severities.contains(c.getSeverity())).collect(Collectors.toList()));
//		scanResult.setCiphers(scanResult.getCiphers().stream().filter(c -> severities.contains(c.getSeverity())).collect(Collectors.toList()));
//		scanResult.setPfs(scanResult.getPfs().stream().filter(c -> severities.contains(c.getSeverity())).collect(Collectors.toList()));
//		scanResult.setServerPreferences(scanResult.getServerPreferences().stream().filter(c -> severities.contains(c.getSeverity())).collect(Collectors.toList()));
//		scanResult.setServerDefaults(scanResult.getServerDefaults().stream().filter(c -> severities.contains(c.getSeverity())).collect(Collectors.toList()));
//		scanResult.setHeaderResponse(scanResult.getHeaderResponse().stream().filter(c -> severities.contains(c.getSeverity())).collect(Collectors.toList()));
//		scanResult.setVulnerabilities(scanResult.getVulnerabilities().stream().filter(c -> severities.contains(c.getSeverity())).collect(Collectors.toList()));
//		scanResult.setCipherTests(scanResult.getCipherTests().stream().filter(c -> severities.contains(c.getSeverity())).collect(Collectors.toList()));
//		scanResult.setBrowserSimulations(scanResult.getBrowserSimulations().stream().filter(c -> severities.contains(c.getSeverity())).collect(Collectors.toList()));	
//		ssl.setScanResult(List.of(scanResult));
		
		List<VulnerabilityResponse> vulns = this.mapProtocols(scanResult);
		vulns.addAll(this.mapGreases(scanResult));
		vulns.addAll(this.mapCiphers(scanResult));
		vulns.addAll(this.mapPfs(scanResult));
		vulns.addAll(this.mapServerPreferences(scanResult));
		vulns.addAll(this.mapServerDefaults(scanResult));
		vulns.addAll(this.mapHeadersResponse(scanResult));
		vulns.addAll(this.mapVulnerabilities(scanResult));
		vulns.addAll(this.mapChiperTests(scanResult));
		vulns.addAll(this.mapBrowserSimulations(scanResult));
		
		return vulns;
	}
	
	private List<VulnerabilityResponse> mapProtocols(ScanResult scanResult){
		return scanResult.getProtocols().stream()
			.filter(c -> severities.contains(c.getSeverity()))
			.map(c -> new VulnerabilityResponse()
				.setFinding(c.getFinding())
				.setId(c.getId())
				.setSeverity(c.getSeverity()))
			.collect(Collectors.toList());
	}
	
	private List<VulnerabilityResponse> mapGreases(ScanResult scanResult){
		return scanResult.getGrease().stream()
			.filter(c -> severities.contains(c.getSeverity()))
			.map(c -> new VulnerabilityResponse()
				.setFinding(c.getFinding())
				.setId(c.getId())
				.setSeverity(c.getSeverity()))
			.collect(Collectors.toList());
	}
	
	private List<VulnerabilityResponse> mapCiphers(ScanResult scanResult){
		return scanResult.getCiphers().stream()
			.filter(c -> severities.contains(c.getSeverity()))
			.map(c -> new VulnerabilityResponse()
				.setCwe(c.getCwe())
				.setFinding(c.getFinding())
				.setId(c.getId())
				.setSeverity(c.getSeverity()))
			.collect(Collectors.toList());
	}

	private List<VulnerabilityResponse> mapPfs(ScanResult scanResult){
		return scanResult.getPfs().stream()
			.filter(c -> severities.contains(c.getSeverity()))
			.map(c -> new VulnerabilityResponse()
				.setFinding(c.getFinding())
				.setId(c.getId())
				.setSeverity(c.getSeverity()))
			.collect(Collectors.toList());
	}
	
	private List<VulnerabilityResponse> mapServerPreferences(ScanResult scanResult){
		return scanResult.getServerPreferences().stream()
			.filter(c -> severities.contains(c.getSeverity()))
			.map(c -> new VulnerabilityResponse()
				.setFinding(c.getFinding())
				.setId(c.getId())
				.setSeverity(c.getSeverity()))
			.collect(Collectors.toList());
	}
	
	private List<VulnerabilityResponse> mapServerDefaults(ScanResult scanResult){
		return scanResult.getServerDefaults().stream()
			.filter(c -> severities.contains(c.getSeverity()))
			.map(c -> new VulnerabilityResponse()
				.setFinding(c.getFinding())
				.setId(c.getId())
				.setSeverity(c.getSeverity()))
			.collect(Collectors.toList());
	}
	
	private List<VulnerabilityResponse> mapHeadersResponse(ScanResult scanResult){
		return scanResult.getHeaderResponse().stream()
			.filter(c -> severities.contains(c.getSeverity()))
			.map(c -> new VulnerabilityResponse()
				.setFinding(c.getFinding())
				.setId(c.getId())
				.setSeverity(c.getSeverity()))
			.collect(Collectors.toList());
	}
	
	private List<VulnerabilityResponse> mapVulnerabilities(ScanResult scanResult){
		return scanResult.getVulnerabilities().stream()
			.filter(c -> severities.contains(c.getSeverity()))
			.map(c -> new VulnerabilityResponse()
				.setCve(c.getCve())
				.setCwe(c.getCwe())
				.setFinding(c.getFinding())
				.setId(c.getId())
				.setSeverity(c.getSeverity()))
			.collect(Collectors.toList());
	}

	private List<VulnerabilityResponse> mapChiperTests(ScanResult scanResult){
		return scanResult.getCipherTests().stream()
			.filter(c -> severities.contains(c.getSeverity()))
			.map(c -> new VulnerabilityResponse()
				.setFinding(c.getFinding())
				.setId(c.getId())
				.setSeverity(c.getSeverity()))
			.collect(Collectors.toList());
	}
	
	private List<VulnerabilityResponse> mapBrowserSimulations(ScanResult scanResult){
		return scanResult.getBrowserSimulations().stream()
			.filter(c -> severities.contains(c.getSeverity()))
			.map(c -> new VulnerabilityResponse()
				.setFinding(c.getFinding())
				.setId(c.getId())
				.setSeverity(c.getSeverity()))
			.collect(Collectors.toList());
	}
}
