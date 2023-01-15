package com.casestudy1.FirstApp.controller;

import com.casestudy1.FirstApp.model.EnvironmentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class MainController {

    @Autowired
    Environment env;

    @GetMapping("/")
    public ResponseEntity<EnvironmentDetails> helloworld() throws UnknownHostException {
        EnvironmentDetails det = new EnvironmentDetails();
        det.setPort(env.getProperty("server.port"));
        InetAddress addr = InetAddress.getLocalHost();
        det.setHostName(addr.getHostName());
        det.setHostAddress(addr.getHostAddress());
        return new ResponseEntity<>(det, HttpStatus.OK);
    }
}
