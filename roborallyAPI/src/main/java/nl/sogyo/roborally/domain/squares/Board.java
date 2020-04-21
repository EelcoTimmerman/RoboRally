package nl.sogyo.roborally.domain.squares;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nl.sogyo.roborally.domain.Direction;

public class Board{

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

	public ArrayList<ArrayList<Square>> getBoard(){
		return this.board;
	}

	public void setBoard(ArrayList<ArrayList<Square>> board){
		this.board = board;
	}


	public Board(){
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

	public ArrayList<ArrayList<Square>> generateBoard(String boardString){
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

	protected Square getSquare(int x, int y){
		return this.board.get(x).get(y);
	}

	public Square generateSquareType(String stringSquare){
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
}