import React, { Component } from "react";
import { makeStyles } from '@material-ui/styles';

interface Card{
    type: string,
}

function getCards(){
    const cards:Card[] = [];
    for(let i = 0; i < 9; i++){
            cards.push({
                type: "MoveOneForward",
            });
    }
    return cards;
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

export function showCards(){
    let myCards:Card[] = getCards();
    let mycarddivs = myCards.map((card: Card, index: number) => showcard(card, index));
    return (      
      <div className = "cardsinhandgrid">
        {mycarddivs}
      </div>
    );
}

function showcard(card: Card, index: number){
  const classes = useStyles();
  return (
      <div key={index} className={classes.paper}>{card.type}</div>
  )
}
