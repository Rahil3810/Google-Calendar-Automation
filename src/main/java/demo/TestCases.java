package demo;

import java.time.Duration;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    WebDriverWait waits;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.INFO);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Connect to the chrome-window running on debugging port
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01() throws InterruptedException{
        System.out.println("Start Test case: testCase01");
        driver.get("https://calendar.google.com/");
        Thread.sleep(3000);
        waits = new WebDriverWait(driver, Duration.ofSeconds(10));
        waits.until(ExpectedConditions.urlContains("calendar."));
        System.out.println("URL cointains Calendar");
        System.out.println("end Test case: testCase01");
    }
    public void testCase02() throws InterruptedException{
        System.out.println("Start TestCase 02");
        WebElement ViewBtn = driver.findElement(By.xpath("//button[@class='AeBiU-LgbsSe AeBiU-LgbsSe-OWXEXe-Bz112c-UbuQg AeBiU-LgbsSe-OWXEXe-dgl2Hf j9Fkxf I2n60c']"));
        ViewBtn.click();
        Thread.sleep(3000);

        WebElement monthBtn = driver.findElement(By.xpath("/html/body/div[22]/div/div/ul/li[3]"));
        // monthBtn.click();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", monthBtn);
        Thread.sleep(3000);

        
        waits = new WebDriverWait(driver, Duration.ofSeconds(10));
        waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gb\"]/div[2]/div[2]/div[3]/div/div/div/div[5]/div/div[1]/div[1]/div/button/span[5]")));


        WebElement date = driver.findElement(By.xpath("//div[@data-datekey='28052']"));
        date.click();
        Thread.sleep(2000);

        WebElement Title = driver.findElement(By.xpath("//input[@placeholder='Add title and time']"));
        Title.sendKeys("Crio INTV Task Automation");
        Thread.sleep(2000);

        WebElement Taskbtn = driver.findElement(By.xpath("//div[text()='Task']"));
        Taskbtn.click();
        Thread.sleep(2000);

        WebElement DescriptionSpace = driver.findElement(By.xpath("//textarea[@aria-label='Add description']"));
        DescriptionSpace.sendKeys("Crio INTV Calendar Task Automation");
        Thread.sleep(3000);

        WebElement SaveBtn = driver.findElement(By.xpath("//*[@id=\'yDmH0d\']/div/div/div[2]/span/div/div[1]/div[2]/div[2]/div[4]/button/span[4]"));
        // SaveBtn.click();
        
        executor.executeScript("arguments[0].click();", SaveBtn);
        waits.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Crio INTV Task Automation']")));

        System.out.println("End TestCase 02");

    }

    public void testCase03() throws InterruptedException{

        System.out.println("Start Test 03");
        Thread.sleep(2000);
        driver.navigate().refresh();
        Thread.sleep(5000);


        List<WebElement> tasks = driver.findElements(By.xpath("//div[@class='KF4T6b jKgTF QGRmIf']"));
        WebElement newTask = tasks.get(0);
        newTask.click();
        // WebElement task = driver.findElement(By.xpath("//div[@jslog='185338; track:impression,click,dblclick']"));
        // task.click();
        WebElement editBtn = driver.findElement(By.xpath("//button[@class='pYTkkf-Bz112c-LgbsSe pYTkkf-Bz112c-LgbsSe-OWXEXe-SfQLQb-suEOdc m2yD4b HfYfLe']"));
        editBtn.click();
        Thread.sleep(2000);
        WebElement descriptionArea = driver.findElement(By.xpath("//textarea[@placeholder='Add description']"));
        descriptionArea.clear();
        descriptionArea.sendKeys("Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application");
        WebElement saveBtn = driver.findElement(By.xpath("//button[@class='UywwFc-LgbsSe UywwFc-LgbsSe-OWXEXe-dgl2Hf']"));
        saveBtn.click();

        Thread.sleep(3000);
        driver.navigate().refresh();
        Thread.sleep(7000);
        List <WebElement> updatetaks = driver.findElements(By.xpath("//div[@class='KF4T6b jKgTF QGRmIf']"));
        WebElement updateTask = updatetaks.get(0);
        updateTask.click();
        // uptask.click();
        WebElement UpdatedDes = driver.findElement(By.xpath("//div[@class='toUqff vfzv']"));
        String Updated = UpdatedDes.getText();
        waits.until(ExpectedConditions.visibilityOf(UpdatedDes));
        System.out.println(Updated);


        WebElement exitBtn = driver.findElement(By.xpath("//button[@class='pYTkkf-Bz112c-LgbsSe pYTkkf-Bz112c-LgbsSe-OWXEXe-SfQLQb-suEOdc m2yD4b q7aeG']"));
        exitBtn.click();
        Thread.sleep(2000);

      
        System.out.println("End TestCase 03");

    }

    public void testCase04() throws InterruptedException{
        System.out.println("Start TestCase 04");
        List <WebElement> updatetasks = driver.findElements(By.xpath("//div[@class='KF4T6b jKgTF QGRmIf']"));
        WebElement updateTask = updatetasks.get(0);
        updateTask.click();
        WebElement Title = driver.findElement(By.xpath("//span[@id='rAECCd']"));
        String Titletxt = Title.getText();
        if(Titletxt.contains("Crio INTV Task Automation")){
            System.out.println(Titletxt);
        }else{
            System.out.println("Error while grtting Title");
        }
        
        waits = new WebDriverWait(driver, Duration.ofSeconds(10));
        waits.until(ExpectedConditions.visibilityOf(Title));
        

        WebElement deleteBtn = driver.findElement(By.xpath("//div[@jsaction='JIbuQc:qAGoT']"));
        deleteBtn.click();
       
        // WebElement DeletedTxt = driver.findElement(By.xpath("//div[text()='Event deleted']"));
        waits.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='VYTiVb']")));
        WebElement deleteTxt = driver.findElement(By.xpath("//div[@class='VYTiVb']"));
        String Deletetxt = deleteTxt.getText();
        if (Deletetxt.contains("Task deleted")){
            System.out.println("The Task was Deleted Successfully");
        }else{
            System.out.println("The Task was Not deleted");
        }

        Thread.sleep(5000);
        




        // WebElement task = driver.findElement(By.xpath("//*[@id=\"YPCqFe\"]/div/div/div/div[2]/div[1]/div[3]/div/div[1]/div/div/div"));
        // task.click();
        // Thread.sleep(2000);
        // WebElement deleteBtn = driver.findElement(By.xpath("//*[@id=\"yDmH0d\"]/div[1]/div/div[2]/span/div/div[1]/div/div/div[2]/div[2]/span/button/div"));
        // deleteBtn.click();
        // Thread.sleep(3000);
        // System.out.println("End TestCase 04");
        

    }

    


}
