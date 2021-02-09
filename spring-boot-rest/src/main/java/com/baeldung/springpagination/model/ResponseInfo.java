package com.baeldung.springpagination.model;

import java.io.Serializable;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ResponseInfo extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 276303624831485080L;

    private String messageCode;

    private String message;

    /**
     * @param errorCode
     * @param errorMessage
     */
    public ResponseInfo(String messageCode, String message) {

        this.messageCode = messageCode;

        this.message = message;
    }
    
    public ResponseInfo(){
    }

    /**
     * @return the messageCode
     */
    public String getMessageCode() {

        return messageCode;
    }

    /**
     * @param messageCode
     *            the messageCode to set
     */
    public void setMessageCode(String messageCode) {

        this.messageCode = messageCode;
    }

    /**
     * @return the message
     */
    public String getMessage() {

        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {

        this.message = message;
    }

}