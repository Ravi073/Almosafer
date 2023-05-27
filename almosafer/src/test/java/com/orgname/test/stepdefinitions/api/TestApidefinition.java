package com.orgname.test.stepdefinitions.api;

import com.orgname.framework.api.ApiDriverFactory;
import cucumber.api.java8.En;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.ArrayList;

public class TestApidefinition extends ApiDriverFactory implements En {
    Response response =null;
    public TestApidefinition() {
        Given("^Hit the baseURL \"([^\"]*)\"$", (String baseURI) -> {
            RestAssured.baseURI = baseURI;
        });
        When("^run the GET method for the API endpoint \"([^\"]*)\"$", (String path) -> {
            response= getMethod(path,"");
            printOutput(response);
        });

        Then("^validate the base currency \"([^\"]*)\" and Equivalent \"([^\"]*)\" currencies are with \"([^\"]*)\" at the first\\.$", (String expectedBaseCurrency, String expectedCurrencyCount, String actualFirstCurrency) -> {
            String actualBaseCurrency = response.jsonPath().getString("base.symbol");
            Assert.assertEquals(expectedBaseCurrency, actualBaseCurrency);
            ArrayList<String> actualCurrencyCount= response.jsonPath().get("equivalent.symbol");
            Assert.assertEquals(Integer.parseInt(expectedCurrencyCount), actualCurrencyCount.size());
            String expectedFirstCurrency= response.jsonPath().get("equivalent.symbol[0]");
            Assert.assertEquals(expectedFirstCurrency, actualFirstCurrency);
        });

        When("^run the POST method for the API endpoint \"([^\"]*)\" for checkinDate \"([^\"]*)\" checkoutDate \"([^\"]*)\"$", (String path, String checkinDate, String checkoutDate) -> {
            String requestBody ="{\"searchCriteria\":[{\"lookupTypeId\":2,\"lookupId\":[9]}],\"" +
                    "checkIn\":\""+checkinDate+"\"," +
                    "\"checkOut\":\""+checkoutDate+"\",\"" +
                    "sortBy\":\"rank\",\"sortOrder\":\"DESC\",\"rankType\":\"dynamic\",\"pageNo\":1,\"pageSize\":10}";
            response= postMethod(path,requestBody);
            printOutput(response);
        });

        Then("^validate a hotel name \"([^\"]*)\" and min and max bathroom is \"([^\"]*)\" \"([^\"]*)\" in filter$", (String expHotelName, String expMinBathroom, String expMaxBathroom) -> {
            ArrayList<String> actHotelNames = response.path("properties.nameEn");
            int actMinBathroom = response.path("filters.bathRoomCountFilter.minimumCount");
            int actMaxBathroom = response.path("filters.bathRoomCountFilter.maximumCount");
            String actHotelName =null;
            for (String hotelName : actHotelNames) {
                actHotelName = hotelName;
                if (actHotelName.equalsIgnoreCase(expHotelName))
                    break;
            }
            Assert.assertEquals(expHotelName,actHotelName);
            Assert.assertEquals(Integer.parseInt(expMinBathroom),actMinBathroom);
            Assert.assertEquals(Integer.parseInt(expMaxBathroom),actMaxBathroom);
        });
    }
}
