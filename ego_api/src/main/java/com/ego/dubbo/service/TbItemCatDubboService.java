package com.ego.dubbo.service;

import com.ego.pojo.TbItemCat;

import java.util.List;

/**
 * 选择类目功能
 */
public interface TbItemCatDubboService {
    /**
     * 选择出所有的类目
     * @param pid
     * @return
     */
    List<TbItemCat> selectByPid(Long pid);

    /**
     * 根据主键查询类目
     * @param id
     * @return
     */
    TbItemCat selectByid(Long id);
}
