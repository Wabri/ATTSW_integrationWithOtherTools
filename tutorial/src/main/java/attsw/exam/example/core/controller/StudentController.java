package attsw.exam.example.core.controller;

import java.util.stream.Collector;
import java.util.stream.Collectors;

import attsw.exam.example.core.service.IStudentService;

public class StudentController {

	private IStudentService studentService;

	public StudentController(IStudentService iStudentService) {
		this.studentService = iStudentService;

	}

	public String getAllStudents() {
		return studentService.getAllStudents().stream()
				.map(student -> student.toString() + System.getProperty("line.separator"))
				.collect(Collectors.joining());
	}

	public String getOneStudent(String idStudent) {
		return studentService.oneStudent(idStudent).toString();
	}

}
