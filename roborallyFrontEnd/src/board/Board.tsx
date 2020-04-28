import React, { Component } from "react";
import { Square } from "./Square";
import { Robot } from "../Robot";

interface BoardProps{
    squares: Square[][];
}
export function Board({ squares }: BoardProps){
    let rows = squares.map((row: Square[], index: number) => 
        createRow(row, index)
    );
    return (
        <div className="boardgrid">
            {rows}
        </div>
    );
}

function createRow(row: Square[], rowNumber: number):JSX.Element[]{
    return row.map((square: Square, index: number) => createSquare(square, rowNumber, index));
}

function createSquare(square: Square, rowNumber: number, columnNumber: number):JSX.Element{
    let style: React.CSSProperties = {
        gridColumnStart: columnNumber + 1,
        gridRowStart: rowNumber + 1,
    }

    if(square.northwall){
        style.borderTopWidth = "20px";
        style.borderTopColor = "rgb(153, 153, 8)";
    }
    if(square.eastwall){
        style.borderRightWidth = "20px";
        style.borderRightColor = "rgb(153, 153, 8)";
    }
    if(square.southwall){
        style.borderBottomWidth = "20px";
        style.borderBottomColor = "rgb(153, 153, 8)";
    }
    if(square.westwall){
        style.borderLeftWidth = "20px";
        style.borderLeftColor = "rgb(153, 153, 8)";
    }

    let squareText = square.type;
<<<<<<< roborallyFrontEnd/src/board/Board.tsx
    let robotElement : JSX.Element = <div></div>;
    if(square.robot != undefined){
        robotElement = createRobot(square.robot);
        // squareText += "\n " + square.robot.name + " " + square.robot.orientation;
=======

    if(square.robot != null){
        squareText += "\n " + square.robot.name + " " + square.robot.orientation;
>>>>>>> roborallyFrontEnd/src/board/Board.tsx
    }

    return (<div key={(columnNumber + 1) * (rowNumber + 1)} style={style}>
            {squareText}
            {robotElement}
        </div>);
}

function createRobot(robot: Robot):JSX.Element{
    let classname:string = robot.orientation + "-arrow";
    return (<div className="inner-square">
        <div className={classname}>
            
        </div>
    </div>);
}