import React, { useState } from "react";
import { showCards } from "./board/CardsInHand";

export function App() {
    const [ testcolor, setTestColor] = useState<String>("");

    async function testFetch(){
        const response = await fetch('roborally/api/players', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
            }
        });
        const testy = await response.json();
        setTestColor(testy.testcolor);
    }
    //testFetch();
    let cards = showCards();

    return cards;
}