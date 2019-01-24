package createnew;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Inews {
	
	public WebDriver driver;
	public String url = "http://192.168.1.164:8081/#/" ;
	 	//FirefoxDriver driver = new FirefoxDriver();
	String  [] logindata = {"admin","123456"};
	String  [] tedata = {"tenant"+ new Random().nextInt() ,"tna" + new Random().nextInt(),"root", new Random().nextInt() + "F.,Central","Icastle12"};
 	@BeforeTest

	public void login() throws InterruptedException {

	driver = new ChromeDriver();
		// Firefox 
     System.setProperty("webdriver.chrome.driver","chromedriver.exe");		
			driver.get(url);
		    driver.manage().window().maximize();
		    driver.findElement(By.cssSelector("#username")).sendKeys(logindata[0]);
		    driver.findElement(By.cssSelector("#password")).sendKeys(logindata[1]);
		    Thread.sleep(8000);
		    }
	
 	@Test (priority = 1 )
	public void mark_inews() {

		// wait to click on nav bar
		WebElement inews = driver.findElement(By.xpath("//*[@class = 'navbar-collapse collapse']//*[@class = 'nav navbar-nav']//li[5]"));
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(inews));
		inews.click();

		// list all item in grid
		WebElement listnews = driver.findElement(By.xpath("//*[@id=\"main-row\"]/div/div/div/div[2]/div[2]"));
		// get text from list below
		WebElement b = listnews.findElement(By.tagName("b"));
		String title = b.getText();
		// click on button of element have text below
		driver.findElement(By.xpath("//button[@title='Add to iNewsmark']")).click();
//		 List<WebElement> tdElements = element.findElements(By.cssSelector("#entry"));
	 		// find list
		WebElement inewmark = driver.findElement(By.xpath("//*[@id=\"sidebar\"]"));
		// list element tag B for get text
		List<WebElement> bElements = inewmark.findElements(By.tagName("b"));
		// compare text of 2 grid
		for (WebElement we : bElements) {
			try {
				String bdvalue = we.getText();
				System.out.println(bdvalue);
				if (bdvalue.equals(title)) {

					System.out.println("PASSED");
//			return;
				}
				else {
					System.out.println("FAILED");
				}
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
		}
		
}

 @Test(priority = 2)
public void un_mark_inews() {

	WebElement inewmark = driver.findElement(By.xpath("//*[@id=\"sidebar\"]"));
	// list element tag B for get text
	List<WebElement> bElements = inewmark.findElements(By.tagName("b"));
	
	for (WebElement we : bElements) {
		try {
			String bdvalue = we.getText();
			System.out.println(bdvalue);
			
			WebElement listnews = driver.findElement(By.xpath("//*[@id=\"main-row\"]/div/div/div/div[2]/div[2]"));
			List<WebElement> r_b_elements = listnews.findElements(By.tagName("b"));
			
			for (WebElement r_web : r_b_elements )	
			{
				String b_value = r_web.getText();
				if (bdvalue.equals(b_value)) {
					inewmark.findElement(By.xpath("//*[@class = 'btnMark']")).click();
					 					 				 
				}
			}
	}
		catch (Exception e) {
		      System.out.println(e);
		      e.printStackTrace();
		     }
			}
	System.out.println("FAILED");
}
@AfterTest
 	public void shutDownDriver() {
 		 driver.close();
 	} 	
 	
}
