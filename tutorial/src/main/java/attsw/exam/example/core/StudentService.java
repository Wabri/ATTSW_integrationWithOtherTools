package attsw.exam.example.core;

import java.util.List;

import attsw.exam.example.core.repository.Repository;

public class StudentService {

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
