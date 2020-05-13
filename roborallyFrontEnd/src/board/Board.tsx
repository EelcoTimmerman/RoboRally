import React, { Component } from "react";
import { Square } from "./Square";
import { Robot } from "../Robot";
import { Laser, createLaserElement } from "./Laser";

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
    return row.map((square: Square, index: number) => createSquare(square, rowNumber, index));
}

function createSquare(square: Square, rowNumber: number, columnNumber: number){
    let style: React.CSSProperties = {
        position: "relative",
        gridColumnStart: columnNumber + 1,
        gridRowStart: rowNumber + 1,
    }
    let borderwidth = "20px";
    let bordercolour = "rgb(153, 153, 8)";

    if(square.northwall){
        style.borderTopWidth = borderwidth;
        style.borderTopColor = bordercolour;
    }
    if(square.eastwall){
        style.borderRightWidth = borderwidth;
        style.borderRightColor = bordercolour;
    }
    if(square.southwall){
        style.borderBottomWidth = borderwidth;
        style.borderBottomColor = bordercolour;
    }
    if(square.westwall){
        style.borderLeftWidth = borderwidth;
        style.borderLeftColor = bordercolour;
    }

    let squareText = square.type;
    let robotElement: JSX.Element = <div></div>;
    if(square.robot != undefined){
        robotElement = createRobot(square.robot);
    }

    let laserElements: JSX.Element[] = [];
    if(square.lasers != undefined){
        square.lasers.forEach(laser => {
            laserElements.push(createLaserElement(laser));        
        });
    }

    return (<div key={(columnNumber + 1) * (rowNumber + 1)} style={style}>
            {squareText}
            {robotElement}
            {laserElements}
        </div>);
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

function createRobot(robot: Robot):JSX.Element{
    let classname: string = robot.orientation + "-arrow";
    return (<div className="inner-square" style={{backgroundColor: robot.colour}}>
        <div className={classname}>
            
        </div>
    </div>);
}