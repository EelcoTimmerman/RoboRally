import React, { useState } from "react";
import { Square } from "./board/Square";
import { Board } from "./board/Board";
import { showCards } from "./board/CardsInHand";
import { Startscreen } from "./Startscreen";
import { Robot } from "./Robot";
import { incomingMessage } from "./incomingMessage";
import { PlayerList } from "./PlayerList";

export function App() {
    const [ board, setBoard ] = useState<Square[][] | undefined>(undefined);
    const [ robots, setRobots ] = useState<Robot[] | undefined>(undefined);
    const [websocket, setWebsocket] = useState<WebSocket | undefined>(undefined);

    if(board != undefined && robots != undefined){
        return (<div>
                    <Board squares = {board} robots={robots}></Board>
                    <button onClick={() => programCard(0)}>Forward</button>
                    <button onClick={() => programCard(1)}>Right</button>
                    <button onClick={() => programCard(2)}>Left</button>
                    <button onClick={() => programCard(3)}>Turn around</button>
                    <button onClick={() => programCard(4)}>Forward x 2</button>
                    <button onClick={() => programCard(5)}>Forward x 3</button>
                    <button onClick={() => programCard(6)}>Backwards</button>
                    <PlayerList players={robots}></PlayerList>
                </div>);
    }
    else{
        return <Startscreen login={getGameState}></Startscreen>;
    }
    
    async function getGameState(name: string){
        if (websocket !== undefined && websocket.readyState !== WebSocket.CLOSED) {
            return;
        }
        let tempwebsocket = new WebSocket("ws://localhost:3000/roborally/websocket");

        if(tempwebsocket !== undefined && tempwebsocket.readyState !== WebSocket.CLOSED){

            tempwebsocket.onopen = function(){
                console.log("connected");
                tempwebsocket.send("initialize " + name);
            };

            tempwebsocket.onmessage = function(event: WebSocketMessageEvent){
                let message: incomingMessage = JSON.parse(event.data);
                if(message.messagetype == "boardstate") setBoard(message.body);
                else if(message.messagetype == "robots") setRobots(message.body);
            };

            tempwebsocket.onclose = function(event: WebSocketCloseEvent){
                console.log("connection closed");
            };

        }

        setWebsocket(tempwebsocket);
    }

<<<<<<< roborallyFrontEnd/src/App.tsx


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
        console.log("hey");
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

=======
>>>>>>> roborallyFrontEnd/src/App.tsx
    async function programCard(cardnr:number){
        if (websocket !== undefined && websocket.readyState !== WebSocket.CLOSED) {
            websocket.send(cardnr.toString());
        }
        else{
            console.log("No connection.");
        }
    }
<<<<<<< roborallyFrontEnd/src/App.tsx

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
=======
}
>>>>>>> roborallyFrontEnd/src/App.tsx
