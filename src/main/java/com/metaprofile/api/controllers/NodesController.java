package com.metaprofile.api.controllers;

import com.metaprofile.api.core.ControllerResponse;
import com.metaprofile.api.model.Node;
import com.metaprofile.api.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NodesController {

    private final NodeService nodeService;

    @Autowired
    public NodesController(NodeService nodeService){
        this.nodeService = nodeService;
    }

    @GetMapping("/api/v1/node")
    public ResponseEntity<ControllerResponse<List<Node>>> GetList(){
        List<Node> nodes = nodeService.getAll();
        HttpStatus status = HttpStatus.OK;
        return new ControllerResponse<>(nodes, status).response();
    }

}

