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

    return (<div key={(columnNumber + 1) * (rowNumber + 1)} style={style}>
            {squareText}
            {robotElement}
        </div>);
}

function createRobot(robot: Robot):JSX.Element{
    let classname: string = robot.orientation + "-arrow";
    return (<div className="inner-square" style={{backgroundColor: robot.colour}}>
        <div className={classname}>
            
        </div>
    </div>);
}