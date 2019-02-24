import java.time.LocalTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Teacher extends Person{

	private KindergartenGroup group;
	private Set<Child> childrenInMyGroup;
	
	public Teacher(String name,KindergartenGroup group) throws Exception {
		super(name);
		this.childrenInMyGroup = new HashSet<Child>();
		this.group = group;
		Kindergarten.getKindergarten().addTeacher(this.group, this);
	}
	
	void exceptChildInMyGroup(Child child) {
		System.out.println(super.getName() + " exepted " + child.getName() + " in group "  + this.group.toString());
		this.childrenInMyGroup.add(child);
	}

	@Override
	public void run() {
		while(!Kindergarten.getKindergarten().areAllChildrenDistributed()) {
			try {
				Kindergarten.getKindergarten().distributeChild(this);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int randomActivity = new Random().nextInt(2);
		if (randomActivity == 0 ) {
			this.group.setActivity("sing");
		}
		else {
			this.group.setActivity("draw");
		}

		System.out.println("*** All children are distributed! ***\n*** The classes in group " + this.group + " begin at " + LocalTime.now() + " and the children will " + this.group.getActivity());
		
		for ( Child child : this.childrenInMyGroup) {
			Thread thread = (new Thread(child));
			thread.start();
			
		}

		
	}

}
