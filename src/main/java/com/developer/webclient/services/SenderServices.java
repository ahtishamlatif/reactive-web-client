package com.developer.webclient.services;

import com.developer.webclient.requestDTO.QueueRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;


import java.util.List;

@Service
public class SenderServices {

    private final WebClient.Builder webClientBuilder;

    public SenderServices(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }




    public void sendObjectsToController(List<QueueRequestDTO> requestDTOS) {
        Flux.fromIterable(requestDTOS)
                .parallel()
                .runOn(Schedulers.boundedElastic()) // Use boundedElastic scheduler for blocking tasks
                .flatMap(this::sendObjectToController)
                .doOnComplete(() -> System.out.println("All objects sent successfully."))
                .subscribe();
    }


    private Mono<String> sendObjectToController(QueueRequestDTO requestDTO) {
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8001/api/v1/queue/send/message")
                .body(Mono.just(requestDTO), QueueRequestDTO.class)
                .retrieve()
                .bodyToMono(String.class);
    }


}
