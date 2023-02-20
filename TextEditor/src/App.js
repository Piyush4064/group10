import React, { useState } from "react";
import "./App.css";
import TextEditor from "./components/TextEditor";
import { convertFromRaw } from "draft-js";
import Card from "./components/Card";
import ImportExport from "./components/ImportExport";

function App() {
    const [flag, setFlag] = useState(false);

    const handleClick = () => {
        setFlag((prevState) => {
            return !prevState;
        });
    };

    const editorTemplate = (
        <div className="editor">
            <TextEditor />
        </div>
    );

    const [notes, setNotes] = useState(null);
    async function getNotes() {
        const response = await fetch("http://localhost:8000/blogs");
        const obj = await response.json();
        setNotes(JSON.stringify(obj));
    }
    getNotes();
    let noteTemplate = [],
        size = 0;
    if (notes) {
        size = JSON.parse(notes).length;
    }
    for (let index = 0; index < size; index++) {
        let oneNote = notes ? convertFromRaw(JSON.parse(notes)[index]) : null;
        noteTemplate.push(oneNote);
    }

    return (
        <div className="App">
            <header className="App-header">
                <img
                    src="https://images.unsplash.com/photo-1676407591026-824246b4a102?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1770&q=80"
                    alt="Random"
                />
                <button onClick={handleClick}>Notes</button>
                <button onClick={handleClick}>Create Note</button>
            </header>
            {flag ? (
                editorTemplate
            ) : (
                <div className="notes">
                    <ImportExport />
                    {noteTemplate.map((item) => {
                        return <Card item={item} />;
                    })}
                </div>
            )}
        </div>
    );
}

export default App;
