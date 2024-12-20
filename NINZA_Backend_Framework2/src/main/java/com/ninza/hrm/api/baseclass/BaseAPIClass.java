package com.ninza.hrm.api.baseclass;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericutility.DataBaseUtility;
import com.ninza.hrm.api.genericutility.FileUtility;
import com.ninza.hrm.api.genericutility.JavaUtility;
import com.ninza.hrm.api.pojoclass.ProjectPojo;

import static io.restassured.RestAssured.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseAPIClass {

	public JavaUtility jLib=new JavaUtility(); 
	public	FileUtility flib=new FileUtility();
	public ProjectPojo pObj;
	
	public static RequestSpecification specReqObj;
	
	public 	DataBaseUtility dbLib=new DataBaseUtility();
  public  static ResponseSpecification specRespObj;
	@BeforeSuite
	public void configBS()
	{
		dbLib.connectToDB();
		System.out.println("=================Connect To DB======================");
		RequestSpecBuilder builder=new RequestSpecBuilder();
		builder.setContentType(ContentType.JSON);
		// builder.setAuth(basic("username", "password"));
		// builder.addHeader("", "");
		builder.setBaseUri(flib.getDataFromPropetyFile("BASEUri"));
		specReqObj = builder.build();
		 
		 ResponseSpecBuilder resBuilder=new ResponseSpecBuilder();
		 resBuilder.expectContentType(ContentType.JSON);
		 specRespObj = resBuilder.build();
	}
	@AfterSuite
	public void configAs() throws SQLException
	{
		 dbLib.closeDbConnection();
		 System.out.println("=================DisConnect To DB======================");
	}
}
