package com.developer.webclient.requestDTO;

import lombok.Data;

import java.io.Serializable;
@Data
public class QueueRequestDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private  String message;
    private  String  date;
    private Integer messageId;

}