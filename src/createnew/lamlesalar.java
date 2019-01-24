package createnew;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class lamlesalar {
 public static void main(String[] args) {
  
  
  String valueData = "testabc" + new Random().nextInt();

  System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
  WebDriver driver = new ChromeDriver();
  driver.manage().window().maximize();
  String url = "http://salarfusion.dev.idealogic.com.vn/#/";
  driver.get(url);
  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  driver.findElement(By.cssSelector("#login-username")).sendKeys("test1@yopmail.com");
  driver.findElement(By.cssSelector("#login-password")).sendKeys("welkom");
  driver.findElement(By.cssSelector("#login-cmdlogin")).click();
  
  
  WebElement btnOrg = driver.findElement(By.cssSelector("#function-menu-Organisatie"));
  WebDriverWait wait = new WebDriverWait(driver, 20);
  wait.until(ExpectedConditions.elementToBeClickable(btnOrg));  
  btnOrg.click();
  
  
  
  driver.findElement(By.cssSelector("#menu-items-FinancieelGroep")).click();
    
  WebDriverWait wait4 = new WebDriverWait(driver, 20);
  wait4.withTimeout(Duration.ofSeconds(5));
  
  WebElement btnadd = driver.findElement(By.cssSelector("#financial-group-btadd"));
  WebDriverWait wait2 = new WebDriverWait(driver, 20);
  wait2.until(ExpectedConditions.elementToBeClickable(btnadd));  
  btnadd.click();
  
  
  
  driver.findElement(By.cssSelector("#financial-groep-naam")).sendKeys(valueData);
  driver.findElement(By.xpath("/html/body/div[8]/div/div/div[2]/div/div[3]/div/salar-button/button/span")).click();
  
//  WebDriverWait wait3 = new WebDriverWait(driver, 20);
//  wait3.(Duration.ofSeconds(10));
  
  try {
   Thread.sleep(3000);
  } catch (InterruptedException e1) {
   // TODO Auto-generated catch block
   e1.printStackTrace();
  }
  
  
  WebElement element = driver.findElement(By.cssSelector("#MainDetails > div > div:nth-child(2) > div > div.k-grid-content > table"));
  List<WebElement> tdElements = element.findElements(By.tagName("td"));
  for(WebElement we : tdElements) {
   try {
    String tdvalue = we.getText();
    System.out.println(tdvalue);
    if (valueData.equals(tdvalue)) {
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
}