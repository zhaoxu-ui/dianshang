package com.ego.service.impl;

import com.ego.commons.exception.DaoException;
import com.ego.commons.pojo.EasyUIDatagrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.util.IDUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;
import com.ego.service.TbItemService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TbItemServiceImpl implements TbItemService {
    @Reference
    private TbItemDubboService tbItemDubboService;
    @Override
    public EasyUIDatagrid showItem(int page, int rows) {
        List<TbItem> list = tbItemDubboService.selectByPage(rows,page);
        long total = tbItemDubboService.selectCount();
        return new EasyUIDatagrid(list,total);
    }

    @Override
    public EgoResult updateStatus(long[] ids, int status) {
        try {
            int index = tbItemDubboService.updateStatusByIds(ids, status);
            if (index == 1){
                return EgoResult.ok();
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return EgoResult.error("操作失败");
    }

    /**
     * 新增商品
     * @param
     * @param
     * @return
     */
    @Override
    public EgoResult insert(TbItem item, String desc,String itemParams) {
        Date date = new Date();
        long id = IDUtils.genItemId();
        item.setCreated(date);
        item.setUpdated(date);
        item.setId(id);
        item.setStatus((byte) 1);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(id);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setUpdated(date);
        tbItemDesc.setCreated(date);
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setId(IDUtils.genItemId());
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        tbItemParamItem.setItemId(id);
        tbItemParamItem.setParamData(itemParams);

        try {
            int index = tbItemDubboService.insert(item, tbItemDesc,tbItemParamItem);
            if (index == 1){
                return EgoResult.ok();
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return EgoResult.error("新增失败");
    }

    /**
     * 修改商品信息
     * @param item
     * @param desc
     * @return
     */
    @Override
    public EgoResult update(TbItem item, String desc,String itemParams,long itemParamId) {
        Date date = new Date();
        item.setUpdated(date);
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setUpdated(date);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setItemId(item.getId());

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setId(itemParamId);
        tbItemParamItem.setItemId(item.getId());
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        try {
            int result = tbItemDubboService.update(item, tbItemDesc,tbItemParamItem);
            if (result == 1){
                return EgoResult.ok();
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return EgoResult.error("修改商品信息失败");
    }
}
