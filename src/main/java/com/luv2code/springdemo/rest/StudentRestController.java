package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

	private List<Student> theStudents;
	
	
	// define @PostConstruct to load the student data ... only once!

	@PostConstruct
	public void loadData() {
	
		theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Poornima", "Patel"));
		theStudents.add(new Student("Mario", "Rossi"));
		theStudents.add(new Student("Mary", "Smith"));		
	}
	
	
	
	// define endpoint for "/students" - return list of students
	
	@GetMapping("/students")
	public List<Student> getStudents() {
			
		return theStudents;
	}
	
	// define endpoint for "/students/{studentId}" - return student at index

	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		// just index into the list ... keep it simple for now
		
		// check the studentId against list size
		if ( (studentId >= theStudents.size()) || (studentId < 0) ) {
//			throw custom exception for student not found if studentId is out of bounds. if were using DB could use query instead- if no results, throw exception
			throw new StudentNotFoundException("Student id not found - " + studentId);
		}

//		happy path, get and return the student
		return theStudents.get(studentId);
	}

//	exception handler returns a ResponseEntity object with the error message in the body and the status code in the header
//	ResponseEntity is a wrapper for the HTTP response object. It provides for specifying HTTP status code + Response Body and HTTP headers
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

//		custom pojo created earlier to hold error message and timestamp
		StudentErrorResponse error = new StudentErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value()); //not found is 404
		error.setMessage(exc.getMessage()); //error message set based on exception message
		error.setTimeStamp(System.currentTimeMillis()); //timestamp
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//		jackson returns the created object error(body of response) and status code as JSON in the response body
	 }

	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {
//		this one is for any other exception

//		again create a StudentErrorResponse object
		StudentErrorResponse error = new StudentErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
//		error.setMessage(exc.getMessage());    //can update this line to give whatever plain text message you want
		error.setMessage("I don't know what that is...");
		error.setTimeStamp(System.currentTimeMillis());

//		return ResponseEntity object with error message and status code in the body and headers
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}









