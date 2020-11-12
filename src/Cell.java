import java.util.Random;
public class Cell {
private boolean alive;
private int team;
private Random rand = new Random();

public Cell(boolean alive, int team) {
	this.alive=alive;
	this.team=team;
}

public Cell() {
	alive=rand.nextBoolean();
	team=rand.nextInt(2);
}


public boolean isAlive() {
	return alive;
	
}

public int getTeam() {
	return team;
}


public void setAlive(boolean alive) {
	this.alive=alive;
}

public void setTeam(int team) {
	this.team=team;
}


}


