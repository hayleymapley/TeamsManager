
public class Coach implements TeamMember {
	private String name;
	
	public Coach(String n) {
		name = n;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
}
