package attsw.exam.example.core;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;

public class MongoRepositoryTest extends AbstractMongoRepositoryTest {

	@Override
	protected MongoClient extractMongoClient() {
		return new Fongo("Mongo Server").getMongo();
	}

}
