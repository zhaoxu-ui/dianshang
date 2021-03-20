package com.ego.dubbo.service;

import com.ego.commons.exception.DaoException;
import com.ego.pojo.TbContentCategory;

import java.util.List;

/**
 * 内容分类管理
 */
public interface TbContentCategoryDubboService {
    /**
     * 展示内容分类的详情，通过树形来展示
     * @param pid
     * @return
     */
    List<TbContentCategory> selectByPid(Long pid);

    /**
     * 新增内容分类管理
     * @param category
     * @return
     * @throws DaoException
     */
    int insert(TbContentCategory category) throws DaoException;

    /**
     * 重命名功能
     * @param category
     * @return
     * @throws DaoException
     */
    int update(TbContentCategory category) throws DaoException;

}
