import React, { Component } from "react";
import { CardElement, Card } from "./Card";

interface ProgrammedCardsProps{
    cards: Card[],
    ready(cardids: number[]): void,
    removeCard(cardid: number): void,
}

export function ProgrammedCards({cards, ready, removeCard}: ProgrammedCardsProps){
    let mycarddivs = cards.map((card: Card, index: number) => <CardElement card={card} key={card.speed} onClick={removeCard}/>);
    return (      
      <div className = "cardsinhandgrid">
        {mycarddivs}
        <button onClick={() => ready(cards.map((card: Card) => card.cardid))} disabled={cards.length != 5}>ready</button>
      </div>
    );
}