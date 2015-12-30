package com.api.parser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class Abstract_Parser {
	protected Map map;
	public Map makeJson(){
		map = new HashMap<String, Map>();
		try {
			map.put("daumRank", getDaum());
			map.put("naverRank", getNaver());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	public abstract Map getNaver() throws IOException;
	public abstract Map getDaum() throws IOException;
	// daum and naver rank get
}
