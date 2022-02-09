package com.metaprofile.api.repository;

import com.metaprofile.api.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NodeRepository extends JpaRepository<Node, Integer> {

}