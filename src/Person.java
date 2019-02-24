
public abstract class Person implements Runnable {

	private final String name;

	public Person(String name) throws Exception {
		if ( name != null && !(name.equals(""))){
			this.name = name;
		}
		else {
			throw new Exception("Invalid name");
		}
	}

	String getName() {
		return name;
	}
	
	
	
}
