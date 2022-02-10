package com.metaprofile.api.service.impl;

import com.metaprofile.api.exceptions.MetaProfileException;
import com.metaprofile.api.exceptions.NotDeniedMetaProfileException;
import com.metaprofile.api.exceptions.NotFoundMetaProfileException;
import com.metaprofile.api.model.Node;
import com.metaprofile.api.repository.NodeRepository;
import com.metaprofile.api.service.NodeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NodeServiceImpl implements NodeService {

    private final NodeRepository nodeRepository;

    public NodeServiceImpl(NodeRepository nodeRepository){
        this.nodeRepository = nodeRepository;
    }

    @Override
    public List<Node> getAll() {
        return nodeRepository.findAll();
    }

    @Override
    public Optional<Node> getNodeById(Integer id) {
        return nodeRepository.findById(id);
    }

    @Override
    public Node getNodeByIdSafe(Integer nodeId, Integer testUserId) throws NotFoundMetaProfileException, NotDeniedMetaProfileException {

        Node node = getNodeById(nodeId).orElseThrow(NotFoundMetaProfileException::new);

        // Если не найден
        if(!node.isAccessibleForUserId(testUserId)) throw new NotDeniedMetaProfileException();

        return node;
    }
}
