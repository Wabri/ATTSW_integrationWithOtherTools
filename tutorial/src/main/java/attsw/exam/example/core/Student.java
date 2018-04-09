package attsw.exam.example.core;

public class Student {

	private String id;

	public Student(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + "]";
	}
}
