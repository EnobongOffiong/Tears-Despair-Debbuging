package src.tddgame;

public class Simulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Grid randomGrid = GridBuilder.buildRandomGrid();
        new Game(randomGrid);
	}

}
