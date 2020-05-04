import React, { Component } from "react";
import { makeStyles } from '@material-ui/styles';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';

interface Card{
    type: string,
}

function getCards(){
    const cards:Card[] = [];
    for(let i = 0; i < 5; i++){
            cards.push({
                type: "MoveOneForward",
            });
    }
    return cards;
}

const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  paper: {
    background: 'linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)',
    textAlign: 'center'
  },
}));

export function showCards(){
    const classes = useStyles();
    let myCards:Card[] = getCards();

    for(let card in myCards){
      //showcard(classes, card)
    }
    return (
      <Grid container spacing={8}>
        <Grid item xs={2}>
          <Paper className={classes.paper}>{myCards[0].type}</Paper>
        </Grid>
        <Grid item xs={2}>
          <Paper className={classes.paper}>{myCards[0].type}</Paper>
        </Grid>
        <Grid item xs={2}>
          <Paper className={classes.paper}>{myCards[0].type}</Paper>
        </Grid>
        <Grid item xs={2}>
          <Paper className={classes.paper}>{myCards[0].type}</Paper>
        </Grid>
        <Grid item xs={2}>
          <Paper className={classes.paper}>{myCards[0].type}</Paper>
        </Grid>
      </Grid>
    );
}

function showcard(classes: Record<"root" | "paper", string>, card: string){
  classes = classes;
  return (
    <Grid item xs={3}>
    <Paper className={classes.paper}>card.type</Paper>
    </Grid>
  )

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
