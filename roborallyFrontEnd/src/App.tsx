import React, { useState } from "react";

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
    testFetch();

    return <div>{testcolor}</div>
}