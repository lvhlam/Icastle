package createnew;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class createnewuser {
	
	public WebDriver driver;
	public String url = "http://192.168.1.164:8081/#/" ;
	    //
	//FirefoxDriver driver = new FirefoxDriver();
	String[] logindata = {"admin","123456"};
    String[] userinfo = {"icastle04"+ new Random().nextInt() , "icastle04"+ new Random().nextInt(), "icastles" + new Random().nextInt() + "@yopmail.com","123456","123456","root","1"};
    String[] usgroup = {"Testgroup" + new Random().nextInt(),"123" + new Random().nextInt()};
	
@Test
public void login () {
	
	driver = new ChromeDriver();

   //System.setProperty("webdriver.gecko.driver","geckodriver.exe");		
    System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    this.driver.get(url);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.findElement(By.cssSelector("#username")).sendKeys(logindata[0]);
    driver.findElement(By.cssSelector("#password")).sendKeys(logindata[1]);
    
    // Sleep for input capcha     
    try {
    	Thread.sleep(10000);
    } catch (InterruptedException e1) {
    	// TODO Auto-generated catch block
    	e1.printStackTrace();
}
   
//WebElement btnlogin = driver.findElement(By.cssSelector("#login-box-content > button"));
//WebDriverWait wait6 = new WebDriverWait(driver, 20);
//wait6.until(ExpectedConditions.elementToBeClickable(btnlogin));  
//btnlogin.click();

//*[@class = 'nav navbar-nav']//*[text()= 'Users']

    WebElement navuser = driver.findElement(By.xpath("//*[@class = 'navbar-collapse collapse']//*[@class = 'nav navbar-nav']//li[3]"));
    WebDriverWait wait = new WebDriverWait(driver, 20);
    wait.until(ExpectedConditions.elementToBeClickable(navuser));  
    navuser.click();

//  WebDriverWait wait4 = new WebDriverWait(driver, 20);
//wait4.withTimeout(Duration.ofSeconds(5));
}	
//     String valueData = "testabc" + new Random().nextInt();
 
  
 @Test
  public void test_case_01 ()   {
  
  WebElement btnuser = driver.findElement(By.xpath("//*[@class = 'content row']//*[@class = 'row']//ul/li[1]"));
  WebDriverWait wait2 = new WebDriverWait(driver, 20);
  wait2.until(ExpectedConditions.elementToBeClickable(btnuser));  
  btnuser.click();
     
  this.driver.findElement(By.xpath("//*[@class='actions-container']//*[button='Add new user']")).click();
  this.driver.findElement(By.cssSelector("#username")).sendKeys(userinfo[0]);
  this.driver.findElement(By.cssSelector("#fullname")).sendKeys(userinfo[1]);
  this.driver.findElement(By.cssSelector("#email")).sendKeys(userinfo[2]);
  this.driver.findElement(By.cssSelector("#password")).sendKeys(userinfo[3]);
  this.driver.findElement(By.cssSelector("#password-repeat")).sendKeys(userinfo[4]);
//  driver.findElement(By.cssSelector("#tenant-user")).sendKeys(userinfo[5]);
//  driver.findElement(By.cssSelector("#parent-group")).sendKeys(userinfo[6]);


  WebElement btncreateuser = driver.findElement(By.xpath("//div[@class = 'padding25 col-sm-9 col-sm-offset-2']//button[@type='submit']"));
  WebDriverWait wait7 = new WebDriverWait(driver, 20);
  wait7.until(ExpectedConditions.elementToBeClickable(btncreateuser));  
  btncreateuser.click();

  try {
	  Thread.sleep(2000);
	 } catch (InterruptedException e1) {
	  // TODO Auto-generated catch block
	  e1.printStackTrace();
	 }
  
  
  driver.findElement(By.cssSelector("#user-list-data-filter")).sendKeys(userinfo[1]);
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
    if (userinfo[0].equals(tdvalue)) {
     System.out.println("PASSED");
     return;
    }
   } catch (Exception e) {
    System.out.println(e);
    e.printStackTrace();
   }
 } 
  
  System.out.println("FAILED");
  
  return;
  }
 //--------------------------------------------------------------------- 
  //Test case 2  : check  create user group
  //*[@class='content row']//*[text()='User Groups']
  //*[@class = 'content row']//*[@class = 'row']//ul/li[1]
@Test
  public void test_case_02() {
  
  try {
	  Thread.sleep(2000);
	 } catch (InterruptedException e1) {
	  // TODO Auto-generated catch block
	  e1.printStackTrace();
	 }
   
  WebElement btnusgr = driver.findElement(By.xpath( "//*[@class = 'content row']//*[@class = 'row']//ul/li[2]"));
  WebDriverWait wait1 = new WebDriverWait(driver, 20);
  wait1.until(ExpectedConditions.elementToBeClickable(btnusgr));  
  btnusgr.click();
  
  driver.findElement(By.xpath("//*[@class='actions-container']//*[text()='Add new group']")).click();
  driver.findElement(By.cssSelector("#role-name")).sendKeys(usgroup[0]);
  driver.findElement(By.cssSelector("#role-description")).sendKeys(usgroup[1]);
//  driver.findElement(By.cssSelector("#parent-group")).sendKeys(usgroup[2]);
//  driver.findElement(By.cssSelector("#parent-group")).sendKeys(usgroup[3]);
  driver.findElement(By.cssSelector("#permissionSelectorTabs-tab-DASHBOARD")).click();
  driver.findElement(By.xpath("//*[@id=\"permissionSelectorTabs-pane-DASHBOARD\"]//*[text()= 'Select all']")).click();
  
  WebElement btncreateusergr = driver.findElement(By.cssSelector("#create-user-form > button.btn.btn-primary"));
  WebDriverWait wait11  = new WebDriverWait(driver, 10);
  wait11.until(ExpectedConditions.elementToBeClickable(btncreateusergr));  
  btncreateusergr.click();
  

  driver.findElement(By.cssSelector("#role-list-data-filter")).sendKeys(usgroup[0]);
  driver.findElement(By.xpath("//*[@id=\"main-content\"]//*[text()='Filter']")).click(); 
  try {
	  Thread.sleep(2000);
	 } catch (InterruptedException e1) {
	  // TODO Auto-generated catch block
	  e1.printStackTrace();
	 }
  WebElement element1 = driver.findElement(By.xpath("//table [@class='table table-hover']"));
  List<WebElement> tdElements1 = element1.findElements(By.tagName("td"));
  for(WebElement we : tdElements1) {
   try {
     String tdvalue = we.getText();
    System.out.println(tdvalue);
    if (usgroup[0].equals(tdvalue)) {
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
 
 @AfterTest
 public void shutDownDriver() {
	 driver.close();
 }
  
}
