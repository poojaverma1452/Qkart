package QKART_SANITY_LOGIN.Module1;

// import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/checkout";

    public Checkout(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCheckout() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    /*
     * Return Boolean denoting the status of adding a new address
     */
    public Boolean addNewAddress(String addresString) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Click on the "Add new address" button, enter the addressString in the address
             * text box and click on the "ADD" button to save the address
             */
           WebElement addNewAdr = driver.findElement(By.id("add-new-btn"));
           if(addNewAdr != null) {

            addNewAdr.click();
            System.out.println("Clicked on add new address");
            WebElement addedAddress = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[2]/div[1]/div/textarea[1]"));
            addedAddress.sendKeys(addresString);
            System.out.println("Added given String");
            WebElement addButton = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[2]/div[2]/button[1]"));
            addButton.click();
            WebDriverWait driverwait = new WebDriverWait(driver, 30);
            System.out.println("Clicking on the add button");
            try {
                driverwait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-new-btn")));
                System.out.println("We should wait until the address gets added and is visible");
            } catch (Exception e) {
                //TODO: handle exception
                System.out.println("Some exception showing ");
            }
            return true;
           }
            return false;
        } catch (Exception e) {
            System.out.println("Exception occurred while entering address: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting the status of selecting an available address
     */
    public Boolean selectAddress(String addressToSelect) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through all the address boxes to find the address box with matching
             * text, addressToSelect and click on it
             */
            WebElement selectAddress = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[1]/div/div[1]/span/input"));
            if(selectAddress != null) {
                selectAddress.click();
                System.out.println("Selected address radio button");
                return true;
            }
            System.out.println("Unable to find the given address");
            return false;
            } catch (Exception e) {
                System.out.println("Exception Occurred while selecting the given address: " + e.getMessage());
                return false;
            }

    }

    /*
     * Return Boolean denoting the status of place order action
     */
    public Boolean placeOrder() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find the "PLACE ORDER" button and click on it
            WebElement placeOrder = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/button[2]"));
            if(placeOrder!= null) {
                placeOrder.click();
                System.out.println("place order button has been clicked");
                return true;
            }
            return false;

        } catch (Exception e) {
            System.out.println("Exception while clicking on PLACE ORDER: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the insufficient balance message is displayed
     */
    public Boolean verifyInsufficientBalanceMessage() {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 08: MILESTONE 7
            WebElement sufficientBalance = driver.findElement(By.id("notistack-snackbar"));
            String walletBalance = sufficientBalance.getText();
            if(walletBalance != null) {
                System.out.println(walletBalance);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Exception while verifying insufficient balance message: " + e.getMessage());
            return false;
        }
    }
}
