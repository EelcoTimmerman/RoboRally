import React from "react";
import { ArrowUpward, Undo, Rotate90DegreesCcw, ArrowDownward } from "@material-ui/icons"
import { Badge } from "@material-ui/core";

export interface Card{
    name: string,
    speed: number,
    cardid: number
}

interface CardElementProps{
    card: Card,
    onClick(cardid: number): void,
}

export function CardElement({card, onClick}: CardElementProps){
    let cardimage = getCardImage(card.name);
    return (<div className="card" onClick={() => onClick(card.cardid)}>
        {cardimage}
        <br/>
        {card.speed}
    </div>);
}

function getCardImage(cardname: string){
    switch(cardname){
        case "MoveOneCard": return <ArrowUpward style={{fontSize: "60px"}}/>
        case "RotateRightCard": return <Undo style={{transform: "scaleX(-1)", fontSize: "60px"}}/>
        case "RotateLeftCard": return <Undo style={{fontSize: "60px"}}/>
        case "UTurnCard": return <Rotate90DegreesCcw style={{transform: "rotate(90deg) scaleX(-1)", fontSize: "60px"}}/>
        case "MoveTwoCard": return <Badge badgeContent={2}><ArrowUpward style={{fontSize: "60px"}}/></Badge>
        case "MoveThreeCard": return <Badge badgeContent={3}><ArrowUpward style={{fontSize: "60px"}}/></Badge>
        case "MoveBackCard": return <ArrowDownward style={{fontSize: "60px"}}/>
    }
}