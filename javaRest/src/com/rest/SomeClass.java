package com.rest;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


@Path("/hello")
public class SomeClass {
	
	private String FILE_PATH = "D:\\IBA Application Form.pdf";

	private String message ="Hello, ";
	private String name ="Udgin";
			
			public SomeClass(){}
	@GET
	public String helloMethod(){
		return message + name;
	}
	@PUT
	public void nameMethod(String name){
		this.name = name;
	}
	
	@GET
	 @Path("/pdf")
	 @Produces("application/pdf")
	 public Response sayPdfHello() {
	  File file = new File(FILE_PATH);

	  ResponseBuilder responseBuilder = Response.ok((Object) file);
	//responseBuilder.type("application/pdf");
	    responseBuilder.header("Content-Disposition", "filename=test.pdf");
	  return responseBuilder.build();

	 }
	
	
}
