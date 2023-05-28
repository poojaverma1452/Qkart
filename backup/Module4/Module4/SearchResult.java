package QKART_SANITY_LOGIN.Module4;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResult {
    WebElement parentElement;
    WebElement sizeChart;

    public SearchResult(WebElement SearchResultElement) {
        this.parentElement = SearchResultElement;
    }

    /*
     * Return title of the parentElement denoting the card content section of a
     * search result
     */
    public String getTitleofResult() {
        String titleOfSearchResult = "";
        // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
        // Find the element containing the title (product name) of the search result and
        // assign the extract title text to titleOfSearchResult
        titleOfSearchResult = this.parentElement.getText();
        return titleOfSearchResult;
    }

    /*
     * Return Boolean denoting if the open size chart operation was successful
     */
    // TODO: CRIO_TASK_MODULE_XPATH - M2_3 Update locators to use Xpath
    public Boolean openSizechart() {
        try {

            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // Find the link of size chart in the parentElement and click on it
            if(this.sizeChart != null) {
                this.sizeChart.click();
                Thread.sleep(500);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Exception while opening Size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the close size chart operation was successful
     */
    public Boolean closeSizeChart(WebDriver driver) {
        try {
            Thread.sleep(2000);
            Actions action = new Actions(driver);

            // Clicking on "ESC" key closes the size chart modal
            action.sendKeys(Keys.ESCAPE);
            action.perform();
            Thread.sleep(2000);
            return true;
        } catch (Exception e) {
            System.out.println("Exception while closing the size chart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean based on if the size chart exists
     */
    public Boolean verifySizeChartExists(WebDriver driver) {
        Boolean status = false;
        try {
            /*
             * Check if the size chart element exists. If it exists, check if the text of
             * the element is "SIZE CHART". If the text "SIZE CHART" matches for the
             * element, set status = true , else set to false
             */
            this.sizeChart = driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[2]/div[1]/div/div[1]/button"));
            String verifySizechart = this.sizeChart.getText();
            if(verifySizechart != null) {
                return true;
            }
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if the table headers and body of the size chart matches the
     * expected values
     */
    public Boolean validateSizeChartContents(List<String> expectedTableHeaders, List<List<String>> expectedTableBody,
            WebDriver driver) {
        Boolean status = true;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            /*
                * Locate the table element when the size chart modal is open
                * 
                * Validate that the contents of expectedTableHeaders is present as the table
                * header in the same order
                * 
                * Validate that the contents of expectedTableBody are present in the table body
                * in the same order
                */
            List<WebElement> sizeChartHead = driver.findElements(By.xpath("//html/body/div[2]/div[3]/div/div/table/thead/tr/th"));
            for (int i=0; i<sizeChartHead.size(); i++) {
                if (!sizeChartHead.get(i).getText().equals(expectedTableHeaders.get(i)) ) {
                    return false;
                }
            }
            
            List<WebElement> sizeChartBody = driver.findElements(By.xpath("/html/body/div[2]/div[3]/div/div/table/tbody/tr"));
            for(int i=0; i<sizeChartBody.size(); i++) {
                int a = i;
                List<WebElement> sizeChartBodyTd = driver.findElements(By.xpath("/html/body/div[2]/div[3]/div/div/table/tbody/tr[" + ++a + "]" + "/td"));
                for(int j=0; j<sizeChartBodyTd.size(); j++) {
                    if (!sizeChartBodyTd.get(j).getText().equals(expectedTableBody.get(i).get(j)) ) {
                        return false;
                    }
                }
            }
            return status;
            

        } catch (Exception e) {
            System.out.println("Error while validating chart contents");
            return false;
        }
    }

    /*
     * Return Boolean based on if the Size drop down exists
     */
    public Boolean verifyExistenceofSizeDropdown(WebDriver driver) {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 04: MILESTONE 2
            // If the size dropdown exists and is displayed return true, else return false
            WebElement dropdown = driver.findElement(By.xpath("//*[@id='uncontrolled-native']"));
            if(dropdown != null) {
                return true;
            }
            return status;
        } catch (Exception e) {
            return status;
        }
    }
}