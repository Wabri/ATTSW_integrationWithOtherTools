package attsw.exam.example.core;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import attsw.exam.example.core.repository.Repository;

public class StudentServiceTest {

	StudentService studentService;
	List<Student> studentsList;
	Repository repository;

	@Before
	public void init() {
		studentsList = new ArrayList<Student>();
		repository = mock(Repository.class);
		studentService = new StudentService(repository);
		when(repository.findAll()).thenReturn(studentsList);
	}

	@Test
	public void testAllStudentsWithNoStudents() {
		verifyNumberOfStudents(0);
	}
	
	@Test
	public void testAllStudents() {
		studentsList.add(newStudentTest("id0"));
		studentsList.add(newStudentTest("id1"));
		verifyNumberOfStudents(2);
	}

	private Student newStudentTest(String idStudent) {
		return new Student(idStudent);
	}

	private void verifyNumberOfStudents(int numberOfStudents) {
		assertEquals(numberOfStudents, studentService.getAllStudents().size());
		verify(repository, times(1)).findAll();
	}

}
