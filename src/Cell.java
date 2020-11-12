import java.util.Random;
public class Cell {
private boolean alive;
private char team;

public Cell(boolean alive, char team) {
	this.alive=alive;
	this.team=team;
}

public Cell() {
	Random rand = new Random();
	alive=rand.nextBoolean();
	team=(rand.nextBoolean())? 'A' : 'B';
}


public boolean isAlive() {
	return alive;
	
}

public char getTeam() {
	return team;
}


public void setAlive(boolean alive) {
	this.alive=alive;
}



}


