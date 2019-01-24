package createnew;
import java.util.List;
import java.util.Random;
//import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class tenant {

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
@Test(priority = 1 )
public void create_new_tenant () {
		    
		  driver.findElement(By.xpath("//*[@class = 'navbar-collapse collapse']//*[@class = 'nav navbar-nav']//li[4]")).click();
				  
		  WebElement btnadte = driver.findElement(By.xpath("//*[@id=\"mainContent\"]//*[@class = 'btn btn-success']"));
		  WebDriverWait wait = new WebDriverWait(driver, 20);
		  wait.until(ExpectedConditions.elementToBeClickable(btnadte));  
		  btnadte.click();
		  		  driver.findElement(By.cssSelector("#tenantName")).sendKeys(tedata[0]);
		  driver.findElement(By.cssSelector("#tenantAlias")).sendKeys(tedata[1]);
		  driver.findElement(By.cssSelector("#tenantParent")).sendKeys(tedata[2]);
		  driver.findElement(By.cssSelector("#companyAddress")).sendKeys(tedata[3]);
		  driver.findElement(By.cssSelector("#gateway_name")).sendKeys(tedata[4]);
		  		  
		  WebElement btncreatetn = driver.findElement(By.xpath("//*[@id=\"create-user-form\"]/div[3]/div/button[1]"));
		  WebDriverWait wait1  = new WebDriverWait(driver, 10);
		  wait1.until(ExpectedConditions.elementToBeClickable(btncreatetn));  
		  btncreatetn.click();
		  
		  try {
			  Thread.sleep(2000);
			 } catch (InterruptedException e1) {
			  // TODO Auto-generated catch block
			  e1.printStackTrace();
			 }
		  
		  driver.findElement(By.cssSelector("#tenant-list-data-filter")).sendKeys(tedata[0]);
		  driver.findElement(By.xpath("//*[@id=\"mainContent\"]//button[1]")).click();      
		  WebElement element = driver.findElement(By.xpath("//table [@class='table table-hover']"));
		  List<WebElement> tdElements = element.findElements(By.tagName("td"));
		  for(WebElement we : tdElements) {
		   try {
		    String tdvalue = we.getText();
		    System.out.println(tdvalue);
		    if (tedata[1].equals(tdvalue)) {
		     System.out.println("PASSED");
		     return;
		    }
		   } catch (Exception e) {
		    System.out.println(e);
		    e.printStackTrace();
		   }
		 } 
		  
		  System.out.println("FAILED");

	}

@Test(priority = 2 )
public void disable_tenant_after() {
  //   String Xpath_button_action = " "//*[@id = 'status-tenant- " + tedata[0]+ "']//*[text()= 'Disable']" ";
	 driver.findElement(By.cssSelector("#tenant-list-data-filter")).sendKeys(tedata[0]);
	  driver.findElement(By.xpath("//*[@id=\"mainContent\"]//button[1]")).click(); 
		  driver.findElement(By.cssSelector("#status-tenant-A2")).sendKeys("Disable");
	 	  WebElement element = driver.findElement(By.xpath("//table [@class='table table-hover']"));  
		  List<WebElement> tdElements = element.findElements(By.tagName("td[3]"));
	  for(WebElement we : tdElements) {
	   try {
	    String tdvalue = we.getText();
	    System.out.println(tdvalue);
	    if ( driver.findElement(By.xpath("")) != null) {
	    	   	
	     System.out.println("PASSED");
	     return;
	    }
	   } catch (Exception e) {
	    System.out.println(e);
	    e.printStackTrace();
	   }
	 } 
	  
	  System.out.println("FAILED");

}
	  
	  
@Test()
 public void check_user_default() throws InterruptedException {
	
	    Thread.sleep(5000);
	
	    driver.findElement(By.xpath("//*[@class = 'navbar-collapse collapse']//*[@class = 'nav navbar-nav']//li[3]")).click();
	    WebElement btnuser = driver.findElement(By.xpath("//*[@class = 'content row']//*[@class = 'row']//ul/li[1]"));
	    WebDriverWait wait2 = new WebDriverWait(driver, 20);
	    wait2.until(ExpectedConditions.elementToBeClickable(btnuser));  
	    btnuser.click(); 
	    String tnuser =  new String("tedata[0]" + "_" + "admin") ;
	    	    
	    driver.findElement(By.cssSelector("#user-list-data-filter")).sendKeys(tnuser);
	    driver.findElement(By.xpath("//*[@class='btn btn-default']")).click();      
	    try {
	  	  Thread.sleep(2000);
	  	 } catch (InterruptedException e1) {
	  	  // TODO Auto-generated catch block
	  	  e1.printStackTrace();
	  	 }
	    WebElement element = driver.findElement(By.cssSelector("#user-list > table > tbody"));
	    List<WebElement> tdElements = element.findElements(By.tagName("td"));
	    for(WebElement we : tdElements) {
	     try {
	      String tdvalue = we.getText();
	      System.out.println(tdvalue);
	      if (tnuser.equals(tdvalue)) {
	       System.out.println("tenant user defaut");
	       return;
	      }
	     } catch (Exception e) {
	      System.out.println(e);
	      e.printStackTrace();
	     }
	   } 
	    
	    System.out.println("user default tenent is not exits");
	    	    return;
	
}


	
@AfterTest
public void shutDownDriver() {
	 driver.close();
}

}
