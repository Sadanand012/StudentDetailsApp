package com.example.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Student;
import com.example.exception.StudentException;
import com.example.repo.StudentRepo;

@RestController
public class StudentController {

	@Autowired
	private StudentRepo sImpl;
	
	@GetMapping("/hello")
	public String SayHello() {
		return "{\"message\":\"Hello !\"}";
	}
	@PostMapping("/student")
	public ResponseEntity<String> createStudentHandler(@RequestBody Student std){
		String res = "";
//		System.out.println(std);
		sImpl.save(std);
		res = "Created!!!";
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}
	
	@GetMapping("/studentlists")
	public ResponseEntity<List<Student>> getListOfStudents(){
		List<Student> list = sImpl.findAll();
		if(list == null) throw new StudentException("Its empty or Errors");
		return new ResponseEntity<List<Student>>(list, HttpStatus.OK);
	}
	
	@DeleteMapping("/students/{roll}")
	public ResponseEntity<String> deleteStudentHandler(@PathVariable Integer roll){
		Student stu = sImpl.findById(roll).orElseThrow(() -> new StudentException("Not Found" + roll));
		sImpl.delete(stu);
		return new ResponseEntity<>("Done", HttpStatus.OK);
	}
	
	@PutMapping("/students/{roll}")
	public ResponseEntity<String> updateStudent(@PathVariable Integer roll, @RequestBody Student student){
		student.setRoll(roll);
		Student stu = sImpl.findById(roll).orElseThrow(() -> new StudentException("Not Found" + roll));
		
		Student std1 = sImpl.save(student);
		return new ResponseEntity<String>("Done",HttpStatus.OK);
	}
	
	@GetMapping("/studentsrange")
    public ResponseEntity<List<Student>> getStudentsWithinMarkRange(@RequestParam("startMark") double startMark, @RequestParam("endMark") double endMark) {
        
		List<Student> list = sImpl.findByMarksBetween(startMark, endMark);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
	
	
}
