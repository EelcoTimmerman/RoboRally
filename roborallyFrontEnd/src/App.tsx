import React, { useState } from "react";
import { Square } from "./board/Square";
import { Board } from "./board/Board";
import { Startscreen } from "./Startscreen";
import { Robot } from "./Robot";
import { incomingMessage } from "./incomingMessage";
import { PlayerList } from "./PlayerList";
import { Card } from "./board/Card";
import { CardsInhand } from "./board/CardsInHand";
import { Powerbutton } from "./Powerbutton";
import { Laser } from "./board/Laser";

export function App() {
    const [ board, setBoard ] = useState<Square[][] | undefined>(undefined);
    const [ robots, setRobots ] = useState<Robot[] | undefined>(undefined);
    const [ cards, setCards ] = useState<Card[] | undefined>(undefined);
    const [ websocket, setWebsocket ] = useState<WebSocket | undefined>(undefined);
    const [ powerstatus, setPowerstatus ] = useState("Active");
    const [ lasers, setLasers ] = useState<Laser[] | undefined>(undefined);

    if(board != undefined && robots != undefined && lasers != undefined && cards != undefined){
        return (<div>
                    <Board squares = {board} robots={robots} lasers={lasers}></Board>
                    <button onClick={() => programCard(0)}>Forward</button>
                    <button onClick={() => programCard(1)}>Right</button>
                    <button onClick={() => programCard(2)}>Left</button>
                    <button onClick={() => programCard(3)}>Turn around</button>
                    <button onClick={() => programCard(4)}>Forward x 2</button>
                    <button onClick={() => programCard(5)}>Forward x 3</button>
                    <button onClick={() => programCard(6)}>Backwards</button>
                    <Powerbutton powerstatus={powerstatus} onClick={() => powerDown()}/>
                    <PlayerList players={robots}></PlayerList>
                    <CardsInhand cards = {cards} onClick={programCard}></CardsInhand>
                </div>);
    }
    else{
        return <Startscreen login={initialiseConnection}></Startscreen>;
    }
    
    async function initialiseConnection(name: string){
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
                if(message.messagetype == "boardstate") createBoard(message.body);
                else if(message.messagetype == "robots") setRobots(message.body);
                else if(message.messagetype == "drawncards")setCards(message.body);              
                else if(message.messagetype == "powerstatus") setPowerstatus(message.body);
                else if(message.messagetype == "lasers") setLasers(message.body);
            };

            tempwebsocket.onclose = function(event: WebSocketCloseEvent){
                console.log("connection closed");
            };
        }

        setWebsocket(tempwebsocket);
    }

    function createBoard(squares: Square[][]){
        let board = squares.map(row => row.map(square => new Square(square.type, square.northwall, square.eastwall, square.southwall, square.westwall)));
        setBoard(board);
    }

    async function programCard(cardnr:number){
        if (websocket !== undefined && websocket.readyState !== WebSocket.CLOSED) {
            websocket.send(cardnr.toString());
        }
        else{
            console.log("No connection.");
        }
    }

    async function powerDown(){
        if (websocket !== undefined && websocket.readyState !== WebSocket.CLOSED) {
            websocket.send("switchpower");
        }
        else{
            console.log("No connection.");
        }
    }
}

