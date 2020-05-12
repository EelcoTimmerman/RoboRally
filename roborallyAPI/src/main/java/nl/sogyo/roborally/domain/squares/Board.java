package nl.sogyo.roborally.domain.squares;

import java.util.ArrayList;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.elements.Laser;

public class Board{

	public static Board createSimpleBoard(){
		Board simpleBoard = new Board();
		Square square00 = new EmptySquare();
		Square square10 = new EmptySquare();
		Square square01 = new EmptySquare();
		Square square11 = new EmptySquare();
		square00.eastWall = true;
		square00.southWall = true;
		square10.westWall = true;
		square01.northWall = true;
		ArrayList<Square> row1 = new ArrayList<>();
		ArrayList<Square> row2 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		row2.add(square01);
		row2.add(square11);
		simpleBoard.addRow(row1);
		simpleBoard.addRow(row2);
		return simpleBoard;
	}

	public static Board createWrongWalls(){
		//square00 needs an eastwall
		//square10 needs a northwall
		Board wrongWallsBoard = new Board();
		Square square00 = new EmptySquare();
		Square square10 = new EmptySquare();
		Square square01 = new EmptySquare();
		Square square11 = new EmptySquare();
		square00.southWall = true;
		square10.westWall = true;
		ArrayList<Square> row1 = new ArrayList<>();
		ArrayList<Square> row2 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		row2.add(square01);
		row2.add(square11);
		wrongWallsBoard.addRow(row1);
		wrongWallsBoard.addRow(row2);
		return wrongWallsBoard;
	}

	public static Board createNonRectangularBoard(){
		Board simpleBoard = new Board();
		Square square00 = new EmptySquare();
		Square square01 = new EmptySquare();
		Square square10 = new EmptySquare();
		square00.eastWall = true;
		square00.southWall = true;
		square10.westWall = true;
		square01.northWall = true;
		ArrayList<Square> row1 = new ArrayList<>();
		ArrayList<Square> row2 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		row2.add(square01);
		simpleBoard.addRow(row1);
		simpleBoard.addRow(row2);
		return simpleBoard;
	}

	public static Board createLaserTestBoard(){
		Board laserTestBoard = new Board();
		Square square00 = new EmptySquare();
		Square square10 = new EmptySquare();
		Square square01 = new EmptySquare();
		Square square11 = new EmptySquare();
		Square square02 = new EmptySquare();
		Square square12 = new EmptySquare();

		square00.northWall = true;

		square00.southWall = true;
		square01.northWall = true;
		
		square01.westWall = true;

		square10.southWall = true;
		square11.northWall = true;

		square11.southWall = true;
		square12.northWall = true;
		
		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(square01);
		row2.add(square11);
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(square02);
		row3.add(square12);
		laserTestBoard.addRow(row1);
		laserTestBoard.addRow(row2);
		laserTestBoard.addRow(row3);

		Laser laser1 = new Laser(0,0, Direction.SOUTH, 1);
		Laser laser2 = new Laser(0,1, Direction.EAST, 1);
		Laser laser3 = new Laser(1,1, Direction.NORTH, 1);

		laserTestBoard.addLaser(laser1);
		laserTestBoard.addLaser(laser2);
		laserTestBoard.addLaser(laser3);

		return laserTestBoard;
	}

	public static Board createFaultyLaserTestBoard(){
		Board faultyLaserTestBoard = new Board();
		Square square00 = new EmptySquare();
		Square square10 = new EmptySquare();
		Square square01 = new EmptySquare();
		Square square11 = new EmptySquare();
		Square square02 = new EmptySquare();
		Square square12 = new EmptySquare();


		square00.southWall = true;
		square01.northWall = true;

		square10.southWall = true;
		square11.northWall = true;

		
		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(square01);
		row2.add(square11);
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(square02);
		row3.add(square12);
		faultyLaserTestBoard.addRow(row1);
		faultyLaserTestBoard.addRow(row2);
		faultyLaserTestBoard.addRow(row3);

		Laser laser1 = new Laser(0,0, Direction.SOUTH, 1);
		Laser laser2 = new Laser(0,1, Direction.EAST, 1);
		Laser laser3 = new Laser(1,2, Direction.NORTH, 1);

		faultyLaserTestBoard.addLaser(laser1);
		faultyLaserTestBoard.addLaser(laser2);
		faultyLaserTestBoard.addLaser(laser3);

		return faultyLaserTestBoard;		
	}

	public static Board createSlowConveyorbeltTestBoard(){
		Board conveyorbeltTestBoard = new Board();

		Square square00 = new SlowConveyorbelt(Direction.EAST);
		Square square10 = new EmptySquare();
		Square square20 = new SlowConveyorbelt(Direction.SOUTH);
		
		Square square01 = new EmptySquare();
		Square square11 = new EmptySquare();
		Square square21 = new EmptySquare();
		
		Square square02 = new SlowConveyorbelt(Direction.NORTH);
		Square square12 = new EmptySquare();
		Square square22 = new SlowConveyorbelt(Direction.WEST);

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		row1.add(square20);
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(square01);
		row2.add(square11);
		row2.add(square21);
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(square02);
		row3.add(square12);
		row3.add(square22);
		
		conveyorbeltTestBoard.addRow(row1);
		conveyorbeltTestBoard.addRow(row2);
		conveyorbeltTestBoard.addRow(row3);

		return conveyorbeltTestBoard;
	}

	public static Board createRobotLaserWallTestBoard(){
		Board robotLaserTestBoard = new Board();
		Square square00 = new EmptySquare();
		Square square10 = new EmptySquare();
		Square square01 = new EmptySquare();
		Square square11 = new EmptySquare();
		square00.eastWall = true;
		square00.southWall = true;
		square10.westWall = true;
		square10.southWall = true;
		square01.northWall = true;
		square01.eastWall = true;
		square11.northWall = true;
		square11.westWall = true;
		ArrayList<Square> row1 = new ArrayList<>();
		ArrayList<Square> row2 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		row2.add(square01);
		row2.add(square11);
		robotLaserTestBoard.addRow(row1);
		robotLaserTestBoard.addRow(row2);
		return robotLaserTestBoard;

	}

	public static Board createGearTestBoard(){
		Board gearTestBoard = new Board();

		Square square00 = new GearLeft();
		Square square10 = new GearRight();
		Square square20 = new Gear180();

		ArrayList<Square> row = new ArrayList<>();
		row.add(square00);
		row.add(square10);
		row.add(square20);

		gearTestBoard.addRow(row);

		return gearTestBoard;
	}

	public static Board createSlowConveyorbeltTestBoardOther(){
		Board conveyorbeltTestBoard = new Board();

		Square square00 = new SlowConveyorbelt(Direction.EAST);
		square00.eastWall = true;
		Square square10 = new EmptySquare();
		square10.westWall = true;
		Square square20 = new SlowConveyorbelt(Direction.SOUTH);
		
		Square square01 = new EmptySquare();
		Square square11 = new EmptySquare();
		Square square21 = new Pit();
		
		Square square02 = new SlowConveyorbelt(Direction.NORTH);
		Square square12 = new EmptySquare();
		Square square22 = new SlowConveyorbelt(Direction.SOUTH);

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		row1.add(square20);
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(square01);
		row2.add(square11);
		row2.add(square21);
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(square02);
		row3.add(square12);
		row3.add(square22);

		conveyorbeltTestBoard.addRow(row1);
		conveyorbeltTestBoard.addRow(row2);
		conveyorbeltTestBoard.addRow(row3);

		return conveyorbeltTestBoard;
	}

	public static Board createPitTestBoard(){
		Board pitTestBoard = new Board();

		Square square0 = new EmptySquare();
		Square square1 = new EmptySquare();
		Square square2 = new EmptySquare();
		Square pit = new Pit();

		ArrayList<Square> row = new ArrayList<>();
		row.add(square0);
		row.add(square1);
		row.add(square2);
		row.add(pit);

		pitTestBoard.addRow(row);

		return pitTestBoard;
	}

	public static Board createTESTBOARD4X4(){
		Board defaultBoard = new Board();

		Square square00 = new EmptySquare();
		Square square10 = new EmptySquare();
		Square square20 = new EmptySquare();
		Square square30 = new EmptySquare();
		
		Square square01 = new EmptySquare();
		Square square11 = new EmptySquare();
		Square square21 = new EmptySquare();
		Square square31 = new EmptySquare();
		
		Square square02 = new EmptySquare();
		Square square12 = new EmptySquare();
		Square square22 = new EmptySquare();
		Square square32 = new EmptySquare();
		
		Square square03 = new EmptySquare();
		Square square13 = new EmptySquare();
		Square square23 = new EmptySquare();
		Square square33 = new Checkpoint();

		square20.northWall = true;

		square01.westWall = true;

		square32.eastWall = true;

		square13.southWall = true;

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(square00);
		row1.add(square10);
		row1.add(square20);
		row1.add(square30);
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(square01);
		row2.add(square11);
		row2.add(square21);
		row2.add(square31);
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(square02);
		row3.add(square12);
		row3.add(square22);
		row3.add(square32);
		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(square03);
		row4.add(square13);
		row4.add(square23);
		row4.add(square33);

		defaultBoard.addRow(row1);
		defaultBoard.addRow(row2);
		defaultBoard.addRow(row3);
		defaultBoard.addRow(row4);

		return defaultBoard;
	}


/** This is an example boardstring:
* ES-NW*ES-NE*||*ES-WS*ES-X
* explanation of symbols:
* square separator: *
* new row: ||
* square type: ES (Empty Square), CH (checkpoint), 180 (Gear180), GL (GearLeft), GR (GearRight), PT (Pit),
* CSE (SlowConveyerBelt East), CSW (SlowConveyerBelt West), CSN (SlowConveyorBelt North), CSS (SlowConveyerBelt South)
* directions that contain wall: N (North), E (East), S (South), W (West), X (no walls)
*/ 

	private ArrayList<ArrayList<Square>> board;
	private ArrayList<Laser> lasers;

	public ArrayList<ArrayList<Square>> getBoard(){
		return this.board;
	}


	public Board(){
		this.board = new ArrayList<>();
		this.lasers = new ArrayList<>();
	}

	public void addRow(ArrayList<Square> row){
		this.board.add(row);
	}

	public void addLaser(Laser laser){
		this.lasers.add(laser);
	}

	public boolean hasConsistentWalls(){
		boolean consistent = true;
		for(int row = 0; row < this.getHeight(); row++){
			consistent &= eastNeighboursHaveConsistentHorizontalWalls(this.getSquare(0, row).hasWallAt(Direction.EAST), 1, row);
		}
		for(int column = 0; column < this.getWidth(); column++){
			consistent &= southNeighboursHaveConsistenVerticalWalls(this.getSquare(column, 0).hasWallAt(Direction.SOUTH), column, 1);
		}
		return consistent;
	}

	public boolean isRectangular(){
		for(ArrayList<Square> row : this.board){
			if(row.size() != this.getWidth()) return false;
		}
		return true;
	}

	private boolean eastNeighboursHaveConsistentHorizontalWalls(boolean mustHaveWestWall, int xCoordinate, int yCoordinate){
		boolean stillOnBoard = this.board.get(0).size() > xCoordinate;
		boolean consistent = true;
		if(stillOnBoard){
			consistent = this.getSquare(xCoordinate, yCoordinate).hasWallAt(Direction.WEST) == mustHaveWestWall;
			consistent &= eastNeighboursHaveConsistentHorizontalWalls(this.getSquare(xCoordinate, yCoordinate).hasWallAt(Direction.EAST), xCoordinate + 1, yCoordinate);
		}
		return consistent;
	}

	private boolean southNeighboursHaveConsistenVerticalWalls(boolean mustHaveNorthWall, int xCoordinate, int yCoordinate){
		boolean stillOnBoard = this.board.size() > yCoordinate;
		boolean consistent = true;
		if(stillOnBoard){
			consistent = this.getSquare(xCoordinate, yCoordinate).hasWallAt(Direction.NORTH) == mustHaveNorthWall;
			consistent &= southNeighboursHaveConsistenVerticalWalls(this.getSquare(xCoordinate, yCoordinate).hasWallAt(Direction.SOUTH), xCoordinate, yCoordinate + 1);
		}
		return consistent;
	}

	public boolean allLasersOnWalls(){
		for(Laser laser : lasers){
			Square laserSquare = this.getSquare(laser.getxCoordinate(), laser.getyCoordinate());
			boolean laserIsOnWall = laserSquare.hasWallAt(laser.getOrientation().getReverse());
			if(!laserIsOnWall) return false;
		}
		return true;
	}

	public int getWidth(){
		return this.board.get(0).size();
	}

	public int getHeight(){
		return this.board.size();
	}	

	public Square getSquare(int x, int y){
		return this.board.get(y).get(x);
	}
}