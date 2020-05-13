package nl.sogyo.roborally.domain.squares;

import java.util.ArrayList;

import nl.sogyo.roborally.domain.Direction;
import nl.sogyo.roborally.domain.elements.Laser;

public class Board{

	private ArrayList<ArrayList<Square>> squares;
	private ArrayList<Laser> lasers;

	public ArrayList<ArrayList<Square>> getBoard(){
		return this.squares;
	}


	public Board(){
		this.squares = new ArrayList<>();
		this.lasers = new ArrayList<>();
	}

	public void addRow(ArrayList<Square> row){
		this.squares.add(row);
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
		for(ArrayList<Square> row : this.squares){
			if(row.size() != this.getWidth()) return false;
		}
		return true;
	}

	private boolean eastNeighboursHaveConsistentHorizontalWalls(boolean mustHaveWestWall, int xCoordinate, int yCoordinate){
		boolean stillOnBoard = this.squares.get(0).size() > xCoordinate;
		boolean consistent = true;
		if(stillOnBoard){
			consistent = this.getSquare(xCoordinate, yCoordinate).hasWallAt(Direction.WEST) == mustHaveWestWall;
			consistent &= eastNeighboursHaveConsistentHorizontalWalls(this.getSquare(xCoordinate, yCoordinate).hasWallAt(Direction.EAST), xCoordinate + 1, yCoordinate);
		}
		return consistent;
	}

	private boolean southNeighboursHaveConsistenVerticalWalls(boolean mustHaveNorthWall, int xCoordinate, int yCoordinate){
		boolean stillOnBoard = this.squares.size() > yCoordinate;
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
		return this.squares.get(0).size();
	}

	public int getHeight(){
		return this.squares.size();
	}	

	public Square getSquare(int x, int y){
		return this.squares.get(y).get(x);
	}

	public ArrayList<Laser> getLasers(){
		return lasers;
	}
}