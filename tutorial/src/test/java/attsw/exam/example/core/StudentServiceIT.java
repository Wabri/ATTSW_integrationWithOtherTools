package attsw.exam.example.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import attsw.exam.example.core.repository.Repository;
import attsw.exam.example.core.repository.mongo.MongoRepository;
import attsw.exam.example.core.service.StudentService;

public class StudentServiceIT {

	private StudentService studentService;
	private DBCollection students;

	@Before
	public void init() {
		MongoClient mongoClient = new Fongo("Mongo Server").getMongo();
		Repository mongoRepository = new MongoRepository(mongoClient);
		studentService = new StudentService(mongoRepository);
		DB db = mongoClient.getDB("School");
		students = db.getCollection("Students");
	}

	@Test
	public void testGetAllStudentsWhenThereAreNoStudents() {
		assertTrue(studentService.getAllStudents().isEmpty());
	}

	@Test
	public void testGetAllStudents() {
		addNewStudentToCollection("id0");
		assertEquals(1, studentService.getAllStudents().size());
		assertEquals("id0", studentService.getAllStudents().get(0).getId());
	}
	
	@Test
	public void testGetOneStudentWhenThereAreNoStudents() {
		addNewStudentToCollection("id0");
		Student student = studentService.oneStudent("id1");
		assertNull(student);
	}
	
	@Test
	public void testGetStudent () {
		addNewStudentToCollection("id0");
		addNewStudentToCollection("id1");
		assertEquals("id1", studentService.oneStudent("id1").getId());
	}

	private void addNewStudentToCollection(String id) {
		BasicDBObject newElement = new BasicDBObject();
		newElement.put("id", id);
		students.insert(newElement);
	}

}
