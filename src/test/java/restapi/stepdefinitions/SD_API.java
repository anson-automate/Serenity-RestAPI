package restapi.stepdefinitions;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;
import restapi.steplib.SLuniversityList;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class SD_API {
	
	private static final Logger logger = LoggerFactory.getLogger(SD_API.class);
	
	
	@Steps
	SLuniversityList universitylist;
	
    @Given("user set below")
    public void user_set_below(DataTable requstInfo) {
    	
    	List<String> reqDetails = requstInfo.row(0);
    	Serenity.setSessionVariable("requstDetailsList").to(reqDetails);

    }
    
    @When("user run get call on {string} method for country parameter {string}")
    public void user_run_get_call_on_something_method_for_country_parameter_something(String method, String params) throws IOException {
    	universitylist.runGetWithParam( method,params);
    	
    }

    @Then("response code should be {int}")
    public void response_code_should_be(int reCode) throws Throwable {
    	
    	restAssuredThat(response -> response.statusCode(reCode));
    	//logger.info("Exp return code ---->>>> "+reCode); 
    }

    @And("user should see {string} in the search result")
    public void user_should_see_something_in_the_search_result(String universityName) {
    	universitylist.SearchFor(universityName);
    }

}
