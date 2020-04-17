import React, { useState } from "react";
import { Square } from "./board/Square";
import { Board } from "./board/Board";
import { Robot } from "./Robot";

export function App() {
    let squares : Square[][] = tempGetSquares();
    return <Board squares = {squares}></Board>
}

function tempGetSquares():Square[][]{
    let result: Square[][] = [];
    for(let i = 0; i < 5; i++){
        let row: Square[] = [];
        for(let j = 0; j < 5; j++){
            row.push({
                type: "Empty",
                northwall: false,
                eastwall: true,
                southwall: false,
                westwall: true,
                robot: undefined,
            });
        }
        result.push(row);
    }
    let robot: Robot = {
        name: "Testrobot",
        orientation: "North",
    }
    result[2][3].robot = robot;
    return result;
}