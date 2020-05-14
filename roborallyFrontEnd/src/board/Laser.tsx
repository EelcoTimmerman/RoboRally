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

interface LaserbeamProps{
    beam: Laserbeam,
    zIndex: number,
    situation: string,
}

interface LaserElementProps{
    laser: Laser,
}
export function LaserElement({laser}: LaserElementProps){
    let style : React.CSSProperties = {
        position: "absolute",
        margin: "0px",
        padding: "0px",
        zIndex: 5,
    }

    switch(laser.orientation){
        case "South":   style.top = "-9px";
                        style.left = "50%";
                        style.transform = "translate(-50%)";
                        style.width = "30px";
                        style.height = "37px";
                        break;
        case "West":    style.top = "50%";
                        style.right = "-9px";
                        style.transform = "translate(0, -50%)";
                        style.width = "37px";
                        style.height = "30px";
                        break;
        case "North":   style.bottom = "-15px";
                        style.left = "50%";
                        style.transform = "translate(-50%)";
                        style.width = "30px";
                        style.height = "37px";
                        break;
        case "East":    style.top = "50%";
                        style.left = "-13px";
                        style.transform = "translate(0, -50%)";
                        style.width = "37px";
                        style.height = "30px";
                        break;
    }
    let image = createLaserImage(laser);
    return (<div style={style}>
        {image}
    </div>
    );
}

function createLaserImage(laser: Laser){
    let style : React.CSSProperties = {
        fontSize: 30,
    };
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

export function BeamElement({beam, zIndex, situation}: LaserbeamProps){
    let beamLength = "0px";
    switch(situation){
        case "robot":   beamLength = "90px";
                        break;
        case "wall":    beamLength = "180px";
                        break;
        case "none":    beamLength = "201px";
    }
    let beamColor = "red";
    if(beam.firepower == 2) beamColor = "blue";
    if(beam.firepower == 3) beamColor = "purple";
    if(beam.direction == "East"){
        return(<div className="laserbeamHorizontal" style={{borderColor: beamColor, zIndex: zIndex, width: beamLength, left: "0px"}}></div>);
    }
    else if(beam.direction == "South"){
        return(<div className="laserbeamVertical" style={{borderColor: beamColor, zIndex: zIndex, height: beamLength, top: "0px"}}></div>);
    }
    else if(beam.direction == "West"){
        return(<div className="laserbeamHorizontal" style={{borderColor: beamColor, zIndex: zIndex, width: beamLength, right: "0px"}}></div>);
    }
    else{
        return(<div className="laserbeamVertical" style={{borderColor: beamColor, zIndex: zIndex, height: beamLength, bottom: "0px"}}></div>);
    }
}