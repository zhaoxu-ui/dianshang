package com.ego.dubbo.service;

import com.ego.commons.exception.DaoException;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamItem;

import java.util.List;

/**
 * 商品参数
 */
public interface TbItemParamDubboService {
    /**
     * 根据id来返回商品参数
     * @param catId
     * @return
     */
    TbItemParam selectById(Long catId);

    /**
     * 分页查询
     * @param pageNumber 页码
     * @param pageSize 每页的大小
     * @return
     */
    List<TbItemParam> selectByPage(int pageNumber,int pageSize);

    /**
     * 查询总数量
     * @return
     */
    long selectCount();

    /**
     * 新增商品参数的功能
     * @param tbItemParam 整合好的TbItemParam对象
     * @return
     */
    int insert(TbItemParam tbItemParam);

    /**
     * 批量删除操作
     * @param ids
     * @return
     * @throws DaoException
     */
    int delete(long [] ids) throws DaoException;

}
