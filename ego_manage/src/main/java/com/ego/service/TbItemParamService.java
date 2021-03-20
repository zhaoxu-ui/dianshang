package com.ego.service;

import com.ego.commons.pojo.EasyUIDatagrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbItemParam;

/**
 * consumer中商品参数
 */
public interface TbItemParamService {
    /**
     * 根据主键id来返回商品参数
     * @param id
     * @return
     */
    EgoResult selectById(Long catId);

    /**
     * 分页显示商品参数列表
     * @param page
     * @param rows
     * @return
     */
    EasyUIDatagrid showItemParam(int page ,int rows);

    /**
     * 新增规格模版
     * @param tbItemParam
     * @return
     */
    EgoResult insert(TbItemParam tbItemParam);

    /**
     * 批量删除商品规格参数模版
     * @return
     */
    EgoResult delete(long [] ids);
}
