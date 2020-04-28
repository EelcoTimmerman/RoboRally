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
        programCard(0);
    }

    async function turnRight(){
        programCard(1);
    }

    async function turnLeft(){
        programCard(2);
    }

    async function uTurn(){
        programCard(3);
    }

    async function moveForward2(){
        programCard(4);
    }

    async function moveForward3(){
        programCard(5);
    }

    async function programCard(cardnr:number){
        console.log("Programming card: " + cardnr);
        const response = await fetch("roborally/api/program/" + cardnr, {
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
                    <button onClick={uTurn}>Turn around</button>
                    <button onClick={moveForward2}>Forward x 2</button>
                    <button onClick={moveForward3}>Forward x 3</button>
                </div>);
    }
    else{
        getGameState();
        return <div>Loading...</div>;
    }
}