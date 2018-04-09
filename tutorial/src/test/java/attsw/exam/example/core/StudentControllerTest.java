package attsw.exam.example.core;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

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
		studentController = new StudentController(iStudentService);
	}

	@Test
	public void testGetAllStudentsWhenThereAreNoStudents() {
		assertEquals("", studentController.getAllStudents());
		verify(iStudentService, times(1)).getAllStudents();
	}

}
