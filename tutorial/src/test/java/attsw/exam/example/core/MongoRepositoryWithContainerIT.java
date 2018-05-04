package attsw.exam.example.core;

import java.net.UnknownHostException;

import org.junit.ClassRule;
import org.testcontainers.containers.GenericContainer;

import com.mongodb.MongoClient;

public class MongoRepositoryWithContainerIT extends AbstractMongoRepositoryTest {

	@SuppressWarnings("rawtypes")
	@ClassRule
	public static GenericContainer mongo = new GenericContainer("mongo:latest").withExposedPorts(27017);

	@Override
	protected MongoClient extractMongoClient() throws UnknownHostException {
		return new MongoClient(mongo.getContainerIpAddress(), mongo.getMappedPort(27017));
	}

}
