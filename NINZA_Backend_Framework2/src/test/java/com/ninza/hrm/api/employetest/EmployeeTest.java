package com.ninza.hrm.api.employetest;

import static io.restassured.RestAssured.given;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.hamcrest.Matchers;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
import com.ninza.hrm.api.baseclass.BaseAPIClass;
import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.api.pojoclass.EmployeePojo;
import com.ninza.hrm.api.pojoclass.ProjectPojo;
import com.ninza.hrm.constants.endpoints.IendPoint;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class EmployeeTest extends BaseAPIClass {
	String userName;
	String projectName;
	public String BASEURI;
		@Test
	public void addEmployeeTest() throws SQLException
	{
          
		// create an object to Pojo class
		BASEURI=flib.getDataFromPropetyFile("BASEUri");
				 projectName="Abb"+jLib.getRandomNumber();
				 userName="user"+jLib.getRandomNumber();
				// Api-1 ==> add a proejct in side server
				ProjectPojo pObj=new ProjectPojo(projectName, "Created", "jagadish", 0);
			 given()
			  .spec(specReqObj)
			  .body(pObj)
			  .when()
			  .post(IendPoint.ADD_Proj)
			 .then()
			 .spec(specRespObj)
			 .log().all();
			  
			
    // API-2 ==> add employee to same project
			EmployeePojo empObj=new EmployeePojo(projectName,"24/04/1983","abcd@gmail.com",userName,17,"9080908090",projectName,"ROLE_EMPLOYEE",userName);
			 given()
			  .spec(specReqObj)
			  .body(empObj)
			  .when()
			  .post(IendPoint.ADDEmp)
			 .then()
			 
			  .assertThat().statusCode(201)
			  .and()
			  .time(Matchers.lessThan(3000l))
			  .spec(specRespObj)
			  .log().all();
			 
			 // Verify Emp Name in Database
			
			 boolean flag = dbLib.executeSelectQuaryVerifyAndGetData("select * from employee", 5, userName);
	         org.testng.Assert.assertTrue(flag, "userName in DB is not verified");
           
	
	
	}

	@Test
	public void addEmployeeWithoutEmailTest() throws SQLException
	{
          
		// create an object to Pojo class
				Random random=new Random();
				int ranNum=random.nextInt(5000);
				String projectName="Abb"+ranNum;
				String userName="user"+ranNum;
				// Api-1 ==> add a proejct in side server
				ProjectPojo pObj=new ProjectPojo(projectName, "Created", "jagadish", 0);
			 given()
			  .spec(specReqObj)
			  .body(pObj)
			  .when()
			  .post("/addProject")
			 .then()
			 .spec(specRespObj)
			 .log().all();
			  
			
    // API-2 ==> add employee to same project
			EmployeePojo empObj=new EmployeePojo("Architect","24/04/1983","","user"+ranNum,17,"9080908090",projectName,"ROLE_EMPLOYEE",userName);
			 given()
			  .spec(specReqObj)
			  .body(empObj)
			  .when()
			  .post("/employees")
			 .then()
			
			  .assertThat().statusCode(500)
			  .spec(specRespObj)
			  .log().all();
		
			 
		
	
	
	}
	}
