package createnew;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class create_dashboard {
	

	// TODO Auto-generated method stub

	  public WebDriver driver;
	  
		public String url = "http://192.168.1.164:8081/#/" ;
		    //
		//FirefoxDriver driver = new FirefoxDriver();
		String  [] logindata = {"admin","123456"};
		String [] cr_dab = {"Dashboard"+ new Random(). nextInt(),"description" + new Random().nextInt()};
		

			

@Test
public void dash_cre () throws InterruptedException {
	

	// Firefox 
// System.setProperty("webdriver.gecko.driver","geckodriver.exe");		

	 driver = new ChromeDriver();
	    driver.get(url);
	    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    driver.manage().window().maximize();
	  	driver.findElement(By.cssSelector("#username")).sendKeys(logindata[0]);
	    driver.findElement(By.cssSelector("#password")).sendKeys(logindata[1]);
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//*[@class = 'navbar-collapse collapse']//*[@class = 'nav navbar-nav']//li[1]")).click();
				  
				
				  
				WebElement btn_cre_dashboard = driver.findElement(By.xpath("//*[@id=\"main-content\"]//*[button = 'Create dashboard']"))  ;
				WebDriverWait wait_btn_cre_dashboard = new WebDriverWait(driver,20);
				wait_btn_cre_dashboard.until(ExpectedConditions.elementToBeClickable(btn_cre_dashboard));
			    btn_cre_dashboard.click();
			    
			    WebElement txt_title = driver.findElement(By.cssSelector("#undefined-title"))  ;
				WebDriverWait wait_txt_title = new WebDriverWait(driver,20);
				wait_txt_title.until(ExpectedConditions.elementToBeClickable(txt_title));
				txt_title.sendKeys(cr_dab[0]);
				driver.findElement(By.cssSelector("#undefined-description")).sendKeys(cr_dab[1]);
			    driver.findElement(By.xpath("//*[@class = 'modal-content']//*[@type='submit']")).click();
			    return;
			    
}	    

	 
			    	
@Test
public void set_default_dash () throws InterruptedException {
	//tra ve list li nam trong ul co class = stream
    List<WebElement> li_list = driver.findElements(By.cssSelector("ul > li.stream")); 

            
   for (WebElement we : li_list) {
	   //for each li
	   try {
		   WebElement aSpan = we.findElement(By.cssSelector("a > span")); 
		   //lay value cua span trong a cua li
			String avalue = aSpan.getText();
		if(cr_dab[0].equals(avalue)) { 			
			
			we.findElement(By.xpath(".//*[@class='dropdown btn-group']")).click();
			we.findElement(By.xpath(".//*[@class=\"dropdown-menu dropdown-menu-right\"]//*[text()='Set as startpage']")).click();
			return;
   	   			
		}
   	}	   
		catch(Exception e) {
	    System.out.println(e);
	   e.printStackTrace();
   
   }
    
      
    }
   
   System.out.println("Can not set default");
   return;
}

@Test
public void check_dashboard_exits() {
			  	
	List<WebElement> li_list = driver.findElements(By.cssSelector("ul > li.stream")); 
	for (WebElement we : li_list) {
		
		   try {
			   WebElement aSpan = we.findElement(By.cssSelector("a > span")); 
		
				String avalue = aSpan.getText();
			if(cr_dab[0].equals(avalue)) { 	
				
				System.out.println("Dashboard Exits");
    			return;
    			
				
			}
		   }catch(Exception e) {
	    		System.out.println(e);
	    		e.printStackTrace();
	    	}
	    }
	    
	   System.out.println("Dashboard Not Exits");
	   return;
}		



@AfterTest
public void shutDownDriver() {
	 driver.close();
}
		
 }
 
