package restapi.restoperations;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.google.common.base.Charsets;

import io.restassured.path.json.JsonPath;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.rest.SerenityRest;

public class CommonFunctions extends PageObject {

	public void setRestBaseUrl(String baseUrl) {
		SerenityRest.setDefaultBasePath(baseUrl);
		
	}

	@SuppressWarnings("static-access")
	public String getBaseUrlFromEndpoint(String baseUrlKey) throws IOException {
		String baseUrlValue = null;
		String filePath = "src/test/resources/endPoint/endpoints.json";
		String filePathContent = fileData(filePath);
		JsonPath jp = new JsonPath(filePathContent);
		baseUrlValue=jp.get(baseUrlKey);
		return baseUrlValue;
		// TODO Auto-generated method stub
		
	}
	
	public String fileData(String filePath) throws IOException {
	  
	    File file = new File(filePath);
	    String content = com.google.common.io.Files.asCharSource(file, Charsets.UTF_8).read();
	    return content;
		
	}

	public JsonPath getCall(String method, String params) {
		return SerenityRest.given().queryParam("country", params)
		.when()
		.get(method)
		.andReturn()
		.body()    
		.jsonPath();	
		
	}

}
