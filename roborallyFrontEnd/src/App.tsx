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

    async function moveForward(){
        console.log("moving forward");
        const response = await fetch("roborally/api/program/0", {
            method: 'PUT',
            headers:{
                'Accept': 'application/json'
            }
        });
        const gamestate = await response.json();
        setGamestate(gamestate);
    }

    async function turnRight(){
        console.log("turning right");
        const response = await fetch("roborally/api/program/1", {
            method: 'PUT',
            headers:{
                'Accept': 'application/json'
            }
        });
        const gamestate = await response.json();
        setGamestate(gamestate);

    }

    async function turnLeft(){
        console.log("turning left");
        const response = await fetch("roborally/api/program/2", {
            method: 'PUT',
            headers:{
                'Accept': 'application/json'
            }
        });
        const gamestate = await response.json();
        setGamestate(gamestate);

    }


    if(gamestate != undefined){
        return (<div>
                    <Board squares = {gamestate}></Board>
                    <button onClick={moveForward}>Forward</button>
                    <button onClick={turnRight}>Right</button>
                    <button onClick={turnLeft}>Left</button>
                </div>);
    }
    else{
        getGameState();
        return <div>Loading...</div>;
    }
}