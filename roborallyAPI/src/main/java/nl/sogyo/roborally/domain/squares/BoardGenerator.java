package nl.sogyo.roborally.domain.squares;

import java.util.LinkedList;

import nl.sogyo.roborally.domain.Direction;

public class BoardGenerator {

	public static LinkedList<LinkedList> generateBoard(String boardString){
		boardString = "E-E*NR*180-WE";
		String[] boardStringElements = boardString.split("\\*");
		LinkedList<LinkedList> board = new LinkedList<LinkedList>();
		LinkedList<Square> row = new LinkedList<Square>();
		board.add(row);
		for(String elem:boardStringElements) {
			readBoardElement(elem, board);
		}
		return board;
	}
	
	private static void readBoardElement(String elem, LinkedList<LinkedList> board) {
		if(elem.equals("NR")) {
			LinkedList<Square> row = new LinkedList<Square>();
			board.add(row);
		}else {
			addSquareToRow(elem, board.getLast());
			addNeighboursToNewSquare(board);
		}
	}
	
	private static void addSquareToRow(String stringSquare, LinkedList<Square> row){
		Square square;
		String squareType = stringSquare.split("-")[0];
		switch(squareType) {
		  case "C": square = new Checkpoint(); break;
		  case "E": square = new EmptySquare(); break;
		  case "180": square = new Gear180(); break;
		  case "L": square = new GearLeft(); break;
		  case "R": square = new GearRight(); break;
		  case "P": square = new Pit(); break;
		  case "CSE": square = new SlowConveyorbelt(Direction.EAST); break;
		  case "CSW": square = new SlowConveyorbelt(Direction.WEST); break;
		  case "CSN": square = new SlowConveyorbelt(Direction.NORTH); break;
		  case "CSS": square = new SlowConveyorbelt(Direction.SOUTH); break;	  
		  default:
		    square = null;
		}
		if(stringSquare.contains("-")) {
			String walls = stringSquare.split("-")[1];
			square.setWalls(walls);
		}
		row.add(square);
	}

	private static void addNeighboursToNewSquare(LinkedList<LinkedList> board) {
		Square newSquare = (Square) board.getLast().getLast();
		if(board.size() > 1) {
			addNorthAndSouth();
		}
		if(board.getLast().size() > 1) {
			addEastAndWest();
		}
	}
	
	private static void addNorthAndSouth() {
		
	}

	private static void addEastAndWest() {
		
	}
}

