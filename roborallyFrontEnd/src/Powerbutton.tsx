import React from "react";

interface PowerbuttonProps{
    powerstatus: string,
    onClick(): void
}
export function Powerbutton({ powerstatus, onClick }: PowerbuttonProps){
    let backgroundcolour = "";
    if(powerstatus == "Active") backgroundcolour = "green";
    else backgroundcolour = "red";
    return <button style={{backgroundColor: backgroundcolour, fontSize: "40px"}} onClick={onClick}>Power</button>
}