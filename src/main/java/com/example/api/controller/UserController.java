package com.example.api.controller;


import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.constants.URI;
import com.example.api.entity.User;
import com.example.api.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

// @Controller
// @ResponseBody - this is used to directly pass the result to the
// response body
// Spring has a combination of above two controllers which is 
@RestController
@RequestMapping(value=URI.USERS)
@Api(tags="users")
public class UserController {
		
	private UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	// it not mandatory to mention produces and consumes as JSON types because spring will take it from dependency
	// but in case there are other types like moc c which is for XML is such cases its better to explicitly metion
	@RequestMapping(method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Find All Users", notes = "Returns the list of Users")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=500, message="Internal Server Error")
	})
	public List<User> findAll(){
		return service.findAll();
	}
	
	@RequestMapping(method=RequestMethod.GET, value=URI.ID, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	// pathvariable is used to match the param in the request to the method param
	@ApiOperation(value = "Find User by id", notes = "Returns the User if the id exists")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=404, message="Not Found"),
			@ApiResponse(code=500, message="Internal Server Error")
	})
	public User findOne(@PathVariable("id")String id) {
		return service.findOne(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Create User", notes = "Creates a User")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=400, message="Bad Request"),
			@ApiResponse(code=500, message="Internal Server Error")
	})
	public User create(@RequestBody User user) {
		return service.create(user);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, value=URI.ID, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Update User", notes = "Updates an existing User")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=404, message="Not Found"),
			@ApiResponse(code=500, message="Internal Server Error")
	})
	public User update(@PathVariable("id") String id, @RequestBody User user) {
		return service.update(id, user);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value=URI.ID, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "Delete User", notes = "Deletes the User if the id exists")
	@ApiResponses(value= {
			@ApiResponse(code=200, message="Success"),
			@ApiResponse(code=404, message="Not Found"),
			@ApiResponse(code=500, message="Internal Server Error")
	})
	public void delete(@PathVariable("id") String id) {
		service.delete(id);
	}
} 
