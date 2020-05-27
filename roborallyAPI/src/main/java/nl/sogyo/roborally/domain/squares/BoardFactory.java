package nl.sogyo.roborally.domain.squares;

import java.util.ArrayList;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.elements.Laser;

public class BoardFactory{
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

	public static Board createBoardlaserTestBoard(){
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

		square12.eastWall = true;
		
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
		Laser laser2 = new Laser(0,1, Direction.EAST, 2);
		Laser laser3 = new Laser(1,1, Direction.NORTH, 1);
		Laser laser4 = new Laser(1,2, Direction.WEST, 3);

		laserTestBoard.addLaser(laser1);
		laserTestBoard.addLaser(laser2);
		laserTestBoard.addLaser(laser3);
		laserTestBoard.addLaser(laser4);

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

	public static Board createSlowConveyorbeltRotationTestBoard(){
		Board slowConveryorbeltRotationTestBoard = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new SlowConveyorbelt(Direction.EAST));
		row2.add(new SlowConveyorbelt(Direction.NORTH));
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new SlowConveyorbelt(Direction.NORTH));
		row3.add(new SlowConveyorbelt(Direction.NORTH));

		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new SlowConveyorbelt(Direction.NORTH));
		row4.add(new SlowConveyorbelt(Direction.NORTH));

		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new SlowConveyorbelt(Direction.EAST));
		row5.add(new SlowConveyorbelt(Direction.WEST));

		slowConveryorbeltRotationTestBoard.addRow(row1);
		slowConveryorbeltRotationTestBoard.addRow(row2);
		slowConveryorbeltRotationTestBoard.addRow(row3);
		slowConveryorbeltRotationTestBoard.addRow(row4);
		slowConveryorbeltRotationTestBoard.addRow(row5);

		return slowConveryorbeltRotationTestBoard;
	}

	public static Board createMultipleRobotsOnSlowConveyorbeltTestBoard(){
		Board multipleRobotsOnSlowConveyorbeltTestBoard = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new SlowConveyorbelt(Direction.NORTH, "N"));
		row1.add(new EmptySquare());

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new SlowConveyorbelt(Direction.NORTH));
		row2.add(new SlowConveyorbelt(Direction.NORTH));
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new SlowConveyorbelt(Direction.NORTH));
		row3.add(new SlowConveyorbelt(Direction.NORTH));

		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new SlowConveyorbelt(Direction.NORTH));
		row4.add(new SlowConveyorbelt(Direction.NORTH));

		multipleRobotsOnSlowConveyorbeltTestBoard.addRow(row1);
		multipleRobotsOnSlowConveyorbeltTestBoard.addRow(row2);
		multipleRobotsOnSlowConveyorbeltTestBoard.addRow(row3);
		multipleRobotsOnSlowConveyorbeltTestBoard.addRow(row4);

		return multipleRobotsOnSlowConveyorbeltTestBoard;
	}

	public static Board createMultipleRobotsOnSlowConveyorbeltWithPitTestBoard(){
		Board multipleRobotsOnSlowConveyorbeltWithPitTestBoard = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new SlowConveyorbelt(Direction.NORTH));
		row1.add(new Pit());

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new SlowConveyorbelt(Direction.NORTH));
		row2.add(new SlowConveyorbelt(Direction.NORTH));
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new SlowConveyorbelt(Direction.NORTH));
		row3.add(new SlowConveyorbelt(Direction.NORTH));

		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new SlowConveyorbelt(Direction.NORTH));
		row4.add(new SlowConveyorbelt(Direction.NORTH));

		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());

		ArrayList<Square> row6 = new ArrayList<>();
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());

		multipleRobotsOnSlowConveyorbeltWithPitTestBoard.addRow(row1);
		multipleRobotsOnSlowConveyorbeltWithPitTestBoard.addRow(row2);
		multipleRobotsOnSlowConveyorbeltWithPitTestBoard.addRow(row3);
		multipleRobotsOnSlowConveyorbeltWithPitTestBoard.addRow(row4);
		multipleRobotsOnSlowConveyorbeltWithPitTestBoard.addRow(row5);
		multipleRobotsOnSlowConveyorbeltWithPitTestBoard.addRow(row6);

		return multipleRobotsOnSlowConveyorbeltWithPitTestBoard;
	}

	public static Board createMultipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard(){
		Board multipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new EmptySquare());
		row1.add(new SlowConveyorbelt(Direction.SOUTH));
		row1.add(new EmptySquare());

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new SlowConveyorbelt(Direction.EAST));
		row2.add(new Pit());
		row2.add(new SlowConveyorbelt(Direction.WEST));
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new EmptySquare());
		row3.add(new SlowConveyorbelt(Direction.NORTH));
		row3.add(new EmptySquare());

		multipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard.addRow(row1);
		multipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard.addRow(row2);
		multipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard.addRow(row3);
		
		return multipleRobotsPushedToSameSquareBySlowConveyorbeltWithPitTestBoard;
	}

	public static Board createMultipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard(){
		Board multipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new EmptySquare());
		row1.add(new SlowConveyorbelt(Direction.SOUTH));
		row1.add(new EmptySquare());

		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new SlowConveyorbelt(Direction.EAST));
		row2.add(new EmptySquare());
		row2.add(new SlowConveyorbelt(Direction.WEST));
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new EmptySquare());
		row3.add(new SlowConveyorbelt(Direction.NORTH));
		row3.add(new EmptySquare());

		multipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard.addRow(row1);
		multipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard.addRow(row2);
		multipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard.addRow(row3);
		
		return multipleRobotsPushedToSameSquareBySlowConveyorbeltWithoutPitTestBoard;
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

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare("N"));
		row1.add(new EmptySquare());

		
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new EmptySquare("W"));
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		row3.add(new EmptySquare());
		row3.add(new EmptySquare("E"));
		
		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new EmptySquare());
		row4.add(new EmptySquare("S"));
		row4.add(new EmptySquare());
		row4.add(new Checkpoint());

		defaultBoard.addRow(row1);
		defaultBoard.addRow(row2);
		defaultBoard.addRow(row3);
		defaultBoard.addRow(row4);

		return defaultBoard;
	
	}

	public static Board createWinningBoard(){
		Board winningBoard = new Board();
		ArrayList<Square> row1 = new ArrayList<>();
		ArrayList<Square> row2 = new ArrayList<>();
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new FinalCheckPoint());
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		winningBoard.addRow(row1);
		winningBoard.addRow(row2);
		return winningBoard;
	} 

	public static Board createSmallCompleteBoard(){
		Board smallCompleteBoard = new Board();

		ArrayList<Square> row1 = new ArrayList<>();
		row1.add(new EmptySquare());
		row1.add(new EmptySquare("S"));
		row1.add(new EmptySquare());
		row1.add(new EmptySquare());
		row1.add(new EmptySquare("S"));
		row1.add(new EmptySquare());
		
		ArrayList<Square> row2 = new ArrayList<>();
		row2.add(new EmptySquare("E"));
		row2.add(new GearRight("NW"));
		row2.add(new EmptySquare());
		row2.add(new EmptySquare());
		row2.add(new GearLeft("NE"));
		row2.add(new EmptySquare("W"));
		
		ArrayList<Square> row3 = new ArrayList<>();
		row3.add(new EmptySquare());
		row3.add(new Checkpoint());
		row3.add(new SlowConveyorbelt(Direction.EAST));
		row3.add(new SlowConveyorbelt(Direction.SOUTH));
		row3.add(new Pit());
		row3.add(new EmptySquare());
		
		ArrayList<Square> row4 = new ArrayList<>();
		row4.add(new EmptySquare());
		row4.add(new Pit());
		row4.add(new SlowConveyorbelt(Direction.NORTH));
		row4.add(new SlowConveyorbelt(Direction.WEST));
		row4.add(new Checkpoint());
		row4.add(new EmptySquare());
		
		ArrayList<Square> row5 = new ArrayList<>();
		row5.add(new EmptySquare("E"));
		row5.add(new Gear180("SW"));
		row5.add(new EmptySquare());
		row5.add(new EmptySquare());
		row5.add(new Gear180("SE"));
		row5.add(new EmptySquare("W"));
		
		ArrayList<Square> row6 = new ArrayList<>();
		row6.add(new EmptySquare());
		row6.add(new EmptySquare("N"));
		row6.add(new EmptySquare());
		row6.add(new EmptySquare());
		row6.add(new EmptySquare("N"));
		row6.add(new FinalCheckPoint());

		smallCompleteBoard.addRow(row1);
		smallCompleteBoard.addRow(row2);
		smallCompleteBoard.addRow(row3);
		smallCompleteBoard.addRow(row4);
		smallCompleteBoard.addRow(row5);
		smallCompleteBoard.addRow(row6);

		Laser laser1 = new Laser(1, 1, Direction.EAST, 3);
		Laser laser2 = new Laser(4, 1, Direction.SOUTH, 2);
		Laser laser3 = new Laser(4, 4, Direction.WEST, 3);
		Laser laser4 = new Laser(1, 4, Direction.NORTH, 2);

		smallCompleteBoard.addLaser(laser1);
		smallCompleteBoard.addLaser(laser2);
		smallCompleteBoard.addLaser(laser3);
		smallCompleteBoard.addLaser(laser4);
		return smallCompleteBoard;
	}
}