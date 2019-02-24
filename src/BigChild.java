import java.time.LocalTime;
import java.util.Random;

public class BigChild extends Child{
	
	private final static String[] drawings = { "\"horse\"", "\"mom\"", "\"dad\"", "\"house\"","\"tree\"","\"dog\"", "\"cat\"", "\"flower\"","\"mouse\"","\"friend\""};

	public BigChild(String name,KindergartenGroup group) throws Exception {
		super(name, group);
		// TODO Auto-generated constructor stub
	}



	@Override
	void setGroup(KindergartenGroup group) {
		while ( group.equals(KindergartenGroup.DUCKS) || group.equals(KindergartenGroup.PENGUINS)) {
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
		
		try {
			Thread.sleep(new Random().nextInt(4) * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		String drawing = BigChild.drawings[new Random().nextInt(BigChild.drawings.length)];
		System.out.println(this.getName() + " draws a " + drawing);
		
		try {
			
			Kindergarten.getKindergarten().addDrawing(drawing);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}
	
	
}
