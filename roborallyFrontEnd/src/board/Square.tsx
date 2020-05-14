import React, { useState } from "react";
import { Robot, RobotElement } from "./../Robot";
import { Laser, Laserbeam, LaserElement, BeamElement } from "./Laser";
import { Flag, Rotate90DegreesCcw, RotateLeft, RotateRight, Brightness1, KeyboardArrowUp, KeyboardArrowRight, KeyboardArrowDown, KeyboardArrowLeft } from "@material-ui/icons";
import { Badge } from "@material-ui/core";
import zIndex from "@material-ui/core/styles/zIndex";
export class Square{
    type: string;
    northwall: boolean;
    eastwall: boolean;
    southwall: boolean;
    westwall: boolean;
    robot: Robot | undefined;
    private lasers: Laser[];
    private laserbeams: Laserbeam[];

    constructor(type: string, northwall: boolean, eastwall: boolean, southwall: boolean, westwall: boolean){
        this.type = type;
        this.northwall = northwall;
        this.eastwall = eastwall;
        this.southwall = southwall;
        this.westwall = westwall;
        this.lasers = [];
        this.laserbeams = [];
    }

    public addLaser(laser: Laser) {
        if(this.lasers == undefined){
            this.lasers = [laser];
        }
        else{
            this.lasers.push(laser);
        }
    }

    public addLaserbeam(beam: Laserbeam){
        if(this.laserbeams == undefined){
            this.laserbeams = [beam];
        }
        else{
            this.laserbeams.push(beam);
        }
    }

    public getLasers(){
        if(this.lasers == undefined){
            return [];
        }
        else{
            return this.lasers;
        }
    }

    public getLaserbeams(){
        if(this.laserbeams == undefined){
            return [];
        }
        else{
            return this.laserbeams;
        }
    }

    getSituation(direction: string){
        if(this.robot != undefined) return "robot";
        else if(this.hasWallAt(direction)) return "wall";
        else return "none";
    }

    hasWallAt(direction: string){
        switch(direction){
            case "North": return this.northwall;
            case "East": return this.eastwall;
            case "South": return this.southwall;
            case "West": return this.westwall;
        }
        return false;
    }

    public render(rowNumber: number, columnNumber: number){
        const [ squareImageZIndex, setSquareImageZIndex ] = useState<number>(1);
        const [ robotZIndex, setRobotZIndex ] = useState<number>(2);
        const [ beamZIndex, setBeamZIndex ] = useState<number>(1);
        let style = createStyle(this, columnNumber, rowNumber);
    
        let squareImage = createImage(this.type, squareImageZIndex);
    
        let robotElement: JSX.Element = <div></div>;
        if(this.robot != undefined){
            robotElement = <RobotElement robot={this.robot} zIndex={robotZIndex}/>;
        }
    
        let laserElements = this.getLasers().map(laser => <LaserElement laser={laser} key={laser.orientation + laser.xCoordinate + laser.yCoordinate}/>);

        let beamElements = this.getLaserbeams().map(beam => <BeamElement beam={beam} zIndex={beamZIndex} situation={this.getSituation(beam.direction)} key={beam.direction + rowNumber + columnNumber}/>);
    
        return (<div 
                    key={"" + rowNumber + columnNumber} 
                    style={style} 
                    onMouseOver={mouseOver} 
                    onMouseOut={mouseLeave}>
                {squareImage}
                {robotElement}
                {laserElements}
                {beamElements}
            </div>);
    
        function mouseOver(){
            setSquareImageZIndex(3);
            setRobotZIndex(-1);
            setBeamZIndex(-1);
        }
    
        function mouseLeave(){
            setSquareImageZIndex(1);
            setRobotZIndex(2);
            setBeamZIndex(1);
        }
    }

    reset(){
        this.robot = undefined;
        this.laserbeams = [];
        this.lasers = [];
    }
}

function createStyle(square: Square, columnNumber: number, rowNumber: number){
    let style: React.CSSProperties = {
        position: "relative",
        borderWidth: "10px",
        gridColumnStart: columnNumber + 1,
        gridRowStart: rowNumber + 1,
    }
    let bordercolour = "rgb(153, 153, 8)";

    if(square.northwall){
        style.borderTopColor = bordercolour;
    }
    if(square.eastwall){
        style.borderRightColor = bordercolour;
    }
    if(square.southwall){
        style.borderBottomColor = bordercolour;
    }
    if(square.westwall){
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