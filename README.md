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
 

+ <h4> 버전 </h4>

  1.0.0 (2015-12-31)

+ <h4> question </h4>

  lusiue@gmail.com
