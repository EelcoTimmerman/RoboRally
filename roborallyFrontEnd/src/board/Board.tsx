import React, { Component } from "react";
import { Square, SquareElement } from "./Square";
import { Robot } from "../Robot";
import { Laser } from "./Laser";

interface BoardProps{
    squares: Square[][];
    robots: Robot[];
    lasers: Laser[];
}
export function Board({ squares, robots, lasers }: BoardProps){
    let squaresCopy = JSON.parse(JSON.stringify(squares));
    for(let i = 0; i < robots.length; i++){
        addRobotToBoard(robots[i], squaresCopy);
    }
    for(let i = 0; i < lasers.length; i++){
        addLaserToBoard(lasers[i], squaresCopy);
    }
    let board = squaresCopy.map((row: Square[], index: number) => 
        createRow(row, index)
    );
    return (
        <div className="boardgrid">
            {board}
        </div>
    );
}

function createRow(row: Square[], rowNumber: number):JSX.Element[]{
    return row.map((square: Square, index: number) => <SquareElement square={square} rowNumber={rowNumber} columnNumber={index} key={"" + rowNumber + index}></SquareElement>);
}

function addRobotToBoard(robot: Robot, board: Square[][]){
    board[robot.yCoordinate][robot.xCoordinate].robot = robot;
}

function addLaserToBoard(laser: Laser, board: Square[][]){
    let square = board[laser.yCoordinate][laser.xCoordinate];
    if(square.lasers == undefined){
        square.lasers = [laser];
    }
    else{
        square.lasers.push(laser);
    }
}