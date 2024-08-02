package StepDefinition;

import Resources.DataBuilder;
import Resources.ExtentReporter;
import Resources.Utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {
        private Response response;
        private RequestSpecification requestSpec;
        DataBuilder data = new DataBuilder();
    ExtentReports extent;
    ExtentTest test;
    @Before
    public void setup() throws IOException {
        ExtentReporter.extentTest();
        extent = ExtentReporter.getExtent();
        test = ExtentReporter.getTest();
        requestSpec = given().log().all().spec(requestSpecification());
    }

    @After
    public void teardown() {
        extent.flush();
    }

    @Given("the API BaseUrl")
    public void the_api_base_url() throws IOException {
        requestSpec = given().log().all().spec(requestSpecification());
        test.info("Setting up the API Base URL");
    }

    @When("User Send a GET request to {string}")
    public void iSendAGETRequestTo(String endpoint) {
        response = given().spec(requestSpec).get(endpoint).then().log().all().extract().response();
        test.info("Sending GET request to " + endpoint);
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int statusCode) {
        assertEquals(response.getStatusCode(),statusCode);
        test.pass("Verifying the response status code: " + statusCode);
    }
    @Then("the response body should contain a list of users")
    public void theResponseBodyShouldContainAListOfUsers() {
        Assert.assertTrue(response.getBody().asString().contains("data"));
        test.pass("Response body contains a list of users");
    }

    @When("User Send a {string} request to {string} with name {string} and Email {string} as a {string} user")
    public void Send_a_post_request_to_with_name_and_email(String RequestMethod, String url, String name, String email, String status) {

        ApiResources APIResourcess = ApiResources.valueOf(RequestMethod);


        if (RequestMethod.equalsIgnoreCase("Put"))
            response = given().spec(requestSpec).body(data.buildUserPayload(name,email)).put(APIResourcess.getResource()).then().log().all().extract().response();
        else if (RequestMethod.equalsIgnoreCase("Post"))
            response =  given().spec(requestSpec).body(data.buildUserPayload(name,email)).post(APIResourcess.getResource()).then().log().all().extract().response();

        test.info("Sending " + RequestMethod + " request to " + url + " with name: " + name + " and email: " + email);


    }

    @Then("the response body should contain the username {string} and his email {string}")
    public void theResponseBodyShouldContainTheUserDetails(String name, String email) {

        String Name = getJsonPath(response,"name");
        String EMAIL =getJsonPath(response,"email");
        System.out.println(Name);
        System.out.println(EMAIL);
        Assert.assertEquals(Name,name);
        Assert.assertEquals(EMAIL,email);

        test.pass("Verified the response body contains the username: " + name + " and email: " + email);
    }

    @Then("{string} of user and his appropriate {string} should be shown")
    public void theEmailOfUserWithIdShouldBe(String email, String id) {

        String ID = getJsonPath(response,"data.id");
        String EMAIL =getJsonPath(response,"data.email");
        System.out.println(ID);
        System.out.println(EMAIL);
        Assert.assertEquals(ID,id);
        Assert.assertEquals(EMAIL,email);

        test.pass("Verified the response body contains the user ID: " + id + " and email: " + email);

    }
    @When("User Send a GET request to {string}{string}")
    public void Send_a_get_request_to(String endpoint, String id) {
        response = given().spec(requestSpec).pathParam("id",id).get("users/{id}").then().log().all().extract().response();
        test.info("Sending GET request to " + endpoint + " with ID: " + id);
    }

     @When("User Send a {string} request to {string}")
     public void Send_a_request_to(String RequestMethod, String string2) {

       ApiResources APIResources = ApiResources.valueOf(RequestMethod);
       if(RequestMethod.equalsIgnoreCase("Get"))
           response =  given().spec(requestSpec).get(APIResources.getResource()).then().log().all().extract().response();
       else if (RequestMethod.equalsIgnoreCase("Delete"))
           response =  given().spec(requestSpec).delete(APIResources.getResource()).then().log().all().extract().response();

       test.info("Sending " + RequestMethod + " request to " + string2);
    }

    @Then("the response body should be empty")
    public void theResponseBodyShouldBeEmpty() {
        Assert.assertTrue(response.getBody().asString().isEmpty());
        test.pass("Verified the response body is empty");
    }



}

