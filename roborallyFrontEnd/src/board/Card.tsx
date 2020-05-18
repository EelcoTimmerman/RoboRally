import React from "react";
import { ArrowUpward, Undo, Rotate90DegreesCcw, ArrowDownward } from "@material-ui/icons"
import { Badge } from "@material-ui/core";

export interface Card{
    name: string,
    speed: number
}


export function CardElement({ name, speed }: Card){
    let cardimage = getCardImage(name);
    return (<div className="card">
        {cardimage}
        <br/>
        {speed}
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