package com.ego.dubbo.service;

import com.ego.pojo.TbItemDesc;

/**
 * 商品描述
 */
public interface TbItemDescDubboService {
    /**
     * 根据主键查询对象信息
     * @param id
     * @return
     */
    TbItemDesc selectById(Long id);
}
