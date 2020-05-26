import React, { Component } from "react";
import { makeStyles } from '@material-ui/styles';
import { CardElement, Card } from "./Card";

interface CardsInHandProps{
  cards: Card[],
  onClick(cardid: number): void, 
}

export function CardsInhand({cards, onClick}: CardsInHandProps){
    let mycarddivs = cards.map((card: Card, index: number) => <CardElement card={card} key={card.speed} onClick={onClick}/>);
    return (      
      <div className = "cardsinhandgrid">
        {mycarddivs}
      </div>
    );
}