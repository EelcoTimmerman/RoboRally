import React, { useState } from "react";
import { Square } from "./board/Square";
import { Board } from "./board/Board";

export function App() {
    const [gamestate, setGamestate] = useState<Square[][] | undefined>(undefined);

    async function getGameState(){
        const response = await fetch("roborally/api/gamestate", {
            method: 'GET',
            headers:{
                'Accept': 'application/json'
            }
        });
        const gamestate:Square[][] = await response.json();
        setGamestate(gamestate);
    }

    getGameState();

    if(gamestate != undefined)
        return <Board squares = {gamestate}></Board>
    else
        return <div>Loading...</div>;
}

// function tempGetSquares():Square[][]{
//     let result: Square[][] = [];
//     for(let i = 0; i < 5; i++){
//         let row: Square[] = [];
//         for(let j = 0; j < 5; j++){
//             row.push({
//                 type: "Empty",
//                 northwall: false,
//                 eastwall: true,
//                 southwall: false,
//                 westwall: true,
//                 robot: undefined,
//             });
//         }
//         result.push(row);
//     }
//     let robot: Robot = {
//         name: "Testrobot",
//         orientation: "North",
//     }
//     result[2][3].robot = robot;
//     return result;
// }