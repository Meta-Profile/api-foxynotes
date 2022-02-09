package com.metaprofile.api.service;

import com.metaprofile.api.model.Node;
import com.metaprofile.api.repository.NodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
