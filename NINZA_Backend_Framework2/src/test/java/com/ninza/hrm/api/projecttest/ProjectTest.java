package com.ninza.hrm.api.projecttest;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
import com.ninza.hrm.api.baseclass.BaseAPIClass;
import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.api.pojoclass.ProjectPojo;
import com.ninza.hrm.constants.endpoints.IendPoint;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
public class ProjectTest extends BaseAPIClass {
	public String BASEURI;
@Test
public void addSingleProjectWithCreated() throws SQLException
{
	// create an object to Pojo class
		        BASEURI=flib.getDataFromPropetyFile("BASEUri");
			String expSucMsg="Successfully Added";
			String projectName="Abb"+jLib.getRandomNumber();
			 pObj=new ProjectPojo(projectName, "Created", "jagadish", 0);
	// Verify the projectName in API Layer
			Response	 resp= given()
		  .spec(specReqObj)
		  .body(pObj)
		  .when()
		  .post(IendPoint.ADD_Proj);
	resp.then()
		  .assertThat().statusCode(201)
		  .assertThat().time(Matchers.lessThan(3000l))
		 .spec(specRespObj)
		  .log().all();
	String actMsg=resp.jsonPath().get("msg");
	org.testng.Assert.assertEquals(expSucMsg, actMsg);
	// Verify the projectName in DB layer
	
	   boolean flag = dbLib.executeSelectQuaryVerifyAndGetData("select * from project", 4, projectName);
		         org.testng.Assert.assertTrue(flag, "Project in DB is not verified");
      
}


@Test(dependsOnMethods = "addSingleProjectWithCreated")
public void createDuplicateProjectTest()
{
	given()
	 .spec(specReqObj)
	  .body(pObj)
	  .when()
	  .post(IendPoint.ADD_Proj)
	  .then()
	  .spec(specRespObj)
	  .assertThat().statusCode(409)
	  .log().all();
	  

}

}
