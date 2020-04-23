import React, { useState } from "react";
import { Square } from "./board/Square";
import { Board } from "./board/Board";

export function App() {
    const [gamestate, setGamestate] = useState<Square[][] | undefined>(undefined);

    async function getGameState(){
        console.log("Initializing gamestate.");
        const response = await fetch("roborally/api/initialize", {
            method: 'GET',
            headers:{
                'Accept': 'application/json'
            }
        });
        const gamestate = await response.json();
        setGamestate(gamestate);
    }


    if(gamestate != undefined)
        return <Board squares = {gamestate}></Board>
    else{
        getGameState();
        return <div>Loading...</div>;
    }
}