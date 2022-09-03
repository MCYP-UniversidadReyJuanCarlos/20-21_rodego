package com.mcyp.tfm.domain.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mcyp.tfm.domain.model.FileLine;
import com.mcyp.tfm.domain.model.FileLines;
import com.mcyp.tfm.domain.model.firmware.Addresses;
import com.mcyp.tfm.domain.model.firmware.Bin;
import com.mcyp.tfm.domain.model.firmware.Binary;
import com.mcyp.tfm.domain.model.firmware.Configuration;
import com.mcyp.tfm.domain.model.firmware.Database;
import com.mcyp.tfm.domain.model.firmware.Email;
import com.mcyp.tfm.domain.model.firmware.FirmwareResponse;
import com.mcyp.tfm.domain.model.firmware.PasswordResponse;
import com.mcyp.tfm.domain.model.firmware.Script;
import com.mcyp.tfm.domain.model.firmware.Ssh;
import com.mcyp.tfm.domain.model.firmware.Ssl;
import com.mcyp.tfm.domain.model.firmware.Url;
import com.mcyp.tfm.domain.model.firmware.WebServer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FirmwareService {
	
	@Autowired
	private FirmwareResultMapper firmwareResultMapper;
	
	public List<FileLine> getFileLine() {
		this.createResponse();
		return firmwareResultMapper.getLines().stream()
			.map(line -> new FileLine().setLine(line))
			.collect(Collectors.toList());
	}
	
	public Map<String, List<String>> textToMap() {
		List<String> lines = firmwareResultMapper.getLines();
		Map<String, List<String>> map = new HashMap<>();
		
		FileLines fileLines = new FileLines();
		String key = "";
		List<String> values = null;
		for(String line: lines) {
			
			if(line.startsWith("**") || line.contains("extracted")) {
				continue;
			}
			if(line.startsWith("###")) {
				log.info("type: " +line.substring(line.lastIndexOf("#")+1, line.length()));
				key = line.substring(line.lastIndexOf("#")+1, line.length()).trim();
				values = new ArrayList<String>();
				continue;
			} else if(line.startsWith("---")) {
				key = line.substring(line.indexOf(" "), line.lastIndexOf(" ")).trim();
				values = new ArrayList<String>();
				continue;
			}
			if(map.containsKey(key)) {
				map.get(key).add(line);
			}else {
				values.add(line);
				map.put(key, values);
			}
			
		}
		return map;
	}
	
	public List<FileLines> getFileLines(){
		return mapToFileLines(textToMap());
	}
	
	public List<FileLines> mapToFileLines(Map<String, List<String>> map) {
		return map.entrySet().stream()
			.map(entry -> new FileLines().setType(entry.getKey()).setLine(entry.getValue()))
			.collect(Collectors.toList());
	}
	
	public FirmwareResponse createResponse() {
		List<String> lines = this.firmwareResultMapper.getLines();
		
		FirmwareResponse response = new FirmwareResponse()
			.setAddresses(new Addresses().setAddresses(this.filterIps(lines)))
			.setBin(new Bin().setBinFiles(this.filterLine(lines, ".bin")))
			.setBinary(new Binary()
				.setBusybox(this.filterLine(lines, "busybox"))
				.setDropbear(this.filterLine(lines, "dropbear"))
				.setOpenssl(this.filterLine(lines, "openssl"))
				.setScp(this.filterLine(lines, "scp"))
				.setSftp(this.filterLine(lines, "sftp"))
				.setSsh(this.filterLine(lines, "ssh"))
				.setSshd(this.filterLine(lines, "sshd"))
				.setTelnet(this.filterLine(lines, "telnet"))
				.setTelnetd(this.filterLine(lines, "telnetd"))
				.setTftp(this.filterLine(lines, "tftp")))
			.setConfiguration(new Configuration()
				.setCfg(this.filterLine(lines, ".cfg"))
				.setConf(this.filterLine(lines, ".conf"))
				.setIni(this.filterLine(lines, ".ini")))
			.setDatabase(new Database()
				.setDb(this.filterLine(lines, ".db"))
				.setDb(this.filterLine(lines, ".sqlite"))
				.setSqlite3(this.filterLine(lines, ".sqlite3")))
			.setEmail(new Email().setEmails(this.filterLine(lines, "@")))
			.setMd5Hash(null)
			.setPassword(new PasswordResponse()
				.setPasswd(this.filterLine(lines, "passwd"))
				.setPsk(this.filterLine(lines, "shadow"))
				.setShadow(this.filterLine(lines, ".psk")))
			.setPattern(null)
			.setScript(new Script().setScripts(this.filterLine(lines, ".sh")))
			.setSsh(new Ssh()
				.setAuthorizedKeys(this.filterLine(lines, "authorized_keys"))
				.setHostKey(this.filterLine(lines, "host_key"))
				.setIdDsa(this.filterLine(lines, "id_dsa"))
				.setIdRsa(this.filterLine(lines, "id_rsa"))
				.setPub(this.filterLine(lines, ".pub")))
			.setSsl(new Ssl()
				.setCer(this.filterLine(lines, ".cer"))
				.setCrt(this.filterLine(lines, ".crt"))
				.setKey(this.filterLine(lines, ".key"))
				.setP12(this.filterLine(lines, ".p12"))
				.setP7b(this.filterLine(lines, ".p7b"))
				.setPem(this.filterLine(lines, ".pem")))
			.setUrl(new Url().setUrls(this.filterUrls(lines)))
			.setWebServer(new WebServer()
				.setAlphapd(this.isServerActive(lines, "alphapd"))
				.setApache(this.isServerActive(lines, "apache"))
				.setHttpd(this.isHttpdActive(lines))
				.setLighttpd(this.isServerActive(lines, "lighttpd")));
		
		return response;
		
	}
	
	private boolean isServerActive(List<String> lines, String server) {
		log.error("server: " + server + " " + this.filterLine(lines, server));
		return this.filterLine(lines, server).size() > 0;
	}
	
	private boolean isHttpdActive(List<String> lines) {
		return this.filterLine(lines, "httpd").stream()
			.filter(l -> !l.contains("lighttpd"))
			.collect(Collectors.toList()).size() > 0;
	}
	
	private List<String> filterUrls(List<String> lines){
		List<String> urls = this.filterLine(lines, "http:");	
		urls.addAll(this.filterLine(lines, "https:"));
		return urls;
	}
	
	private List<String> filterLine(List<String> lines, String filter) {
		return lines.stream()
			.filter(l -> l.contains(filter) && !l.contains("#"))
			.collect(Collectors.toList());
	}
	
	private List<String> filterIps(List<String> lines){
		return lines.stream()
			.filter(l -> this.isValid(l))
			.collect(Collectors.toList());
	}
	
	private boolean isValid(String ip) {
		String IPV4_PATTERN = "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";
		Pattern pattern = Pattern.compile(IPV4_PATTERN);
		Matcher matcher = pattern.matcher(ip);
        return matcher.matches();
	}
}
