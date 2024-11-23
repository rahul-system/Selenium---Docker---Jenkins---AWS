package com.thakurkumarrahul.test.flightreservation;

import com.thakurkumarrahul.pages.flightreservation.*;
import com.thakurkumarrahul.test.AbstractTest;
import com.thakurkumarrahul.test.flightreservation.model.FlightReservationTestData;
import com.thakurkumarrahul.util.Config;
import com.thakurkumarrahul.util.Constants;
import com.thakurkumarrahul.util.JSONUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FlightReservationTest extends AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(FlightReservationTest.class);

    private FlightReservationTestData testData;


    @BeforeTest
    @Parameters("testDataPath")
    public void setParameters(String testDataPath) throws Exception {
        this.testData = JSONUtil.getTestData(testDataPath, FlightReservationTestData.class);
    }

    @Test
    public void userRegistrationTest(){
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo(Config.get(Constants.FLIGHT_RESERVATION_URL));
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails(testData.firstName(), testData.lastName());
        registrationPage.enterUserCredentials(testData.email(), testData.password());
        registrationPage.enterAddress(testData.street(), testData.city(), testData.zip());
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getFirstName(),testData.firstName());
        registrationConfirmationPage.goToFlightSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightSearchTest() {
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());

        flightSearchPage.selectPassengers(testData.passengerCount());
        flightSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightSelectionTest() {
        SelectFlights selectFlights = new SelectFlights(driver);
        Assert.assertTrue(selectFlights.isAt());

        selectFlights.selectFlights();
        selectFlights.confirmFlights();
    }

    @Test(dependsOnMethods = "flightSelectionTest")
    public void flightConfirmationTest() {
        FlightsConfirmation flightsConfirmation = new FlightsConfirmation(driver);
        Assert.assertTrue(flightsConfirmation.isAt());

        Assert.assertEquals(flightsConfirmation.getPrice(),testData.expectedPrice());
    }

}
