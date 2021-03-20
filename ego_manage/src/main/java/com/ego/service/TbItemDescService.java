package com.ego.service;

import com.ego.commons.pojo.EgoResult;

/**
 * 商品描述，Consumer
 */
public interface TbItemDescService {
    /**
     * 根据主键查询商品描述
     * @param id
     * @return
     */
    EgoResult selectById(Long id);
}
