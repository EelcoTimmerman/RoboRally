import React from "react";
import { ArrowUpward, Undo, Rotate90DegreesCcw, ArrowDownward } from "@material-ui/icons"
import { Badge } from "@material-ui/core";

export interface Card{
    name: string,
    speed: number,
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
        case "forward": return <ArrowUpward/>
        case "right": return <Undo style={{transform: "scaleX(-1)"}}/>
        case "left": return <Undo/>
        case "turn around": return <Rotate90DegreesCcw style={{transform: "rotate(90deg) scaleX(-1)"}}/>
        case "forwardx2": return <Badge badgeContent={2}><ArrowUpward/></Badge>
        case "forwardx3": return <Badge badgeContent={3}><ArrowUpward/></Badge>
        case "backwards": return <ArrowDownward/>
    }
}