package com.thakurkumarrahul.pages.flightreservation;

import com.thakurkumarrahul.pages.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightsConfirmation extends AbstractPage {

    private static final Logger log = LoggerFactory.getLogger(FlightsConfirmation.class);

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement flightTotalPrice;

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    private WebElement flightConfirmationNo;

    public FlightsConfirmation(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.flightTotalPrice));
        return this.flightTotalPrice.isDisplayed();
    }

    public String getPrice() {
        String confirmationNo = this.flightConfirmationNo.getText();
        String confirmationPrice = this.flightTotalPrice.getText();
        log.info("Flight confirmation# : {}", confirmationNo);
        log.info("Flight Total Price# : {}", confirmationPrice);
        return confirmationPrice;
    }

}
