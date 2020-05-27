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
import { ProgrammedCards } from "./board/ProgrammedCards";

export function App() {
    const [ board, setBoard ] = useState<Square[][] | undefined>(undefined);
    const [ robots, setRobots ] = useState<Robot[] | undefined>(undefined);
    const [ cardsInHand, setCardsInHand ] = useState<Card[]>([]);
    const [ websocket, setWebsocket ] = useState<WebSocket | undefined>(undefined);
    const [ powerstatus, setPowerstatus ] = useState("Active");
    const [ lasers, setLasers ] = useState<Laser[] | undefined>(undefined);
    const [ programmedCards, setProgrammedCards ] = useState<Card[]>([]);
    const [gameWinner, setWinner]  = useState<String | undefined>(undefined);

    if(board != undefined && robots != undefined && lasers != undefined && gameWinner == undefined){
        return (<div>
                    <Board squares = {board} robots={robots} lasers={lasers}></Board>
                    <Powerbutton powerstatus={powerstatus} onClick={() => powerDown()}/>
                    <PlayerList players={robots}></PlayerList>
                    <CardsInhand cards = {cardsInHand} onClick={programCard}></CardsInhand>
                    <ProgrammedCards cards={programmedCards} removeCard={unProgramCard} ready={endTurn}></ProgrammedCards>
                </div>);
    }
    else if(gameWinner != undefined){
        return(
        <div>Game Over. A long battle has been fought.. But after an immense power struggle,
            {gameWinner} has gained victory and will now continue to reign the universe until eternity.</div>
        )
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
                else if(message.messagetype == "drawncards"){
                    setCardsInHand(message.body);
                    setProgrammedCards([]);
                } 
                else if(message.messagetype == "powerstatus") setPowerstatus(message.body);
                else if(message.messagetype == "lasers") setLasers(message.body);
                else if(message.messagetype == "gameover") setWinner(message.body);
            };

            tempwebsocket.onclose = function(event: WebSocketCloseEvent){
                console.log("connection closed");
            };
        }

        setWebsocket(tempwebsocket);
    }

    // function setWinner(winner: String){
    //     console.log("reached winner", {winner});
    //     return { gameWinner : winner };
    // }

    function createBoard(squares: Square[][]){
        let board = squares.map(row => row.map(square => new Square(square.type, square.northwall, square.eastwall, square.southwall, square.westwall)));
        setBoard(board);
    }

    function programCard(cardid: number){
        let cardToProgram: Card|undefined = undefined;
        cardsInHand.forEach((card: Card) => {
            if(card.cardid == cardid){
                cardToProgram = card;
            }
        });
        let newCardsInHand = cardsInHand.filter((card: Card) => card.cardid != cardid);
        setCardsInHand(newCardsInHand);
        if(cardToProgram != undefined){
            setProgrammedCards([
                ...programmedCards,
                cardToProgram
            ]);
        }
    }

    function unProgramCard(cardid: number){
        let cardToUnProgram: Card|undefined = undefined;
        programmedCards.forEach((card: Card) => {
            if(card.cardid == cardid){
                cardToUnProgram = card;
            }
        });
        let newProgrammedCards = programmedCards.filter((card: Card) => card.cardid != cardid);
        setProgrammedCards(newProgrammedCards);
        if(cardToUnProgram != undefined){
            setCardsInHand([
                ...cardsInHand,
                cardToUnProgram
            ]);
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

    async function endTurn(cardids: number[]){        
        if (websocket !== undefined && websocket.readyState !== WebSocket.CLOSED) {
            websocket.send(JSON.stringify(cardids));
            console.log(cardids);
        }
        else{
            console.log("No connection.");
        }
    }
}

