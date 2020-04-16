import React, { Component } from "react";
import { Square } from "./Square";

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
        style.borderTopWidth = "thick";
        style.borderTopColor = "rgb(153, 153, 8)";
    }
    if(square.eastwall){
        style.borderRightWidth = "thick";
        style.borderRightColor = "rgb(153, 153, 8)";
    }
    if(square.southwall){
        style.borderBottomWidth = "thick";
        style.borderBottomColor = "rgb(153, 153, 8)";
    }
    if(square.westwall){
        style.borderLeftWidth = "thick";
        style.borderLeftColor = "rgb(153, 153, 8)";
    }

    return <div key={(columnNumber + 1) * (rowNumber + 1)} style={style}>
            {square.type}
        </div>;
}