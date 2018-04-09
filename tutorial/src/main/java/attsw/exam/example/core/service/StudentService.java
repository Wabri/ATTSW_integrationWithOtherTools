package attsw.exam.example.core.service;

import java.util.List;

import attsw.exam.example.core.Student;
import attsw.exam.example.core.repository.Repository;

public class StudentService implements IStudentService{

	private Repository repository;

	public StudentService(Repository repository) {
		this.repository = repository;
	}

	public List<Student> getAllStudents() {
		return repository.findAll();
	}

	public Student oneStudent(String idStudent) {
		return repository.findOne(idStudent);
	}

}
