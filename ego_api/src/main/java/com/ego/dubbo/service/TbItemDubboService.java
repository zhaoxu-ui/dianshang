package com.ego.dubbo.service;

import com.ego.commons.exception.DaoException;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamItem;

import java.util.List;

/**
 * 商品表数据库操作
 */
public interface TbItemDubboService {
    /**
     * 分页查询
     * @param pageSize 每页大小
     * @param pageNumber 页码
     * @return 当前页显示数据
     */
    List<TbItem> selectByPage(int pageSize,int pageNumber);

    /**
     * 查询总条数
     * @return
     */
    long selectCount();

    /**
     * 事务一定要卸载provider方
     * @param ids 所有要修改的id
     * @param status 修改的状态
     * @return
     * @throws DaoException
     */
    int updateStatusByIds(long [] ids, int status) throws DaoException;

    /**
     * 实现商品新增
     * @param tbItem
     * @param tbItemDesc
     * @return
     */
    int insert(TbItem tbItem, TbItemDesc tbItemDesc, TbItemParamItem tbItemParamItem) throws DaoException;

    /**
     * 实现商品的修改功能
     * @param tbItem
     * @param tbItemDesc
     * @return
     * @throws DaoException
     */
    int update(TbItem tbItem,TbItemDesc tbItemDesc,TbItemParamItem tbItemParamItem) throws DaoException;
}
