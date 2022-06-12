package seleniumTest;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class SeleniumTest {
	private final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	private final String WEB_DRIVER_PATH = "C:\\chromedriver\\chromedriver.exe";
	
	private String url ="http://project.yermi.works/map";
	private WebDriver driver;
	private String jsLink;
	
	public SeleniumTest() {
		// TODO Auto-generated constructor stub
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		driver= new ChromeDriver();
	}
	public static void main(String[] args) throws InterruptedException {
		SeleniumTest test = new SeleniumTest();
		test.crawl();
	}
	
	public void crawl() throws InterruptedException{
		jsLink =url+"/map.json";
		driver.get(jsLink);
		
		/** 지도내 펜션별 JSON 데이터를 문자열로 변환해 jsonStr 이라는 String 타입 변수에 저장 */
		String jsonStr = driver.findElement(By.tagName("body")).getText();
		/** JSON 문자열을 java 객체로 변환하기 위한 Gson 객체를 생성 */	
		Gson gson = new Gson();
		/** JSON 문자열을 배열로 저장  */
		Type type = new TypeToken<List<Object>>(){}.getType();
		List<Object> list = (List<Object>) gson.fromJson(jsonStr, type);
		
		for (int i = 0; i < list.size(); i++) {
		/** 고유펜션번호를 no 라는 String 타입 변수에 저장 */
		String no = String.valueOf(((Map)list.get(i)).get("pensionid"));
		
		/** 펜션이름을 name 이라는 String 타입 변수에 저장 */
		String name = String.valueOf(((Map)list.get(i)).get("name"));
		
		/** 펜션주소를 address 라는 String 타입 변수에 저장 */
		String address = String.valueOf(((Map)list.get(i)).get("address"));
		
		/** 사장님 한마디를 comments 라는 String 타입 변수에 저장 */
		String comments = String.valueOf(((Map)list.get(i)).get("comments"));
		
		/** 가격을 price 라는 String 타입 변수에 저장 */
		String price = String.valueOf(((Map)list.get(i)).get("price"));
		
		/** 별점을 starRate 라는 String 타입 변수에 저장 */
		String starRate = String.valueOf(((Map)list.get(i)).get("starRate"));
		
		/** 위도를 latitude 라는 String 타입 변수에 저장 */
		String latitude = String.valueOf(((Map)list.get(i)).get("latitude"));
		
		/** 경도를 longitude 라는 String 타입 변수에 저장 */
		String longitude = String.valueOf(((Map)list.get(i)).get("longitude"));
		
		System.out.println("===========================");
		System.out.println(no+"번");
		System.out.println("펜션이름 : "+name);
		System.out.println("펜션주소 : "+address);
		System.out.println("사장님 한마디 : "+comments);
		System.out.println("가격 : "+price+"원");
		System.out.println("별점 : "+starRate);
		System.out.println("경위도 : "+"("+longitude+","+latitude+")");
		}
		
	}
	static By select(String selector) {
		return By.cssSelector(selector);
	}

}
