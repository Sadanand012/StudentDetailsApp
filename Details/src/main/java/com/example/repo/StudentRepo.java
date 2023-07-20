package com.example.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Student;

@Repository // MVC architecture
public interface StudentRepo extends JpaRepository<Student, Integer>{

	List<Student> findByMarksBetween(double startMark, double endMark);
}
