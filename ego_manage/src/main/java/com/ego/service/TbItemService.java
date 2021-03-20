package com.ego.service;

import com.ego.commons.pojo.EasyUIDatagrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;

/**
 * 完成视图逻辑
 */
public interface TbItemService {
    /**
     * 方法的返回值为页面要的东西
     * 方法的参数为页面传递过来的东西
     */
    /**
     * 分页显示商品信息
     * @param page 页码
     * @param rows 每页大小
     * @return
     */
    EasyUIDatagrid showItem(int page,int rows);

    /**
     * 操作状态值
     * @param ids 要操作的id
     * @param status 状态值
     * @return
     */
    EgoResult updateStatus(long[] ids,int status);

    /**
     * 新增商品
     * @param
     * @param
     * @return
     */
    EgoResult insert(TbItem item, String desc,String itemParams);

    /**
     * 修改商品信息
     * @param item
     * @param desc
     * @return
     */
    EgoResult update(TbItem item,String desc,String itemParams,long itemParamId);
}
