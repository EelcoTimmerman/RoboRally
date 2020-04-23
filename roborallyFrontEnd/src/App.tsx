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
        const gamestate = await response.json();
        setGamestate(gamestate);
    }

    getGameState();

    if(gamestate != undefined)
        return <Board squares = {gamestate}></Board>
    else
        return <div>Loading...</div>;
}