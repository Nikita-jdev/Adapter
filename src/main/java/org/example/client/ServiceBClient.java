package org.example.client;

import org.example.entity.MessageOut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ServiceB", url = "${server.serviceB.host}:${server.serviceB.version}")
public interface ServiceBClient {

    @PostMapping()
    ResponseEntity<String> sendCurrentTemp(@RequestBody MessageOut messageOut);
}
