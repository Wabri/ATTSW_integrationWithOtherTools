package attsw.exam.example.core;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import attsw.exam.example.core.controller.StudentController;
import attsw.exam.example.core.service.IStudentService;

public class StudentControllerTest {

	StudentController studentController;
	private List<Student> studentsList;
	private IStudentService iStudentService;

	@Before
	public void init() {
		studentsList = new ArrayList<Student>();
		iStudentService = mock(IStudentService.class);
		when(iStudentService.getAllStudents()).thenReturn(studentsList);
		studentController = new StudentController(iStudentService);
	}

	@Test
	public void testGetAllStudentsWhenThereAreNoStudents() {
		assertGetAllStudent("");
	}

	@Test
	public void testGetAllStudents() {
		studentsList.add(newStudentTest("id0"));
		studentsList.add(newStudentTest("id1"));
		assertGetAllStudent(extractAllStudentsStringFromList(studentsList));
		verify(iStudentService, times(1)).getAllStudents();
	}

	private String extractAllStudentsStringFromList(List<Student> list) {
		return list.stream().map(student -> student.toString() + System.getProperty("line.separator"))
				.collect(Collectors.joining());
	}

	private void assertGetAllStudent(String expected) {
		assertEquals(expected, studentController.getAllStudents());
		verify(iStudentService, times(1)).getAllStudents();
	}

	private Student newStudentTest(String idStudent) {
		return new Student(idStudent);
	}

}
