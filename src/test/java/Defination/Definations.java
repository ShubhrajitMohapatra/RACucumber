package Defination;

import Utility.JasonToString;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.IOException;

public class Definations {

    public static String BaseURI = "https://simple-books-api.glitch.me";
    public static String AccessKey = "";

    public static String AuthenticateBody;
    public static String OrderBody;
    RequestSpecification requestSpecification;


    Response resp;

    @Given("Authenticate base url along with the body")
    public void authenticate_base_url_along_with_the_body() throws IOException {
        // I provide the body in the String Format

        AuthenticateBody = JasonToString.generatePayload("Authenticate.json");
    }
    @When("Execute the authenticate {string} which provides accessToken")
    public void execute_the_authenticate_which_provides_access_token(String resourcePath) {
        // Write code here that turns the phrase above into concrete actions

        requestSpecification = RestAssured.given()
                .body(AuthenticateBody);

        requestSpecification.contentType(ContentType.JSON);
        resp = requestSpecification.post(BaseURI+resourcePath);
    }
    @Then("Verify the status code is {int}.")
    public void verify_the_status_code_is(int expectedStatus) {
        // Write code here that turns the phrase above into concrete actions

        System.out.println(resp.getStatusCode());
        Assert.assertEquals(expectedStatus,resp.getStatusCode());


    }
    @Then("Verify the accessToken in the response")
    public void verify_the_access_token_in_the_response() {
        System.out.println(resp.asPrettyString());
        JsonPath js = new JsonPath(resp.asString());
        AccessKey = js.get("accessToken");

        System.out.println(AccessKey);
    }


    // Scenario - 2 To create Order

    @Given("Create order by using accessKey")
    public void create_order_by_using_access_key() throws IOException {

      OrderBody = JasonToString.generatePayload("Order.json");

    }
    @When("Execute the order  {string} which provides accessToken")
    public void execute_the_order_which_provides_access_token(String pathToCreate) {
        requestSpecification = RestAssured.given().body(AuthenticateBody);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.header("Authorization","Bearer"+AccessKey);
        resp = requestSpecification.post(BaseURI+pathToCreate);

    }
    @Then("Verify the status code for order is {int}.")
    public void verify_the_status_code_for_order_is(int expCode) {
        System.out.println(resp.getStatusCode());
        Assert.assertEquals(expCode,resp.getStatusCode());
    }
    @Then("Verify the status created as True")
    public void verify_the_status_created_as_true() {


        System.out.println(resp.asPrettyString());
        JsonPath js = new JsonPath(resp.asString());
        String Ctreated = js.get("created");
        Assert.assertEquals("true",Ctreated);

    }
    @Then("Verify the order id")
    public void verify_the_order_id() {
        System.out.println(resp.asPrettyString());
        JsonPath js = new JsonPath(resp.asString());
        String orderId = js.get("orderId");
        Assert.assertNotNull(orderId);
    }

}
