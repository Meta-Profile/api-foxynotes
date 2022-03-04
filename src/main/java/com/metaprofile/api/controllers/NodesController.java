package com.metaprofile.api.controllers;

import com.metaprofile.api.core.ControllerResponse;
import com.metaprofile.api.exceptions.NotDeniedMetaProfileException;
import com.metaprofile.api.exceptions.NotFoundMetaProfileException;
import com.metaprofile.api.model.Node;
import com.metaprofile.api.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class NodesController {

    private final NodeService nodeService;

    @Autowired
    public NodesController(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @GetMapping(value = "/v1/list", produces = "application/json;charset=UTF-8")
    public ResponseEntity<ControllerResponse<List<Node>>> GetList() {
        List<Node> nodes = nodeService.getAll();
        HttpStatus status = HttpStatus.OK;
        return new ControllerResponse<>(nodes, status).response();
    }

    /**
     * Метод для получения мета профиля по id
     *
     * @param nodeId
     * @return
     */
    @GetMapping(value = "/v1/node", produces = "application/json;charset=UTF-8")
    public ResponseEntity<ControllerResponse<Node>> Get(@RequestParam(value = "nodeId") Integer nodeId)
            throws NotFoundMetaProfileException, NotDeniedMetaProfileException {
        // Получение мета профиля
        Node node = nodeService.getNodeByIdSafe(nodeId, 1);

        // Возвращение ответа
        return new ControllerResponse<>(node, HttpStatus.OK).response();
    }

    @GetMapping(value = "/v1/test", produces = "application/json;charset=UTF-8")
    public String Test() {
        return "Мой тест";
    }

}

