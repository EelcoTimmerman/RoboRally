import React, { useState } from "react";
import { Robot, RobotElement } from "./../Robot";
import { Laser, Laserbeam, LaserElement } from "./Laser";
import { Flag, Rotate90DegreesCcw, RotateLeft, RotateRight, Brightness1, KeyboardArrowUp, KeyboardArrowRight, KeyboardArrowDown, KeyboardArrowLeft } from "@material-ui/icons";
import { Badge } from "@material-ui/core";
export interface Square{
    type: string;
    northwall: boolean;
    eastwall: boolean;
    southwall: boolean;
    westwall: boolean;
    robot: Robot | undefined;
    lasers: Laser[];
    laserbeams: Laserbeam[];
}

interface SquareElementProps{
    square: Square,
    rowNumber: number,
    columnNumber: number,
}

export function SquareElement({square, rowNumber, columnNumber}: SquareElementProps){
    const [ squareImageZIndex, setSquareImageZIndex ] = useState<number>(1);
    const [ robotZIndex, setRobotZIndex ] = useState<number>(2);
    let style = createStyle(square, columnNumber, rowNumber);
    let squareImage = createImage(square.type, squareImageZIndex);
    let robotElement: JSX.Element = <div></div>;
    if(square.robot != undefined){
        robotElement = <RobotElement robot={square.robot} zIndex={robotZIndex}/>;
    }

    let laserElements: JSX.Element[] = [];
    if(square.lasers != undefined){
        square.lasers.forEach(laser => {
            laserElements.push(<LaserElement laser={laser} key={laser.orientation + laser.xCoordinate + laser.yCoordinate}/>);        
        });
    }

    return (<div key={(columnNumber + 1) * (rowNumber + 1)} style={style} onMouseOver={mouseOver} onMouseOut={mouseLeave}>
            {squareImage}
            {robotElement}
            {laserElements}
        </div>);

    function mouseOver(){
        setSquareImageZIndex(3);
        setRobotZIndex(-1);
    }

    function mouseLeave(){
        setSquareImageZIndex(1);
        setRobotZIndex(2);
    }
}

function createStyle(square: Square, columnNumber: number, rowNumber: number){
    let style: React.CSSProperties = {
        position: "relative",
        gridColumnStart: columnNumber + 1,
        gridRowStart: rowNumber + 1,
    }
    let borderwidth = "20px";
    let bordercolour = "rgb(153, 153, 8)";

    if(square.northwall){
        style.borderTopWidth = borderwidth;
        style.borderTopColor = bordercolour;
    }
    if(square.eastwall){
        style.borderRightWidth = borderwidth;
        style.borderRightColor = bordercolour;
    }
    if(square.southwall){
        style.borderBottomWidth = borderwidth;
        style.borderBottomColor = bordercolour;
    }
    if(square.westwall){
        style.borderLeftWidth = borderwidth;
        style.borderLeftColor = bordercolour;
    }
    return style;
}

function createImage(type: string, zIndex: number){
    let iconStyle: React.CSSProperties = {
        fontSize: 80,
        position: "absolute",
        left: "50%",
        top: "50%",
        transform: "translate(-50%, -50%)",
        zIndex: zIndex,
    };
    switch(type){
        case "Checkpoint": return <Flag style={iconStyle}></Flag>;
        case "Gear180": return <Rotate90DegreesCcw style={iconStyle}/>;
        case "GearLeft": return <RotateLeft style={iconStyle}/>;
        case "GearRight": return <RotateRight style={iconStyle}/>;
        case "Pit": return <Brightness1 style={iconStyle}/>;
        case "SlowConveyorbeltNorth": return <KeyboardArrowUp style={iconStyle}/>;
        case "SlowConveyorbeltEast": return <KeyboardArrowRight style={iconStyle}/>;
        case "SlowConveyorbeltSouth": return <KeyboardArrowDown style={iconStyle}/>;
        case "SlowConveyorbeltWest": return <KeyboardArrowLeft style={iconStyle}/>;
        case "FastConveyorbeltNorth": return <Badge badgeContent={2}><KeyboardArrowUp style={iconStyle}/></Badge>;
        case "FastConveyorbeltEast": return <Badge badgeContent={2}><KeyboardArrowRight style={iconStyle}/></Badge>;
        case "FastConveyorbeltSouth": return <Badge badgeContent={2}><KeyboardArrowDown style={iconStyle}/></Badge>;
        case "FastConveyorbeltWest": return <Badge badgeContent={2}><KeyboardArrowLeft style={iconStyle}/></Badge>;
        default: return <div></div>;
    }
}