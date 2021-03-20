package com.ego.dubbo.service;

import com.ego.pojo.TbItemParamItem;

/**
 * 对表Tb_Item_Param_Item表的操作
 */
public interface TbItemParamItemDubboService {
    /**
     * 根据ItemId来查询商品规格参数
     * @return
     */
    TbItemParamItem selectByItemId(long itemId);

}
