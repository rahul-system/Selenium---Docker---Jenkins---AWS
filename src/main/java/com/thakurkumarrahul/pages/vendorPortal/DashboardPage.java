package com.thakurkumarrahul.pages.vendorPortal;

import com.thakurkumarrahul.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DashboardPage extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(DashboardPage.class);

    @FindBy(id= "monthly-earning")
    private WebElement monthlyEarning;

    @FindBy(id= "annual-earning")
    private WebElement annualEarning;

    @FindBy(id= "profit-margin")
    private WebElement profitMargin;

    @FindBy(id= "available-inventory")
    private WebElement availableInventory;

    @FindBy(css = "#dataTable_filter input")
    private WebElement searchInput;

    @FindBy(id = "dataTable_info")
    private WebElement searchResultCountElement;

    @FindBy(css = "img.img-profile")
    private WebElement userProfilePictureElement;

    @FindBy(linkText = "Logout")
    private WebElement logoutLink;

    @FindBy(css = "#logoutModal a")
    private WebElement logoutModal;


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.monthlyEarning));
        return this.monthlyEarning.isDisplayed();
    }

    public String getMonthlyEarning() {
        return this.monthlyEarning.getText();
    }

    public String getAnnualEarning() {
        return this.annualEarning.getText();
    }

    public String getProfitMargin() {
        return this.profitMargin.getText();
    }

    public String getAvailableInventory() {
        return this.availableInventory.getText();
    }

    public void searchOrderHistory(String keyword) {
        this.searchInput.clear();
        this.searchInput.sendKeys(keyword);
    }

    public int getSearchResultCount() {

//        Showing 1 to 8 of 8 entries (filtered from 99 total entries)
        String resultsText = this.searchResultCountElement.getText();
        String[] arr = resultsText.split(" ");
        int count = Integer.parseInt(arr[5]);
        log.info("Search result count: {}", count);
        return count;

    }

    public void logout() {
        this.userProfilePictureElement.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutLink));
        this.logoutLink.click();
        this.wait.until(ExpectedConditions.visibilityOf(this.logoutModal));
        this.logoutModal.click();
    }

}
