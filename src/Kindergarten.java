import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Kindergarten {

	private static final int CHILDREN_IN_KINDERGARTEN_COUNT = 40;
	
	private BlockingQueue<Child> childrenExcepted;
	private static Kindergarten kindergarten;
	private Map<KindergartenGroup,Teacher> teachers;
	private volatile Integer distributedChildrenCount;
	
	private BlockingQueue<String> drawings;
	private BlockingQueue<String> coloredDrawings;
	
	private Kindergarten() {
		this.childrenExcepted = new ArrayBlockingQueue<Child>(CHILDREN_IN_KINDERGARTEN_COUNT);
		this.teachers = new HashMap<KindergartenGroup,Teacher>();
		this.distributedChildrenCount = new Integer(0);
		
		this.drawings = new LinkedBlockingQueue<String>();
		this.coloredDrawings = new LinkedBlockingQueue<String>();
	}

	static Kindergarten getKindergarten() {
		if (Kindergarten.kindergarten == null ) {
			Kindergarten.kindergarten = new Kindergarten();
		}
		return Kindergarten.kindergarten;
	}

	void exceptChild(Child child) {
			System.out.println(child.getName() + " was taken to kindergarten.");
			try {
				this.childrenExcepted.put(child);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
	}
	
	void distributeChild(Teacher teacher) throws InterruptedException {

		synchronized(this.childrenExcepted) {
			if ( this.areAllChildrenDistributed()) {
				return;
			}
			Child child = this.childrenExcepted.take();
			this.teachers.get(child.getGroup()).exceptChildInMyGroup(child);
			synchronized(this.distributedChildrenCount) {

				this.distributedChildrenCount ++;
			}
		}
		
		
		
	}
	
	boolean areThereWaitingChildren() {
		return !(this.childrenExcepted.isEmpty());
	}
	
	boolean areAllChildrenDistributed(){
		if (this.distributedChildrenCount < CHILDREN_IN_KINDERGARTEN_COUNT ) {
			return false;
		}
		synchronized(Kindergarten.getKindergarten()) {
			Kindergarten.getKindergarten().notifyAll();
		}
		return true;
		
	}
	
	void addTeacher(KindergartenGroup group, Teacher teacher) {
		this.teachers.put(group, teacher);
	}
	
	void addDrawing(String drawing) throws InterruptedException {
		System.out.println("Child added a " + drawing + " to the drawings!" );
		this.drawings.put(drawing);
		
	}
	
	String takeDrawing() throws InterruptedException {
		return this.drawings.take();
		
	}
	
	void addColoredDrawing(String coloredDrawing) throws InterruptedException {
		System.out.println("Child added a " + coloredDrawing + " to the colored drawings!" );
		this.coloredDrawings.put(coloredDrawing);
		
	}
	
}
