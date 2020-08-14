package com.github.joern.kalz.openapi.spring.angular.starter;

import com.github.joern.kalz.openapi.spring.angular.starter.generated.api.HelloApi;
import com.github.joern.kalz.openapi.spring.angular.starter.generated.model.Greeting;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class HelloApiController implements HelloApi {
    @Override
    public ResponseEntity<Greeting> getHello() {
        return new ResponseEntity<>(new Greeting().message("hello"), HttpStatus.OK);
    }
}
