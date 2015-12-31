# naver_and_daum_rank
  위 코드는 Spring 프레임워크를 사용하여 작성되었습니다.     
  Jsoup를 이용하여 naver 와 daum의 검색어를 가져와 json 형식으로 뿌려주는 형태입니다.
  
+ <h4> 사용방법 </h4>
  com.api.parser 패키지 안에 있는 class를 사용합니다.
  Spring 환경에서 사용방법은 아래와 같습니다.
  Parser 클래스에 있는 makeJson 메소드는 Map을 반환하여 돌려줍니다.


```
  @RequestMapping(value = "/", method = RequestMethod.GET)        
	@ResponseBody  
	public Map home(Locale locale, Model model) {  
		Map map;  
		Abstract_Parser parser = new Html_parser();  
		map = parser.makeJson();  
		return map;  
	}  
```
+ <h4>  세부내용 </h4>


+ abstract class Abstract_Parser 
  
  추상클래스로 구현되었습니다.  진행과정을 간단히 표현하였습니다. 
  makJson메소드만 구현이 되어있습니다. 

```
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
```

+ class Html_parser extends Abstract_Parser 

  추상클래스를 상속받아 세부적인 로직이 작성되었습니다.
  
```
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
  ```

+ <h4>  License </h4>
  
  BSD License

+ <h4> 버전 </h4>

  1.0.0 (2015-12-31)

+ <h4> question </h4>

  lusiue@gmail.com
