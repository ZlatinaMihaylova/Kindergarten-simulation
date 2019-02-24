import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Parent extends Person {
	
	private Set<Child> children;

	public Parent(String name) throws Exception {
		super(name);
	
		this.children = new HashSet<Child>();
	}

	void addChild(Child child) {
		this.children.add(child);
	}
	
	@Override
	public void run() {
		
		try {
			Thread.sleep((new Random().nextInt(3) + 1) * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for ( Child child : this.children) {
			Kindergarten.getKindergarten().exceptChild(child);
			
		}
		
		
		
	}

}
