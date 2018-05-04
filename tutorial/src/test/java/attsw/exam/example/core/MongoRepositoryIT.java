package attsw.exam.example.core;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;

public class MongoRepositoryIT extends AbstractMongoRepositoryTest {

	@Override
	protected MongoClient extractMongoClient() throws UnknownHostException {
		return new MongoClient();
	}

}
