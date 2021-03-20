package com.ego.service;

import com.ego.commons.pojo.EgoResult;

public interface TbItemParamItemService {
    /**
     * 修改商品时显示商品规格参数
     * @param itemId
     * @return
     */
    EgoResult showItemParamItem(long itemId);
}
