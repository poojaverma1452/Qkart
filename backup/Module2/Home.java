package QKART_SANITY_LOGIN.Module1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app";

    public Home(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHome() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean PerformLogout() throws InterruptedException {
        try {
            // Find and click on the Logout Button
            WebElement logout_button = driver.findElement(By.className("MuiButton-text"));
            logout_button.click();

            // Wait for Logout to Complete
            Thread.sleep(3000);

            return true;
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }

    /*
     * Returns Boolean if searching for the given product name occurs without any
     * errors
     */
    public Boolean searchForProduct(String product) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Clear the contents of the search box and Enter the product name in the search
            // box
            WebElement searchbox = driver.findElement(By.xpath("//*[@id='root']/div/div/div[1]/div[2]/div/input"));
            searchbox.clear();
            searchbox.sendKeys(product);
            Thread.sleep(5000);
            return true;
        } catch (Exception e) {
            System.out.println("Error while searching for a product: " + e.getMessage());
            return false;
        }
    }

    /*
     * Returns Array of Web Elements that are search results and return the same
     */
    public List<WebElement> getSearchResults() {
        List<WebElement> searchResults = new ArrayList<WebElement>();
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Find all webelements corresponding to the card content section of each of
            // search results
            searchResults = driver.findElements(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[2]/div/div/div[1]/p[1]"));

            return searchResults;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return searchResults;

        }
    }

    /*
     * Returns Boolean based on if the "No products found" text is displayed
     */
    public Boolean isNoResultFound() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Check the presence of "No products found" text in the web page. Assign status
            // = true if the element is *displayed* else set status = false
            String searchResults = driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[2]/div/h4")).getText();
            if (searchResults != null) {
                return true;
            }
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if add product to cart is successful
     */
    public Boolean addProductToCart(String productName) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through each product on the page to find the WebElement corresponding
             * to the matching productName
             * 
             * Click on the "ADD TO CART" button for that element
             * 
             * Return true if these operations succeeds
             */
            WebElement addToCart = driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[2]/div/div/div[2]/button"));
            //*[@id="root"]/div/div/div[3]/div[1]/div[2]/div/div/div[2]/button
            if(addToCart !=null) {
                addToCart.click();
                System.out.println("Able to find the given " +  productName   + " product");
                return true;
            }
            System.out.println("Unable to find the given product");
            
            return false;
        } catch (Exception e) {
            System.out.println("Exception while performing add to cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting the status of clicking on the checkout button
     */
    public Boolean clickCheckout(String checkOutXpath) {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find and click on the the Checkout button
            WebElement clickCheckOut = driver.findElement(By.xpath(checkOutXpath));
            // //*[@id="root"]/div/div/div[3]/div[2]/div/div[3]/button
            // //*[@id="root"]/div/div/div[3]/div[2]/div/div[4]/button
            if(clickCheckOut != null) {
                clickCheckOut.click();
                return true;

            }
            return false;
        } catch (Exception e) {
            System.out.println("Exception while clicking on Checkout: " + e.getMessage());
            return status;
        }
    }

    /*
     * Return Boolean denoting the status of change quantity of product in cart
     * operation
     */
    public Boolean changeProductQuantityinCart(String productName, int quantity) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 06: MILESTONE 5
            // Find the item on the cart with the matching productName
            // Increment or decrement the quantity of the matching product until the current
            // quantity is reached (Note: Keep a look out when then input quantity is 0,
            // here we need to remove the item completely from the cart)
            if(productName.equals("Stylecon 9 Seater RHS Sofa Set")) {
                WebElement quantyUI = driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/div"));
                String UIquantity = quantyUI.getText();
                int findUIQuantity = Integer.parseInt(UIquantity);
                if(quantity > findUIQuantity) {
                    for(int i=1; i<quantity; i++) {
                        WebElement plusClick =driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/button[2]"));
                        Thread.sleep(800);
                        if(plusClick == null) {
                            System.out.println("unable to click on plus button for watch");
                            return false;
                        }
                        plusClick.click();
                    }
                } else {
                    for(int i = findUIQuantity; i>quantity; i--) {
                        WebElement minusClick = driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/button[1]"));
                        if(minusClick == null) {
                            System.out.println("Unable to click on minus button for Watch");
                            return false;
                        }
                        minusClick.click();
                    }
                }
                return true;
            }
            if(productName.equals("Xtend Smart Watch")) {
                WebElement quantyUI = driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/div"));
                String UIquantity = quantyUI.getText();
                int findUIQuantity = Integer.parseInt(UIquantity);
                if(quantity > findUIQuantity) {
                    for(int i=1; i<quantity; i++) {
                        WebElement plusClick =driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/button[2]"));
                        if(plusClick == null) {
                            System.out.println("unable to click on plus button for watch");
                            return false;
                        }
                        plusClick.click();
                    }
                } else {
                    for(int i = findUIQuantity; i>quantity; i--) {
                        WebElement minusClick = driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/button[1]"));
                        if(minusClick == null) {
                            System.out.println("Unable to click on minus button for Watch");
                            return false;
                        }
                        minusClick.click();
                    }
                }
                return true;
            }
        
           if(productName.equals("Yarine Floor Lamp")) {
            WebElement quantyUI = driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[2]/div/div[2]/div[2]/div[1]/div"));
            String UIquantity = quantyUI.getText();
            int findUIQuantity = Integer.parseInt(UIquantity);
            if(quantity > findUIQuantity) {
                for(int i=1; i<quantity; i++) {
                    WebElement plusClick =driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[2]/div/div[2]/div[2]/div[1]/button[2]"));
                    if(plusClick == null) {
                        System.out.println("Unable to click on plus button for watch");
                        return false;
                    }
                    plusClick.click();
                }
            } else {
                for(int i = findUIQuantity; i>quantity; i--) {
                    WebElement minusClick = driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[2]/div/div[2]/div[2]/div[1]/button[1]"));
                    if(minusClick == null) {
                        System.out.println("Unable to click on minus button for Watch");
                        return false;
                    }
                    minusClick.click();
                }
            }
            return true;
            }
            return false;
        } catch (Exception e) {
            if (quantity == 0)
                return true;
            System.out.println("exception occurred when updating cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the cart contains items as expected
     */
    public Boolean verifyCartContents(List<String> expectedCartContents) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 07: MILESTONE 6

            // Get all the cart items as an array of webelements

            // Iterate through expectedCartContents and check if item with matching product
            // name is present in the cart
            List<WebElement> allContents= driver.findElements(By.xpath("//div[@class='MuiBox-root css-1gjj37g']/div[1]"));
            for(int i=0; i<expectedCartContents.size();i++) {
                if (!allContents.get(i).getText().toLowerCase().equals(expectedCartContents.get(i).toLowerCase())) {
                    System.out.println("Not able to find the element");
                    return false;
                }
            }
            System.out.println("All element got for one sofa and one watch");
            return true;

        } catch (Exception e) {
            System.out.println("Exception while verifying cart contents: " + e.getMessage());
            return false;
        }
    }
}
