import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Demo {

	private static final int CHILDREN_IN_KINDERGARTEN_COUNT = 40;
	
	public static void main(String[] args) throws Exception {
		
		int countKids = 0;
		Set<Thread> parentSet = new HashSet<Thread>();
		Set<Thread> teachersSet = new HashSet<Thread>();
		while (countKids < CHILDREN_IN_KINDERGARTEN_COUNT) {
			int countKidsPerParent = new Random().nextInt(3) + 1;
			
			Parent parent = new Parent("Parent #" + (countKids * 100) + "");
			parentSet.add(new Thread(parent));
			for ( int kid = 0; kid < countKidsPerParent && countKids < CHILDREN_IN_KINDERGARTEN_COUNT; kid++) {
				int bigOrLittle = new Random().nextInt(2);
				if ( bigOrLittle == 0) {
					parent.addChild(new LittleChild("Child #" + countKids,
							KindergartenGroup.values()[new Random().nextInt(KindergartenGroup.values().length)]));
				}
				else {
					parent.addChild(new BigChild("Child #" + countKids,
							KindergartenGroup.values()[new Random().nextInt(KindergartenGroup.values().length)]));
				}
				countKids++;
			}	
		}

		teachersSet.add(new Thread(new Teacher("Teacher in group Frogs", KindergartenGroup.FROGS)));
		teachersSet.add(new Thread(new Teacher("Teacher in group Ladybugs", KindergartenGroup.LADYBUGS)));
		teachersSet.add(new Thread(new Teacher("Teacher in group Ducks", KindergartenGroup.DUCKS)));
		teachersSet.add(new Thread(new Teacher("Teacher in group Penguin", KindergartenGroup.PENGUINS)));
		
		for ( Thread teacher : teachersSet) {
			teacher.start();
		}
		
		for ( Thread parent : parentSet) {
			parent.start();
		}
		
		
	}

}
