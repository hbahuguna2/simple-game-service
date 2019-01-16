package fun;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GameTests {
	
WebDriver driver;
	
	@BeforeMethod
	public void preSetup() {
		
		System.setProperty("webdriver.chrome.driver", "E:\\Automation\\WebDriver\\chromedriver.exe"); //Change the driver location.
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/game");
		
	}
	
	@Test
	public void textVerification() {
		
		
		//Task#1.1: Find out the text that is displayed when the game endpoint is accessed without a query parameter.		
		String actualText = driver.findElement(By.xpath("/html/body/pre")).getText();		
		actualText = actualText.replaceAll("\"id", "").replaceAll("[0-9,\"-]", "").replaceAll(":text:","").replaceAll("[{}]","");
		
		//Display the text
		System.out.println("Displayed Text without query parameter is: " +actualText);
		
		//#Task1.2: verify that this text is displayed when the game endpoint is accessed without a query parameter.
		Assert.assertEquals(actualText, "Playing Sudoku is fun!", "Text does not match");
		
	}
	
	@Test
	public void gameID() {

		//Task#2.1: Find out the id after hitting game endpoint 7 times.
		//Task#2.2: If id increases in a certain pattern, write a test to verify that the game endpoint follows the pattern you have determined.
		
		    int nextNumber = 1; 		 
	        for (int i = 1; i <= 7; ++i)
	        {
	           
	            /* On each iteration, we are assigning second number
	             * to the first number and assigning the sum of last two
	             * numbers to the second number i.e. 0+1, 1+0 , 1+1, 1+2, 3+2 and so on.....
	             */
	        	String getId = driver.findElement(By.xpath("/html/body/pre")).getText();
	        	getId = getId.replaceAll("[^0-9]", ""); 	        	
	     	    int getId1 = Integer.valueOf(getId);	     	     
	     		
	            int sum = getId1 + nextNumber;
	            getId1 = nextNumber;
	            nextNumber = sum;
	           
	            //Print the ID after hitting game endpoint 7 times .
	            if (i == 7)
	             {
	               System.out.print("Seventh ID is: "+ getId);	              
	               break;
	             }
	           
	            driver.navigate().refresh();
	          
	        }
	}
	
	@Test
	public void newGameName() {
		
		driver.navigate().to("http://localhost:8080/game?name=cricket");          
          
		//Task#3.1:Find out the text that is displayed when game endpoint is accessed with a name query parameter.
          
		String textWithName = driver.findElement(By.xpath("/html/body/pre")).getText();
		textWithName = textWithName.replaceAll("\"id", "").replaceAll("[0-9,\"-]", "").replaceAll(":text:","").replaceAll("[{}]","");
		
		//Print the text that is displayed.
		System.out.println("Displayed Text with query parameter is: " +textWithName);
		
		//verify that text is displayed when the game endpoint is accessed with the name query parameter.
		Assert.assertEquals(textWithName, "Playing cricket is fun!","Text does not match with name query parameter");
		
	}
	
	@AfterMethod
	public void closeAllBrowsers() {
		driver.quit();
	}

}