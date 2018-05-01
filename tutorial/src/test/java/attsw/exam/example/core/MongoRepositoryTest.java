package attsw.exam.example.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.github.fakemongo.Fongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import attsw.exam.example.core.repository.Repository;
import attsw.exam.example.core.repository.mongo.MongoRepository;

public class MongoRepositoryTest {

	Repository mongoRepository;
	DBCollection students;

	@Before
	public void init() {
		MongoClient mongoClient = new Fongo("Mongo Server").getMongo();
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
		assertEquals(1,  mongoRepository.findAll().size());
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

	private void addStudentToStudentsCollection(String idValue) {
		BasicDBObject document = new BasicDBObject();
		document.put("id", idValue);
		students.insert(document);
	}

}
