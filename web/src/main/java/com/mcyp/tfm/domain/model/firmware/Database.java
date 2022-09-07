package com.mcyp.tfm.domain.model.firmware;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Database {

	private List<String> db;
	private List<String> sqlite;
	private List<String> sqlite3;
	
}
