package nl.sogyo.roborally.domain.squares;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	public boolean wallsAreConsistent(){
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

	public Board(String boardString){
		if(!coherentBoardStringCheck(boardString)){
			throw new RuntimeException("Your walls don't match.");
		}
		if(!rectangularBoardCheck(boardString)){
			throw new RuntimeException("Not a rectangular board.");
		}
		board = generateBoard(boardString);
	}

	private ArrayList<ArrayList<Square>> generateBoard(String boardString){
		ArrayList<ArrayList<Square>> board = new ArrayList<ArrayList<Square>>();
		String[] rowStrings = boardString.split("\\*\\|\\|\\*");
		for(String rowString : rowStrings){
			ArrayList<Square> row = new ArrayList<Square>();
			String[] squareStrings = rowString.split("\\*");
			for(String squareString : squareStrings){
				row.add(generateSquareType(squareString));
			}
			board.add(row);
		}
		return board;
	}

	private Square generateSquareType(String stringSquare){
		Square square;
		String squareType = stringSquare.split("-")[0];
		String walls = stringSquare.split("-")[1];
		switch(squareType){
			case "CH":
				square = new Checkpoint();
				break;
			case "ES":
				square = new EmptySquare();
				break;
			case "180":
				square = new Gear180();
				break;
			case "GL":
				square = new GearLeft();
				break;
			case "GR":
				square = new GearRight();
				break;
			case "PT":
				square = new Pit();
				break;
			case "CSE":
				square = new SlowConveyorbelt(Direction.EAST);
				break;
			case "CSW":
				square = new SlowConveyorbelt(Direction.WEST);
				break;
			case "CSN":
				square = new SlowConveyorbelt(Direction.NORTH);
				break;
			case "CSS":
				square = new SlowConveyorbelt(Direction.SOUTH);
				break;
			default:
				square = null;
		}
		square.setWalls(walls);
		return square;
	}

	private  boolean coherentBoardStringCheck(String boardString){
		List<List<String>> board = new ArrayList<List<String>>();
		String[] boardRows = boardString.split("\\|\\|");
		for(String row : boardRows){
			if(row.charAt(0) == '*'){
				row = row.substring(1);
			}
			List<String> rowAsList = Arrays.asList(row.split("\\*"));
			for(int i = 0; i < rowAsList.size(); i++){
			    String square = rowAsList.get(i);
				square = square.split("-")[1];
				rowAsList.set(i, square);
			}
			board.add(rowAsList);
		}
		for(int rowNo = 0; rowNo < board.size(); rowNo++){
			for(int colNo = 0; colNo < board.get(rowNo).size(); colNo++){
				if(colNo != board.get(rowNo).size()-1){
					if(board.get(rowNo).get(colNo).contains("E") && !board.get(rowNo).get(colNo+1).contains("W")){
						return false;
					}
				}
				if(rowNo != board.size()-1) {
					if(board.get(rowNo).get(colNo).contains("S") && !board.get(rowNo+1).get(colNo).contains("N")){
						return false;
					}
				}
			}
		}
		return true;
	}

	private  boolean rectangularBoardCheck(String boardString){
		String[] rows = boardString.split("\\*\\|\\|\\*");
		int colCount = rows[0].split("\\*").length;
		for(String row : rows)
			if((row.length() - row.replace("*", "").length() + 1) != colCount)
				return false;
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