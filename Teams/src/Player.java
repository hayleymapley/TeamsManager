import java.util.*;

public class Player implements TeamMember {
	String name;
	ArrayList<Position> positions;
	double height;
	String birthPlace;
	
	public Player(String n, ArrayList<Position> p, double h, String b) {
		name = n;
		positions = p;
		height = h;
		birthPlace = b;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<Position> getPositions() {
		return positions;
	}
	
	public double getHeight() {
		return height;
	}
	
	public String getBirthPlace() {
		return birthPlace;
	}
	
	public String toString() {
		return "Name: " + this.getName() + "\n" +
				"Positions: " + this.getPositions() + "\n" +
				"Height in cm: " + this.getHeight() + "\n" +
				"Birth place: " + this.getBirthPlace() + "\n";
	}
	
}
