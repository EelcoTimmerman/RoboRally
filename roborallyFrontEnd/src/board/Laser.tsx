import React from "react";
import { Colorize } from "@material-ui/icons";

export interface Laser{
    orientation: string,
    firepower: number,
    xCoordinate: number,
    yCoordinate: number,
}

export interface Laserbeam{
    direction: string,
    firepower: number,
}

interface LaserElementProps{
    laser: Laser,
}
export function LaserElement({laser}: LaserElementProps){
    let style : React.CSSProperties = {
        position: "absolute",
        margin: "0px",
        padding: "0px",
    }

    switch(laser.orientation){
        case "South":   style.top = "-9px";
                        style.left = "50%";
                        style.transform = "translate(-50%)";
                        break;
        case "West":    style.top = "50%";
                        style.right = "-6px";
                        style.transform = "translate(0, -50%)";
                        break;
        case "North":   style.bottom = "-13px";
                        style.left = "50%";
                        style.transform = "translate(-50%)";
                        break;
        case "East":    style.top = "50%";
                        style.left = "-6px";
                        style.transform = "translate(0, -50%)";
                        break;
    }
    let image = createLaserImage(laser);
    return (<div style={style}>
        {image}
    </div>
    );
}

function createLaserImage(laser: Laser){
    let style : React.CSSProperties = {};
    switch(laser.orientation){
        case "North":   style.transform = "rotate(135deg)";
                        break;
        case "East":    style.transform = "rotate(-135deg)";
                        break;
        case "South":   style.transform = "rotate(-45deg)";
                        break;
        case "West":    style.transform = "rotate(45deg)";
                        break;
    }
    return <Colorize style={style}/>;
}

export function BeamElement({direction, firepower}: Laserbeam){
    return(<div className="laserbeamHorizontal" style={{backgroundColor: "red", zIndex: 4}}></div>);
}