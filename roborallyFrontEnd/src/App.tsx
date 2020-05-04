import React, { useState } from "react";
import { showCards } from "./board/CardsInHand";

export function App() {
    
    async function showAllCards(){
        showCards();
    }

    let cards = showCards();  
    return cards;
}

