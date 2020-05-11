import React from "react";
import { Robot } from "./Robot";

interface playerlistprops{
    players: Robot[],
}

export function PlayerList({ players }: playerlistprops){
    let playerlist = players.map((robot: Robot) => {
        return createPlayerEntry(robot);
    });
    let style: React.CSSProperties = {
        position: "absolute",
        top: "0px",
        right: "0px",
        minWidth: "350px",
    }
    return (
        <div style={style}>
            {playerlist}
        </div>
    )
}

function createPlayerEntry(robot: Robot){
    let style: React.CSSProperties = {
        color: robot.colour,
    };
    let readytext = "";
    if(robot.ready) readytext = " is ready";
    else readytext = " is programming, and active";
    if(robot.status == "Inactive") readytext = " has powered down.";
    return (
        <p style={style} key={robot.name}>
            {robot.name}({robot.hitpoints}){readytext}
        </p>
    );
}