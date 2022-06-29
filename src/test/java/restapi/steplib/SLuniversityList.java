package restapi.steplib;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import restapi.restoperations.CommonFunctions;

public class SLuniversityList {
	
	CommonFunctions cfs;
	JsonPath response;
	
	private static final Logger logger = LoggerFactory.getLogger(SLuniversityList.class);
	
	@Step("Execute get on {0} method with {1} parameter ")
	public void runGetWithParam(String method, String params) throws IOException {
	   List<String> requstDetailsList =Serenity.sessionVariableCalled("requstDetailsList");
	   String baseUrl = cfs.getBaseUrlFromEndpoint(requstDetailsList.get(0));
	   response = cfs.getCall(baseUrl+method,params);
		
	}

	public void SearchFor(String universityName) {
	
		List<String> universityNameList = response.getList("name");
		logger.info("Number of universitis "+universityNameList.size());
		
		assertThat(universityNameList.contains(universityName)).isEqualTo(true);
		int searchIndex=universityNameList.indexOf(universityName);
		Serenity.recordReportData().withTitle("University Name").andContents(response.getString("["+searchIndex+"].name"));
		Serenity.recordReportData().withTitle("University domain").andContents(response.getString("["+searchIndex+"].domains[0]"));
		Serenity.recordReportData().withTitle("University web_page").andContents(response.getString("["+searchIndex+"].web_pages[0]"));
		
	
		
	}
	


}
