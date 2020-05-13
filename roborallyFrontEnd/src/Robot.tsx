import React from "react";

export interface Robot{
    name: string,
    orientation: string,
    colour: string,
    xCoordinate: number,
    yCoordinate: number,
    ready: boolean,
    hitpoints: number,
    status: string,
}

interface RobotElementProps{
    robot: Robot,
    zIndex: number,
}

export function RobotElement({robot, zIndex}: RobotElementProps){
    let classname: string = robot.orientation + "-arrow";
    let style: React.CSSProperties = {
        backgroundColor: robot.colour,
        position: "absolute",
        left: "50%",
        top: "50%",
        transform: "translate(-50%, -50%)",
        zIndex: zIndex,
    }
    return (<div className="inner-square" style={style}>
                <div className={classname}></div>
            </div>);

}