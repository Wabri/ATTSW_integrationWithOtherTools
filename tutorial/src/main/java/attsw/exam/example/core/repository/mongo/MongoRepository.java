package attsw.exam.example.core.repository.mongo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

import attsw.exam.example.core.Student;
import attsw.exam.example.core.repository.Repository;

public class MongoRepository implements Repository {

	private MongoClient mongoClient;
	DBCollection students;

	public MongoRepository(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
		students = mongoClient.getDB("School").getCollection("Students");
	}

	@Override
	public List<Student> findAll() {
		DBCursor cursor = students.find();
		return StreamSupport.stream(cursor.spliterator(), false).map(element -> new Student((String) element.get("id")))
				.collect(Collectors.toList());
	}

	@Override
	public Student findOne(String string) {
		return null;
	}

}
