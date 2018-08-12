import java.util.*;

public class Team {
	String name;
	Coach coach;
	int numPlayers;
	ArrayList<Player> players;
	
	public Team(String n, Coach c, int num, ArrayList<Player> p) {
		name = n;
		coach = c;
		numPlayers = num;
		players = p;
	}
	
	public String getName() {
		return name;
	}
	
	public Coach getCoach() {
		return coach;
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public String toString() {
		return "Team Name: " + this.getName() + "\n" +
				"Coach: " + (this.getCoach()).toString() + "\n" +
				"Number of Players: " + this.getNumPlayers() + "\n" +
				"Players: " + "\n" + (this.getPlayers()).toString() + "\n";
	}
}
