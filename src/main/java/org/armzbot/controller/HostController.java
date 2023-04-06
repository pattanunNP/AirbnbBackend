package org.armzbot.controller;

import org.armzbot.models.Host;
import org.armzbot.services.HostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/host")
public class HostController {

    private final HostServices hostServices;

    @Autowired
    public HostController(HostServices hostServices){
        this.hostServices = hostServices;
    }

    @GetMapping
    public List<Host> getAllHost() {
        return hostServices.getAllHost();
    }

    @GetMapping(path = "/{id}")
    public Host getHostById(@PathVariable("id") String id) throws IOException {
        return hostServices.getHostById(id);
    }

    /*
    @PostMapping
    public Host addHost() {
    }

    @PutMapping
    public Host updateHost() {
        return
    }

    @DeleteMapping
    public Host deleteHost() {
        return
    }
*/
}
