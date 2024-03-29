package com.multiTest.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message<T> {

    private MessageHead head;

    private T body;

    public Message(SecKillEnum resultEnum, T body){
        this.head = new MessageHead();
        this.head.setStatusCode(resultEnum.getCode());
        this.head.setStatusMessage(resultEnum.getMessage());
        this.body = body;
    }

    public Message(SecKillEnum resultEnum){
        this.head = new MessageHead();
        this.head.setStatusCode(resultEnum.getCode());
        this.head.setStatusMessage(resultEnum.getMessage());
    }

}