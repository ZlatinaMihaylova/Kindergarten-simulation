import java.time.LocalTime;
import java.util.Random;

public class LittleChild extends Child {

	public LittleChild(String name,KindergartenGroup group) throws Exception {
		super(name, group);
	}


	@Override
	void setGroup(KindergartenGroup group)  {
		while ( group.equals(KindergartenGroup.FROGS) || group.equals(KindergartenGroup.LADYBUGS)) {
			try {
				throw new Exception("Invalid kindergarten group!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			group = KindergartenGroup.values()[new Random().nextInt(KindergartenGroup.values().length)];
		}
		super.setGroup(group);

	}

	@Override
	void draw() {
		String drawing = null;
		try {
			drawing = Kindergarten.getKindergarten().takeDrawing();
			System.out.println(this.getName() + " took a " + drawing + " from the table with drawings.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000);
			
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		
		
		System.out.println(this.getName() + " colored a  " + drawing);
		try {
			Kindergarten.getKindergarten().addColoredDrawing(drawing);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
	}
	
	
}
