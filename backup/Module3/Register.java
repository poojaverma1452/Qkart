package QKART_SANITY_LOGIN.Module1;

import java.sql.Timestamp;
// import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
// import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Register {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/register";
    public String lastGeneratedUsername = "";

    public Register(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToRegisterPage() {
        if (!driver.getCurrentUrl().equals(this.url)) {
            driver.get(this.url);
        }
    }

    public Boolean registerUser(String Username, String Password, Boolean makeUsernameDynamic) throws InterruptedException {
        // Find the Username Text Box
        WebElement username_txt_box = this.driver.findElement(By.id("username"));

        // Get time stamp for generating a unique username
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String test_data_username;
        if (makeUsernameDynamic)
            // Concatenate the timestamp to string to form unique timestamp
            test_data_username = Username + "_" + String.valueOf(timestamp.getTime());
        else
             test_data_username = Username;

        // Type the generated username in the username field
        username_txt_box.sendKeys(test_data_username);

        // Find the password Text Box
        WebElement password_txt_box = this.driver.findElement(By.id("password"));
        String test_data_password = Password;

        // Enter the Password value
        password_txt_box.sendKeys(test_data_password);

        // Find the Confirm password text box
        WebElement confirm_password_txt_box;
         confirm_password_txt_box = this.driver.findElement(By.id("confirmPassword"));

        // Enter the Confirm Password Value
        confirm_password_txt_box.sendKeys(test_data_password);

        // Find the register now button
        WebElement register_now_button = this.driver.findElement(By.xpath("//*[@id='root']/div[1]/div/div[2]/div/button"));
        // Click the register now button
        register_now_button.click();
        System.out.println("Clicked on the register button");
        WebDriverWait driverwait = new WebDriverWait(driver, 30);
        try {
            Boolean tillUrlLoginWait = driverwait.until(ExpectedConditions.urlToBe("https://crio-qkart-frontend-qa.vercel.app/login"));
            if(tillUrlLoginWait){
                System.out.println("After clicking on register button , will wait for some time till login page open");
            } else {
                System.out.println("Login page will not be open");
            } 
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("Exception handling for login url");
        }

        this.lastGeneratedUsername = test_data_username;
        Thread.sleep(10000);
        return this.driver.getCurrentUrl().endsWith("/login");
    }
}
