import React, { useState } from "react";
import { Square } from "./board/Square";
import { Board } from "./board/Board";
import { Startscreen } from "./Startscreen";
import { Robot } from "./Robot";
import { incomingMessage } from "./incomingMessage";
import { PlayerList } from "./PlayerList";
<<<<<<< HEAD
import { Card } from "./board/Card";
import { CardsInhand } from "./board/CardsInHand";
=======
import { Powerbutton } from "./Powerbutton";
>>>>>>> 26-board-lasers

export function App() {
    const [ board, setBoard ] = useState<Square[][] | undefined>(undefined);
    const [ robots, setRobots ] = useState<Robot[] | undefined>(undefined);
<<<<<<< HEAD
    const [cards, setCards ] = useState<Card[] | undefined>(undefined);
    const [websocket, setWebsocket] = useState<WebSocket | undefined>(undefined);    
=======
    const [ websocket, setWebsocket ] = useState<WebSocket | undefined>(undefined);
    const [ powerstatus, setPowerstatus ] = useState("Active");
>>>>>>> 26-board-lasers

    if(board != undefined && robots != undefined && cards != undefined){
        return (<div>
                    <Board squares = {board} robots={robots}></Board>
                    <button onClick={() => programCard(0)}>Forward</button>
                    <button onClick={() => programCard(1)}>Right</button>
                    <button onClick={() => programCard(2)}>Left</button>
                    <button onClick={() => programCard(3)}>Turn around</button>
                    <button onClick={() => programCard(4)}>Forward x 2</button>
                    <button onClick={() => programCard(5)}>Forward x 3</button>
                    <button onClick={() => programCard(6)}>Backwards</button>
                    <Powerbutton powerstatus={powerstatus} onClick={() => powerDown()}/>
                    <PlayerList players={robots}></PlayerList>
                    <CardsInhand cards = {cards}></CardsInhand>
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
<<<<<<< HEAD
                else if(message.messagetype == "drawncards")setCards(message.body);              
=======
                else if(message.messagetype == "powerstatus") setPowerstatus(message.body);
>>>>>>> 26-board-lasers
            };

            tempwebsocket.onclose = function(event: WebSocketCloseEvent){
                console.log("connection closed");
            };
        }

        setWebsocket(tempwebsocket);
    }

    async function programCard(cardnr:number){
        if (websocket !== undefined && websocket.readyState !== WebSocket.CLOSED) {
            websocket.send(cardnr.toString());
        }
        else{
            console.log("No connection.");
        }
    }
<<<<<<< HEAD
}
=======

    async function powerDown(){
        if (websocket !== undefined && websocket.readyState !== WebSocket.CLOSED) {
            websocket.send("switchpower");
        }
        else{
            console.log("No connection.");
        }
    }
}

>>>>>>> 26-board-lasers
