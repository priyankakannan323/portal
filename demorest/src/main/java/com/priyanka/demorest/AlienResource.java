package com.priyanka.demorest;

import javax.servlet.http.HttpServletResponse;
//import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import java.io.IOException;

//import com.project.portal.Record;

import java.util.*;
//import com.priyanka.demorest
@Path("aliens")
public class AlienResource {
	portalRepo rep = new portalRepo();
	//@RolesAllowed("ADMIN")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/records")
    public List<Record> getAllRecords() 
    {
        return rep.getAllRecord();
    }
	@Path("/login")
    @POST
    @Produces("text/plain")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void login(
            @FormParam("Username") String uname,
            @FormParam("Password") String pass,
            @Context HttpServletResponse servletResponse
    ) throws IOException,RuntimeException {
		if(rep.isAuthenticated(uname, pass)) {
			servletResponse.sendRedirect("http://localhost:8080/demorest/userPortal.jsp?Username="+uname);
		}
		else {
		servletResponse.sendRedirect("http://localhost:8080/demorest/index.jsp");
		}
	}
	@Path("/adminlog")
    @POST
    @Produces("text/plain")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void adminlogin(
            @FormParam("Username") String uname,
            @FormParam("Password") String pass,
            @Context HttpServletResponse servletResponse
    ) throws IOException,RuntimeException {
		if(rep.adminAuthenticated(uname, pass)) {
			servletResponse.sendRedirect("http://localhost:8080/demorest/adminPortal.jsp");
		}
		else {
		servletResponse.sendRedirect("http://localhost:8080/demorest/index.jsp");
		}
	}
	@Path("/specific")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Record getRecord(
            @FormParam("customerId") String custId,
            @Context HttpServletResponse servletResponse
    ) throws IOException,RuntimeException {
		return rep.getUserRecord(custId);
	}
	@Path("/specificDel")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void deleteRecord(
            @FormParam("customerId") String custId,
            @Context HttpServletResponse servletResponse
    ) throws IOException,RuntimeException {
		if(rep.deleteUserRecord(custId)) {
			servletResponse.sendRedirect("http://localhost:8080/demorest/adminPortal.jsp");
		}
		else {
			servletResponse.sendRedirect("http://localhost:8080/demorest/errorpage.jsp");
		}
	}
	@Path("/adminUpdate")
    @POST
    @Produces("text/plain")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void adminModify(
    		@FormParam("CustomerId") String custId,
            @FormParam("CustomerName") String cname,
            @FormParam("Place") String place,
            @FormParam("PhoneNo") String phoneNo,
            @FormParam("UserName") String uname,
            @FormParam("Password") String pass,
            @Context HttpServletResponse servletResponse
    ) throws IOException,RuntimeException {
		if(rep.isPresent(custId)) {
			if(rep.adminMod(custId,cname,place,phoneNo,uname,pass)) {
			servletResponse.sendRedirect("http://localhost:8080/demorest/adminPortal.jsp");
			}
		}
		else {
			servletResponse.sendRedirect("http://localhost:8080/demorest/updateForm.jsp");
		}
	}
	@Path("/umodify")
    @POST
    @Produces("text/plain")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void userModify(
    		@QueryParam("Username") String uname,
            @FormParam("CustomerName") String cname,
            @FormParam("Place") String place,
            @FormParam("PhoneNo") String phoneNo,
            @Context HttpServletResponse servletResponse
    ) throws IOException,RuntimeException {
		if(rep.isIdPresent(uname)) {
			if(rep.userMod(cname,place,phoneNo,uname)) {
			servletResponse.sendRedirect("http://localhost:8080/demorest/userPortal.jsp");
			}
		}
		else {
			servletResponse.sendRedirect("http://localhost:8080/demorest/errorpage.jsp");
		}
		System.out.println(uname);
	}
	@Path("/viewRecord")
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Record viewRecord(
            @QueryParam("Username") String uname,
            @Context HttpServletResponse servletResponse
    ) throws IOException,RuntimeException {
		return rep.viewUserRecord(uname);
	}

}
