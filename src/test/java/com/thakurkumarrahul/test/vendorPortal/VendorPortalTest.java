package com.thakurkumarrahul.test.vendorPortal;

import com.thakurkumarrahul.pages.vendorPortal.DashboardPage;
import com.thakurkumarrahul.pages.vendorPortal.LoginPage;
import com.thakurkumarrahul.test.AbstractTest;
import com.thakurkumarrahul.test.vendorPortal.model.VendorPortalTestData;
import com.thakurkumarrahul.util.Config;
import com.thakurkumarrahul.util.Constants;
import com.thakurkumarrahul.util.JSONUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {


    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")
    public void setPageObjects(String testDataPath) throws Exception {
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JSONUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void logintest(){
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(),testData.password());
    }

    @Test(dependsOnMethods = "logintest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());

        //finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(),testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(),testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(),testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(),testData.availableInventory());

        //order history search
        dashboardPage.searchOrderHistory(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultCount(),testData.searchResultsCount());

    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){

        //logout
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }


}
