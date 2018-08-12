import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import ecs100.UI;

public class UserInterface {

	ArrayList<Team> allTeams = new ArrayList<Team>();
	public static final int LEFT_SIDE = 10;
	public static final int ZERO = 360;

	public void listTeams() {
		UI.clearText();
		for (Team i : allTeams) {
			UI.println(i.getName());
		}
	}

	public void listTeamsWithCoaches() {
		UI.clearText();
		for (Team i : allTeams) {
			UI.println(i.getName() + " coached by " + i.getCoach());
			UI.println("");
		}
	}

	public void listTeamPlayers() {
		UI.clearText();
		String team = UI.askString("Which team?");
		UI.clearText();
		for (Team i : allTeams) {
			if ((i.getName()).equalsIgnoreCase(team)) {
				for (int j=0; j<(i.getPlayers()).size(); j++) {
					UI.println((i.getPlayers()).get(j));
				}
			}
		}
	}

	public void listPositionPlayersOnTeam() {
		UI.clearText();
		String team = UI.askString("Which team?");
		String position = UI.askString("Which position?");
		UI.clearText();
		for (Team i : allTeams) {
			if ((i.getName()).equalsIgnoreCase(team)) {

				for (int j=0; j<(i.getPlayers()).size(); j++) {
					Player n = i.getPlayers().get(j);

					for (int k=0; k<(n.getPositions()).size(); k++)  {
						Position p = (n.getPositions()).get(k);
						if (p.toString().equalsIgnoreCase(position)) {
							UI.println(n.toString());
						}
					}
				}
			}
		}
	}

	public void listHeights() {
		UI.clearText();
		int min = UI.askInt("Taller than?");
		int max = UI.askInt("Shorter than?");
		UI.clearText();
		for (Team i : allTeams) {
			for (int j=0; j<(i.getPlayers()).size(); j++) {
				Player n = (i.getPlayers()).get(j);
				if (n.getHeight()>min && n.getHeight()<max) {
					UI.println(n.toString());
				}
			}
		}
	}

	//NOT DONE
	//public void checkSelection() {

	//}

	public void graphHeights() {
		UI.clearText();
		UI.clearGraphics();
		String ans = UI.askString("Would you like to see heights by team or position?");
		if (ans.equalsIgnoreCase("team")) {
			ArrayList<Double> heights = new ArrayList<Double>();
			String ans2 = UI.askString("Which team?");
			for (Team i : allTeams) {
				if ((i.getName()).equalsIgnoreCase(ans2)) {
					for (int j=0; j<(i.getPlayers()).size(); j++) {
						Player n = (i.getPlayers()).get(j); 
						heights.add(n.getHeight());
					}
				}

			}
			UI.setColor(Color.black);
			UI.drawLine(LEFT_SIDE, ZERO+5, LEFT_SIDE + heights.size()*10, ZERO+5);
			UI.println(heights.size());
			UI.setLineWidth(10);
			for (int i=0; i<heights.size(); i++) {
				double heightY = heights.get(i);
				if(i%2==0) {
					UI.setColor(Color.blue);
					UI.drawLine(LEFT_SIDE+5+(i*10), ZERO - heightY, LEFT_SIDE+5+(i*10), ZERO);
				} else {
					UI.setColor(Color.green);
					UI.drawLine(LEFT_SIDE + (i*10), ZERO - heightY, LEFT_SIDE + (i*10), ZERO);
				}
			}

		} else if (ans.equalsIgnoreCase("position")) {
			//String ans2 = UI.askString("Which position?");

		} else {
			this.graphHeights();
		}
	}

	public UserInterface() {
		UI.initialise();
		UI.addButton("List teams", this::listTeams);
		UI.addButton("List coaches", this::listTeamsWithCoaches);
		UI.addButton("List team players", this::listTeamPlayers);
		UI.addButton("List players by position", this::listPositionPlayersOnTeam);
		UI.addButton("Search by height", this::listHeights);
		//UI.addButton("Check team lineup", this::checkSelection);
		UI.addButton("Graph player heights", this::graphHeights);
		// You may want to use this, or move it somewhere else,
		// or write your own version.
		try {
			Scanner scanner = new Scanner(new File("teams.txt"));
			while (scanner.hasNext()) {
				ArrayList<Player> teamPlayers = new ArrayList<Player>();

				String teamName = scanner.nextLine();
				String coachName = scanner.nextLine();
				Coach coach = new Coach(coachName);
				int numPlayers = scanner.nextInt();
				scanner.nextLine();
				//loop to get all players details
				for (int i=0; i<numPlayers; i++) {
					ArrayList<Position> playerPositions = new ArrayList<Position>();
					//to get positions
					String position;
					String s = scanner.next();
					String[] pos =  s.split(",");
					for (int j=0; j<pos.length; j++) {
						position = pos[j];
						Position a = new Position(position);
						playerPositions.add(a);
					}
					String playerName = scanner.nextLine();	
					int height = scanner.nextInt();
					scanner.nextLine();
					String birthPlace = scanner.nextLine();
					Player p = new Player(playerName, playerPositions, height, birthPlace);
					teamPlayers.add(p);
				}
				Team b = new Team(teamName, coach, numPlayers, teamPlayers);
				allTeams.add(b);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			UI.printf("Error loading file: %s%n", e);
		}
	}
	public static void main(String[] args) {
		new UserInterface();
	}
}
