import React, { useState } from "react";
import { read, utils, writeFile } from "xlsx";
import { useEffect } from "react";

function uniqueId() {
    const firstItem = {
        value: "0",
    };

    let counter = "123456789".split("").reduce((acc, curValue, curIndex, arr) => {
        const curObj = {};
        curObj.value = curValue;
        curObj.prev = acc;

        return curObj;
    }, firstItem);
    firstItem.prev = counter;

    return function () {
        let now = Date.now();
        if (typeof performance === "object" && typeof performance.now === "function") {
            now = performance.now().toString().replace(".", "");
        }
        counter = counter.prev;
        return `${now}${Math.random().toString(16).substr(2)}${counter.value}`;
    };
}

const ImportExport = () => {
    const [notes, setNotes] = useState(null);
    async function getNotes() {
        const response = await fetch("http://localhost:8000/blogs");
        const obj = await response.json();
        setNotes(JSON.stringify(obj));
    }

    useEffect(() => {
        async function getNote() {
            const response = await fetch("http://localhost:8000/blogs");
            const obj = await response.json();
            setNotes(JSON.stringify(obj));
        }
        getNote();
    }, [notes]);

    function convertDataToSpecificFormat(data) {
        const tmp = {
            blocks: [
                {
                    key: "b5643",
                    text: data.Title,
                    type: "unstyled",
                    depth: 0,
                    inlineStyleRanges: [
                        {
                            offset: 0,
                            length: 5,
                            style: "BOLD",
                        },
                    ],
                    entityRanges: [],
                    data: {},
                },
                {
                    key: "9rcpf",
                    text: data.Note,
                    type: "unstyled",
                    depth: 0,
                    inlineStyleRanges: [],
                    entityRanges: [],
                    data: {},
                },
            ],
            entityMap: {},
            id: uniqueId(),
        };
        const outputData = JSON.stringify(tmp);
        return outputData;
    }

    const handleImport = async ($event) => {
        const files = $event.target.files;
        if (files.length) {
            const file = files[0];
            const reader = new FileReader();
            reader.onload = (event) => {
                const wb = read(event.target.result);
                const sheets = wb.SheetNames;
                const data = [];
                if (sheets.length) {
                    const rows = utils.sheet_to_json(wb.Sheets[sheets[0]]);
                    for (let index = 0; index < rows.length; index++) {
                        data.push(convertDataToSpecificFormat(rows[index]));
                    }
                }
                for (let index = 0; index < data.length; index++) {
                    fetch("http://localhost:8000/blogs", {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: data[index],
                    }).then(() => {
                        console.log("Data saved");
                    });
                }
            };
            reader.readAsArrayBuffer(file);
        }
    };
    const handleExport = () => {
        const headings = [["Title", "Note"]];
        const wb = utils.book_new();
        const ws = utils.json_to_sheet([]);
        getNotes();
        const data = [];
        const content = JSON.parse(notes);
        for (let index = 0; index < content.length; index++) {
            const tmp = { Title: content[index]["blocks"][0].text };
            let innerText = "";
            for (let i = 1; i < content[index]["blocks"].length; i++) {
                innerText += content[index]["blocks"][i].text + " ";
            }
            tmp.Note = innerText;
            data.push(tmp);
        }
        utils.sheet_add_aoa(ws, headings);
        utils.sheet_add_json(ws, data, { origin: "A2", skipHeader: true });
        utils.book_append_sheet(wb, ws, "Report");
        writeFile(wb, "Movie Report.csv");
    };

    return (
        <div className="importExport">
            <div className="input-group">
                <div className="custom-file">
                    <input
                        type="file"
                        name="file"
                        className="custom-file-input"
                        id="inputGroupFile"
                        required
                        onChange={handleImport}
                        accept=".csv, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"
                    />
                    <label
                        className="custom-file-label"
                        htmlFor="inputGroupFile"
                        style={{ fontWeight: "bold" }}
                    >
                        Import
                    </label>
                </div>
            </div>
            <div>
                <button onClick={handleExport} className="btn btn-primary float-right">
                    Export <i className="fa fa-download"></i>
                </button>
            </div>
        </div>
    );
};

export default ImportExport;
