package com.metaprofile.api.service;

import com.metaprofile.api.model.Node;

import java.util.List;

public interface NodeService {

    /**
     * Возвращает все записи
     * @return записи мета профилей
     */
    List<Node> getAll();
}
