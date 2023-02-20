import React, { Component } from "react";
import { Editor } from "react-draft-wysiwyg";
import { EditorState, convertToRaw } from "draft-js";

import "react-draft-wysiwyg/dist/react-draft-wysiwyg.css";
import "./TextEditor.css";

export default class TextEditor extends Component {
    constructor(props) {
        super(props);
        this.state = {
            editorState: EditorState.createEmpty(),
        };
    }

    onEditorStateChange = (editorState) => {
        this.setState({
            editorState,
        });
    };

    saveButtonHandler = () => {
        const outputData = JSON.stringify(
            convertToRaw(this.state.editorState.getCurrentContent())
        );
        fetch("http://localhost:8000/blogs", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: outputData,
        }).then(() => {
            console.log("Data saved");
        });
    };

    render() {
        const { editorState } = this.state;
        return (
            <div>
                <div className="editor">
                    <Editor
                        editorState={editorState}
                        toolbarClassName="toolbarClassName"
                        wrapperClassName="wrapperClassName"
                        editorClassName="editorClassName"
                        onEditorStateChange={this.onEditorStateChange}
                        placeholder="Start Writting"
                    />
                </div>
                <button className="button" onClick={this.saveButtonHandler.bind(this)}>
                    Save
                </button>
                {/* <textarea
          disabled
          value={draftToHtml(convertToRaw(editorState.getCurrentContent()))}
        ></textarea> */}
            </div>
        );
    }
}
