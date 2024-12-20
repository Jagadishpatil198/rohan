package com.ninza.hrm.api.genericutility;

import java.util.ArrayList;

import com.jayway.jsonpath.JsonPath;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class JsonUtils {
	
	FileUtility fLib=new FileUtility();
	
	/**
	 * @author RAJU
	 * @param resp
	 * @param jsonXpath
	 * @return
	 */
	public String getDataOnJsonpath(Response resp, String jsonXpath) {
		ArrayList<Object> list = JsonPath.read(resp.asString(), jsonXpath);
		return list.get(0).toString();

	}
	/**
	 * get the xmldata from based on xml complex xpath
	 * @param resp
	 * @param xmlPath
	 * @return
	 */
	public String getDataOnXpathPath(Response resp,String xmlPath)
	{
		return resp.xmlPath().getString(xmlPath);
	}

	/**
	 * 
	 * @author JAGADISH
	 * @param resp
	 * @param jsonXpath
	 * @param expectedData
	 * @return
	 */
	public boolean verifyDataOnJsonXpath(Response resp, String jsonXpath, String expectedData) {
		ArrayList<Object> list = JsonPath.read(resp.asString(), jsonXpath);
		boolean flag = false;
		for (Object str : list) {
			if (str.equals(expectedData)) {
				System.out.println(expectedData + " is avilable==PASS");
				flag = true;
			}
			if (flag == false) {
				System.out.println(expectedData + " is not avilable==FAIL");
			}
		}
		return flag;
	}

	/**
	 * @author JAGADISH
	 * @param resp
	 * @param jsonPath
	 * @return
	 */
	public String getDataOnJsonPath(Response resp, String jsonPath) {
		return resp.jsonPath().getString(jsonPath);
	}
/**
 *  @author JAGADISH
 * @return
 */
	public String getAcessToken() {
		Response resp = given().formParam("client_id", fLib.getDataFromPropetyFile("ClientID"))
				.formParam("client_secret", fLib.getDataFromPropetyFile("ClientSecret"))
				.formParam("grant_type", fLib.getDataFromPropetyFile("grant_type")).when()
				.post(fLib.getDataFromPropetyFile("redirectURL"));
		resp.then().log().all();
		//capture token from response
		String token=resp.jsonPath().get("access_token");
		return token;
	}
}
