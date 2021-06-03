package com.backend.modelos;

import java.io.Serializable;

public class ErrorResponse implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String content;

    public ErrorResponse(String content){
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
