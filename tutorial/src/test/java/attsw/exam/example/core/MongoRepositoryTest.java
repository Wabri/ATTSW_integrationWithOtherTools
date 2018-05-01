package attsw.exam.example.core;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.fakemongo.Fongo;
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

}
