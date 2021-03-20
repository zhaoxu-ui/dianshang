package com.ego.service;

import com.ego.commons.pojo.EasyUITree;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContentCategory;

import java.util.List;

public interface TbContentCategoryService {
    /**
     * 显示内容分类树状菜单
     * @param pid
     * @return
     */
    List<EasyUITree> showContentCategory(Long pid);
    /**
     * 新增内容分类
     */
    EgoResult insert(TbContentCategory tbContentCategory);

}
