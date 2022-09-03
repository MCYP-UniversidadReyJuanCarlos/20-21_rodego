package com.mcyp.tfm.domain.model.firmware;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Binary {

	List<String> ssh;
	List<String> sshd;
	List<String> scp;
	List<String> sftp;
	List<String> tftp;
	List<String> dropbear;
	List<String> busybox;
	List<String> telnet;
	List<String> telnetd;
	List<String> openssl;
	
}
