package attsw.exam.example.core.repository;

import java.util.List;

import attsw.exam.example.core.Student;

public interface Repository {

	public List<Student> findAll();

	public Student findOne(String string);

}
