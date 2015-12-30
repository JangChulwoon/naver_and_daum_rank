package com.api.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Html_parser extends Abstract_Parser {
	private Map Dmap = null;
	private Map Nmap = null;

	public Map getNaver() throws IOException {
		Document doc = null;
		Nmap = new HashMap<Integer, String>();
		int index = 1;
		String text = "http://www.naver.com/";
		try {
			doc = Jsoup.connect(text).get();
			Elements tds = doc.select(".ranklist dd li a");
			for (Element e : tds) {
				Nmap.put(index,e.attr("title"));
				index++;
				if(index ==11){
					break;
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return Nmap;
		
	}

	public Map getDaum() throws IOException {
		Document doc = null;
		int index = 1;
		Dmap = new HashMap<Integer, String>();
		String text = "http://www.daum.net/";
		try {
			doc = Jsoup.connect(text).get();
			Elements tds = doc.select(".item_g .roll_txt .rank_dummy .txt_issue .ellipsis_g");
			// 다음에는 real 과 dummy rank가 존재합니다 . 그중 dummy를 긁어 왔습니다.
			for (Element e : tds) {
				Dmap.put(index,e.text());
				index++;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return Dmap;
	}

}
