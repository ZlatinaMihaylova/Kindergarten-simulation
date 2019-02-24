import java.time.LocalTime;
import java.util.Random;

public abstract class Child extends Person {
	
	private KindergartenGroup group;

	public Child(String name,KindergartenGroup group) throws Exception {
		super(name);
		this.setGroup(group);
		
	}
	@Override
	public void run() {

		LocalTime endTime =LocalTime.now().plusSeconds(10);
		if ( this.group.getActivity().equals("sing")) {
			while (LocalTime.now().isBefore(endTime)) {
				this.sing();
			}
		}
		else { 
			while (LocalTime.now().isBefore(endTime)) {
				this.draw();
			}
		}
		System.out.println(this.getName() + " will go home now.");
		
	}

	KindergartenGroup getGroup() {
		return group;
	}

	void setGroup(KindergartenGroup group) {
		this.group = group;
	}

	void sing() {

		
		try {
			Thread.sleep(new Random().nextInt(4) * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		System.out.println(this.getName() + " sings!");
	}
	
	abstract void draw();

}
