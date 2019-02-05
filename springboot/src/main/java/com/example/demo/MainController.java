package com.example.demo;
	
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.application.model.Model;
import com.application.security.Hashing;
import com.example.databasefactory.DatabaseFactory;

@Controller
@CrossOrigin(origins="*")
@RequestMapping(path="/formtest")
public class MainController {
	private static Connection connection = null;
	
	public MainController() {
		MainController.connection = new DatabaseFactory().getConnection();
	}
	
	@GetMapping(path="/checkUserNameOrEmail")
	public static @ResponseBody Map<String, String> checkIfUserNameOrEmailExists(@RequestParam String data, @RequestParam String type) {
		HashMap<String, String> hashMap = new HashMap<>();
		data = new Model().encryptField(data);
		
		try {
			Statement statement = MainController.connection.createStatement();
			String fieldName = null;
			String searchType = (type.equals("Username")?"user_name":"email");
			
			ResultSet resultSet = statement.executeQuery("SELECT * FROM registration_details WHERE "+searchType+"='"+data+"'");
			
			while (resultSet.next()) {
				fieldName = resultSet.getString(searchType);
			}
			
			if (fieldName == null) {
				hashMap.put("code", type+" is available");
			}
			else {
				hashMap.put("code", type+" '"+new Model().decryptField(fieldName)+"' is taken. Please choose another "+type.toLowerCase());
			}
		}
		catch (SQLException sqlException) {
			hashMap.put("code", sqlException.getMessage());
		}
		catch (Exception exception) {
			hashMap.put("code", exception.getMessage());
		}
		
		return hashMap;
	}
	
	@GetMapping(path="/add")
	public static @ResponseBody Map<String, String> addNewUser(@RequestParam String userName, @RequestParam String firstName,
			@RequestParam String lastName, @RequestParam String email, @RequestParam String dateOfBirth, @RequestParam String password) {
		Map<String, String> map = new HashMap<>();
		
		RegistrationDetails registrationDetails = new Model().encryptAndHashRegistrationDetails(new RegistrationDetails(userName, firstName, lastName, email, dateOfBirth, password));
		
		map = MainController.save(registrationDetails);
		
		return map;
	}
	
	public static final Map<String, String> save(RegistrationDetails registrationDetails) {
		HashMap<String, String> hashMap = new HashMap<>();
		
		try {
			Statement statement = MainController.connection.createStatement();
			
			boolean bool = statement.execute("INSERT INTO registration_details VALUES('"+registrationDetails.getUserName()+
					"', '"+registrationDetails.getDateOfBirth()+"', '"+registrationDetails.getEmail()+"', '"+registrationDetails.getFirstName()+"', '"+registrationDetails.getLastName()
					+"', '"+registrationDetails.getPassword()+"')");
			
			hashMap.put("code", (bool == false?"Saved": "An error occurred when saving record. Please try again"));
		}
		catch (SQLException sqlException) {
			hashMap.put("code", sqlException.getMessage());
		}
		catch (Exception exception) {
			hashMap.put("code", exception.getMessage());
		}
		
		return hashMap;
	}
	
	@GetMapping(path="/validateUserCredentials")
	public static @ResponseBody final Map<String, String> validateUserLoginCredentials(@RequestParam String userName, @RequestParam String password) {
		HashMap<String, String> hashMap = new HashMap<>();
		
		userName = new Model().encryptField(userName);
		password = new Hashing().hashPassword(password);
		
		try {
			Statement statement = MainController.connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery("SELECT first_name FROM registration_details WHERE user_name='"+userName+"' AND password='"+password+"'");
			String firstName = null;
			
			while (resultSet.next()) {
				firstName = resultSet.getString("first_name");
			}
			
			if (firstName == null) {
				hashMap.put("code", "Login failed. Invalid username or password");
			}
			else if (firstName != null) {
				hashMap.put("code", "Login successful");
			}
		}
		catch (SQLException sqlException) {
			hashMap.put("code", sqlException.getMessage());
		}
		catch (Exception exception) {
			hashMap.put("code", exception.getMessage());
		}
		
		return hashMap;
	}
}