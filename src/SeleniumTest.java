
import java.util.regex.Pattern;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.gecko.driver", "C:\\geckodriver\\geckodriver.exe");
    driver = new FirefoxDriver();
    baseUrl = "https://www.katalon.com/";
    //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitledTestCase() throws Exception {
	 
   /* driver.get("https://psych.liebes.top/st");
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).clear();
    driver.findElement(By.id("username")).sendKeys("3015218114");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).clear();
    driver.findElement(By.id("password")).sendKeys("218114");
    driver.findElement(By.id("submitButton")).click();
    assertEquals("https://github.com/hdychi", driver.findElement(By.xpath("//p")).getText());*/
	List<String[]> data = POIUtil.readExcel("C:\\ื๗าต\\input.xlsx");
	for(int i = 0;i < data.size();i++){
		driver.get("https://psych.liebes.top/st");
		String user = data.get(i)[0];
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys(user);
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys(user.substring(user.length() - 6,user.length()));
	    driver.findElement(By.id("submitButton")).click();
	    String res =  driver.findElement(By.xpath("//p")).getText();
	    if(res.endsWith("/")){
	    	res = res.substring(0, res.length()-1);
	    }
	    if(data.get(i)[1].endsWith("/")){
	    	data.get(i)[1] = data.get(i)[1].substring(0, data.get(i)[1].length()-1);
	    }
	    data.get(i)[1] = data.get(i)[1].trim();
	    if(!res.equals("Sign in to get your information")){
	    	assertEquals(data.get(i)[1],res);
	    }
	    
	}
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
