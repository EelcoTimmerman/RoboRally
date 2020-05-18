import React, { Component } from "react";
import { makeStyles } from '@material-ui/styles';
import { CardElement } from "./Card";

interface Card{
    name: string,
    speed: number
}

interface allCards{
  cards: Card[]
}

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  paper: {
    background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
    textAlign: 'center'
  },
}));

export function CardsInhand({cards}: allCards){
    let mycarddivs = cards.map((card: Card, index: number) => <CardElement name={card.name} speed={card.speed} key={card.speed}/>);
    return (      
      <div className = "cardsinhandgrid">
        {mycarddivs}
      </div>
    );
}