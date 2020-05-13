import React from "react";
import { Square } from "./Square";
import { Robot } from "../Robot";
import { Laser } from "./Laser";

interface BoardProps{
    squares: Square[][];
    robots: Robot[];
    lasers: Laser[];
}
export function Board({ squares, robots, lasers }: BoardProps){
    resetSquares(squares);
    for(let i = 0; i < robots.length; i++){
        addRobotToBoard(robots[i], squares);
    }
    for(let i = 0; i < lasers.length; i++){
        addLaserToBoard(lasers[i], squares);
    }
    let board = squares.map((row: Square[], index: number) => 
        createRow(row, index)
    );
    return (
        <div className="boardgrid">
            {board}
        </div>
    );
}

function createRow(row: Square[], rowNumber: number):JSX.Element[]{
    return row.map((square: Square, index: number) => square.render(rowNumber, index));
}

function addRobotToBoard(robot: Robot, board: Square[][]){
    board[robot.yCoordinate][robot.xCoordinate].robot = robot;
}

function addLaserToBoard(laser: Laser, board: Square[][]){
    let square = board[laser.yCoordinate][laser.xCoordinate];
    square.addLaser(laser);
    propagateLaserbeam(laser, laser.xCoordinate, laser.yCoordinate, board);
}

function propagateLaserbeam(laser: Laser, xCoordinate: number, yCoordinate: number, board: Square[][]){
    if(laser.orientation == "East"){
        let currentSquare = board[yCoordinate][xCoordinate];
        currentSquare.addLaserbeam({direction: "East", firepower: laser.firepower});
        if(!currentSquare.eastwall && currentSquare.robot == undefined && xCoordinate < board[0].length - 1){
            propagateLaserbeam(laser, xCoordinate + 1, yCoordinate, board);
        }
    }
}

function resetSquares(squares: Square[][]){
    squares.forEach(row => {
        row.forEach(square => square.reset());
    });
}
