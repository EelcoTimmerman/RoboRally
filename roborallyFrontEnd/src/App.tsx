import React, { useState } from "react";
import { Square } from "./board/Square";
import { Board } from "./board/Board";
import { showCards } from "./board/CardsInHand";

export function App() {
    const [gamestate, setGamestate] = useState<Square[][] | undefined>(undefined);
    const [websocket, setWebsocket] = useState<WebSocket | undefined>(undefined);

    async function getGameState(){
        if (websocket !== undefined && websocket.readyState !== WebSocket.CLOSED) {
            return;
        }
        let tempwebsocket = new WebSocket("ws://localhost:3000/roborally/websocket");

        if(tempwebsocket !== undefined && tempwebsocket.readyState !== WebSocket.CLOSED){

            tempwebsocket.onopen = function(){
                console.log("connected");
                tempwebsocket.send("initialize");
            };

            tempwebsocket.onmessage = function(event: WebSocketMessageEvent){
                let gamestate = JSON.parse(event.data);
                setGamestate(gamestate);
            };

            tempwebsocket.onclose = function(event: WebSocketCloseEvent){
                console.log("connection closed");
            };

        }

        setWebsocket(tempwebsocket);
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

    async function moveBackwards(){
        programCard(6);
    }

    async function programCard(cardnr:number){
        if (websocket !== undefined && websocket.readyState !== WebSocket.CLOSED) {
            websocket.send(cardnr.toString());
        }
        else{
            console.log("No connection.");
        }
    }

    let cards = showCards();

    if(gamestate != undefined && cards != undefined){
        return (<div>
                    <Board squares = {gamestate}></Board>
                    <button onClick={moveForward}>Forward</button>
                    <button onClick={turnRight}>Right</button>
                    <button onClick={turnLeft}>Left</button>
                    <button onClick={uTurn}>Turn around</button>
                    <button onClick={moveForward2}>Forward x 2</button>
                    <button onClick={moveForward3}>Forward x 3</button>
                    <button onClick={moveBackwards}>Backwards</button>
                    <div>{cards}</div>
                </div>);
    }
    else{
        getGameState();
        return <div>Loading...</div>;
    }
}