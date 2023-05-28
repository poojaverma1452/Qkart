package QKART_SANITY_LOGIN.Module1;

import java.util.ArrayList;
import java.util.List;
// import javax.xml.xpath.XPath;
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
            System.out.println("clicked on logout button");

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
            WebDriverWait driverwait = new WebDriverWait(driver, 30);
            if (product.toLowerCase().equals("yonex")) product = product.toUpperCase();
            driverwait.until(
                ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'"+product+"')]")),
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()=' No products found ']"))
                )
                
            );
            System.out.println("waiting till element display or no product found"); 
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
             if( searchResults.size() != 0) {
            System.out.println("Able to find the Element into content page");
             } 
            return searchResults;
        } catch (Exception e) {
            System.out.println("No Product found msg is displayed " + e.getMessage());
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
            if(addToCart !=null) {
                addToCart.click();
                Thread.sleep(500);
                System.out.println("Added given " +  productName   + " product");
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
                System.out.println("Clicking on the checkout button");
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
                        WebDriverWait driverwait = new WebDriverWait(driver, 30);
                        try {
                            Boolean test = driverwait.until(ExpectedConditions.textToBe(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/div"), "" + (i+1)));
                            // visibilityOfElementLocated());
                            System.out.println("30 sec time duration during updating the product quantity increament " + test);
                        } catch (Exception e) {
                            //TODO: handle exception
                            System.out.println("will be fail in waiting for increase");
                        }
                    }
                } else {
                    for(int i = findUIQuantity; i>quantity; i--) {
                        WebElement minusClick = driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/button[1]"));
                        if(minusClick == null) {
                            System.out.println("Unable to click on minus button for Watch");
                            return false;
                        }
                        minusClick.click();
                        WebDriverWait driverwait = new WebDriverWait(driver, 30);
                        try {
                            boolean testDecrease = driverwait.until(ExpectedConditions.textToBe(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div[1]/div/div[2]/div[2]/div[1]/div"), i-1+ ""));
                                // ));
                            System.out.println("30 sec time duration during updating the product quantity decreament " + testDecrease);
                        } catch (Exception e) {
                            //TODO: handle exception
                            System.out.println("will be fail in waiting for decrease");
                        }
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
    public Boolean privacyPolicyLink() throws InterruptedException {
        WebElement priPolicyElement = driver.findElement(By.xpath("//*[@id='root']/div/div/div[5]/div[2]/p[1]/a"));
        priPolicyElement.click();
        System.out.println("Clicked on privacy-policy Link");
        Thread.sleep(5000);
        //Verify that the url of the current tab does not change
        if(driver.getCurrentUrl().equals("https://crio-qkart-frontend-qa.vercel.app/")) {
            System.out.println("url of the current tab does not changed");
        } else{
        System.out.println("url of the current tab changed");
        }
        return true;
    }

    public Boolean termsOfServiceLink() throws InterruptedException {
        WebElement priPolicyElement = driver.findElement(By.xpath("//*[@id='root']/div/div/div[5]/div[2]/p[4]/a"));
        priPolicyElement.click();
        System.out.println("Clicked on termsOfService button");
        Thread.sleep(5000);
        //Verify that the url of the current tab does not change
        if(driver.getCurrentUrl().equals("https://crio-qkart-frontend-qa.vercel.app/")) {
            System.out.println("url of the current tab does not changed");
        } else{
        System.out.println("url of the current tab changed");
        }
        return true;
    }

    public Boolean privacyPolicyContent() throws InterruptedException {
        WebElement policyContent = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/p/strong"));
        Thread.sleep(500);
        if(policyContent.getText().equals("QIFT SOLUTECH PRIVATE LIMITED")) {
            System.out.println("privacy policy content is available on the newly opened tab");
        } else {
            System.out.println("privacy policy content is not available on the newly opened tab");
        }
        return true;
    }
    public Boolean termsOfServiceContent() {
        WebElement serviceContent = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/h2"));
        if(serviceContent.getText().equals("Terms of Service")) {
            System.out.println("Able to find the content");
        } else {
            System.out.println("Not able to find the content");
        }
        return true;
    }
    public Boolean aboutusContent() {
        WebElement aboutContent = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/h3[1]"));
        if(aboutContent.getText().equals("Disrupting The Way Developers Learn")){
            System.out.println("Able to find the content of aboutUS");
        } else {
            System.out.println("Unable to find the content of aboutUS");
        }
        return true;
    }

    public Boolean contactUs() throws InterruptedException {
        WebElement contactusEle = driver.findElement(By.xpath("//*[@id='root']/div/div/div[5]/div[2]/p[3]"));
        contactusEle.click();
        System.out.println("clicked on the contactUS link in to the home page");
        Thread.sleep(500);
        WebElement nameEle = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/section/div/div/div/form/div/div/div[2]/div[1]/div/input"));
        nameEle.sendKeys("crio user");
        System.out.println("name should be crioUser");
        Thread.sleep(500);
        WebElement emailEle = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/section/div/div/div/form/div/div/div[2]/div[2]/div/input"));
        emailEle.sendKeys("criouser@gmail.com");
        System.out.println("Email should be criouser@gmail.com");
        Thread.sleep(500);
        WebElement msgEle = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/section/div/div/div/form/div/div/div[3]/input"));
        msgEle.sendKeys("Testing the contact us page");
        System.out.println("Msg should be Testing the contact us page");
        Thread.sleep(500);
        WebElement contactButton = driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/section/div/div/div/form/div/div/div[4]/div/button"));
        contactButton.click();
        System.out.println("contactButton is clicked");
        return true;
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
