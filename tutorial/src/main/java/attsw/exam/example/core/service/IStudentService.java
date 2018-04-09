package attsw.exam.example.core.service;

import java.util.List;

import attsw.exam.example.core.Student;

public interface IStudentService {

	List<Student> getAllStudents();

	Student oneStudent(String idStudent);

}
