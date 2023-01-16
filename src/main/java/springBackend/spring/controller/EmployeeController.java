package springBackend.spring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springBackend.spring.exception.ResourceNotFoundException;
import springBackend.spring.model.Employee;
import springBackend.spring.repository.EmployeeRepository;

@CrossOrigin(origins= {"http://localhost:3000/"})
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {


	  
	  
	  @Autowired
	  private EmployeeRepository employeeRepository;
	  
	  //get all employees 
	  
	  @GetMapping("/employees")
	  public List<Employee> getAllEmployees(){
		  
		 
		  
		  return employeeRepository.findAll();
	  }
	  
	  
	  //create Employee Rest_Api
	  
	  @PostMapping("/employees")
	  
	  
	  public ResponseEntity<Employee> createEmployee (@RequestBody Employee employee) {
		  
		    
		     
		     
		     return ResponseEntity.ok(employeeRepository.save(employee));
		  
	  }
	  
	  // get employee by id rest api
	  
	  @GetMapping("/employees/{id}")
	  public  ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		  
		   Employee employee= employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("not Found")
		   
				   
				   );
		  
		  return ResponseEntity.ok(employee);
		  
	  }
	  
	  
	  //Update employee api
	  
	  @PutMapping("/employees/{id}")
	  public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		  
		   Employee employee= employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("not Found"));
		
		   employee.setFirstName(employeeDetails.getFirstName());
		   employee.setLastName(employeeDetails.getLastName());
		   
		   
		     Employee updatedEmployee=employeeRepository.save(employee);
		     
		   return ResponseEntity.ok(updatedEmployee);
		   
		  
		    
	  }
	  
	  //delete employee rest api
	  
	  @DeleteMapping("/employees/{id}")
	  public ResponseEntity <Map<String,Boolean>> deleteEmployee(@PathVariable Long id){
		  
		  
		  Employee employee= employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("not Found"));
		  
		  
		  employeeRepository.delete(employee);
		  
		  Map<String,Boolean> response= new HashMap<String, Boolean>();
		  
		  response.put("deleted",Boolean.TRUE);
		  
		return ResponseEntity.ok(response);
		  
		  
		  
	  }
	  
	  
}
