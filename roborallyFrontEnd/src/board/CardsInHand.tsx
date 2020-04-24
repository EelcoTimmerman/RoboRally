import React, { Component } from "react";

interface Card{
    type: string,
}

function getCards(){
    const cards:Card[] = [];
    for(let i = 0; i < 9; i++){
            cards.push({
                type: "MoveOneForward",
            });
    }
    return cards;
}

export function showCards(){
    let myCards = getCards();
    let cardsInHand = [1,2];
    
    // let cardsInHand = cards.map((row: Square[], index: number) => 
    //     createRow(row, index)
    // );
    return (
        <div className="Cards">
            {myCards}
        </div>
    );
}

// function createRow(row: Square[], rowNumber: number):JSX.Element[]{
//     return row.map((square: Square, index: number) => createSquare(square, rowNumber, index));
// }

// function createSquare(square: Square, rowNumber: number, columnNumber: number):JSX.Element{
//     let style: React.CSSProperties = {
//         gridColumnStart: columnNumber + 1,
//         gridRowStart: rowNumber + 1,
//     }

//     if(square.northwall){
//         style.borderTopWidth = "thick";
//         style.borderTopColor = "rgb(153, 153, 8)";
//     }
//     if(square.eastwall){
//         style.borderRightWidth = "thick";
//         style.borderRightColor = "rgb(153, 153, 8)";
//     }
//     if(square.southwall){
//         style.borderBottomWidth = "thick";
//         style.borderBottomColor = "rgb(153, 153, 8)";
//     }
//     if(square.westwall){
//         style.borderLeftWidth = "thick";
//         style.borderLeftColor = "rgb(153, 153, 8)";
//     }

//     return <div key={(columnNumber + 1) * (rowNumber + 1)} style={style}>
//             {square.type}
//         </div>;
// }