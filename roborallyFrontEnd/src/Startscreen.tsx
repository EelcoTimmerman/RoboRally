import React, { useState } from "react";

interface StartscreenProps{
    login(name:string): void, 
}

export function Startscreen({login}:StartscreenProps){
    const [ playerName, setPlayerName ] = useState("");
    return (
        <div>
            <input  value={playerName} onChange={(e) => setPlayerName(e.target.value)} placeholder="name"/>
            <button onClick={() => login(playerName)}>Start!</button>
        </div>
    );
}