package com.mcyp.tfm.domain.model.firmware;

import java.util.List;

import lombok.Data;

@Data
public class Database {

	private List<String> db;
	private List<String> sqlite;
	private List<String> sqlite3;
	
}
