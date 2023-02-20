import React from "react";

import { Editor } from "react-draft-wysiwyg";
import { EditorState } from "draft-js";

export default class Card extends React.Component {
    render() {
        return (
            <div style={{ width: this.props.width + "px" }}>
                <div className="styleCard">
                    <div className="styleCardContent">
                        <div className="styleDescription">
                            <Editor
                                editorState={EditorState.createWithContent(
                                    this.props.item
                                )}
                                readOnly={true}
                            />
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
