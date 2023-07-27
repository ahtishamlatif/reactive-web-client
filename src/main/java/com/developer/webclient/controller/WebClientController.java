package com.developer.webclient.controller;

import com.developer.webclient.requestDTO.QueueRequestDTO;
import com.developer.webclient.services.SenderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/api/v1/send/")
public class WebClientController {


    @Autowired
    private SenderServices senderServices;


    @PostMapping("multiple")
    public String sendToQueue(@RequestBody List<QueueRequestDTO> requestDTOS) {
        for (QueueRequestDTO myObject : requestDTOS) {
            senderServices.sendObjectsToController(requestDTOS);
        }
        return "Objects sent to the queue successfully!";
    }
}
