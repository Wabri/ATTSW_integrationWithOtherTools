package attsw.exam.example.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.net.UnknownHostException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import attsw.exam.example.core.repository.Repository;
import attsw.exam.example.core.repository.mongo.MongoRepository;

public abstract class AbstractMongoRepositoryTest {

	Repository mongoRepository;
	DBCollection students;

	@Before
	public void init() throws UnknownHostException {
		MongoClient mongoClient = extractMongoClient();
		DB db = mongoClient.getDB("School");
		db.getCollection("Students").drop();
		mongoRepository = new MongoRepository(mongoClient);
		students = db.getCollection("Students");
	}

	@Test
	public void testGetAllStudentsEmpty() {
		assertTrue(mongoRepository.findAll().isEmpty());
	}

	@Test
	public void testOneStudent() {
		addStudentToStudentsCollection("id1");
		assertEquals(1, mongoRepository.findAll().size());
	}

	@Test
	public void testMoreThanOneStudentsInCollection() {
		addStudentToStudentsCollection("id1");
		addStudentToStudentsCollection("id2");
		List<Student> listOfStudents = mongoRepository.findAll();
		assertEquals(2, listOfStudents.size());
		assertEquals("id1", listOfStudents.get(0).getId());
		assertEquals("id2", listOfStudents.get(1).getId());
	}

	@Test
	public void testStudentNotFound() {
		assertNull(mongoRepository.findOne("id1"));
	}

	@Test
	public void testStudentFound() {
		addStudentToStudentsCollection("id1");
		addStudentToStudentsCollection("id2");
		Student student = mongoRepository.findOne("id2");
		assertNotNull(student);
		assertEquals("id2", student.getId());
	}

	private void addStudentToStudentsCollection(String idValue) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", idValue);
		students.insert(document);
	}


	protected abstract MongoClient extractMongoClient() throws UnknownHostException;
}