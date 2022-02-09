package com.metaprofile.api.controllers;

import com.metaprofile.api.core.ControllerResponse;
import com.metaprofile.api.model.Node;
import com.metaprofile.api.service.NodeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/node")
public class NodesController {

    private final NodeService nodeService;

    @Autowired
    public NodesController(NodeService nodeService){
        this.nodeService = nodeService;
    }

    @ApiOperation(value = "получить список пользователей", notes = "получить список пользователей", response = Node[].class)
    @GetMapping("/")
    public ResponseEntity<ControllerResponse<List<Node>>> GetList(){
        List<Node> nodes = nodeService.getAll();
        HttpStatus status = HttpStatus.OK;
        return new ControllerResponse<>(nodes, status).response();
    }

}

