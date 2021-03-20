package com.ego.dubbo.service.impl;

import com.ego.dubbo.service.TbItemParamItemDubboService;
import com.ego.mapper.TbItemParamItemMapper;
import com.ego.pojo.TbItemParamItem;
import com.ego.pojo.TbItemParamItemExample;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class TbItemParamItemDubboServiceImpl implements TbItemParamItemDubboService {
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;
    @Override
    public TbItemParamItem selectByItemId(long itemId) {
        TbItemParamItemExample example = new TbItemParamItemExample();
        example.createCriteria().andItemIdEqualTo(itemId);
        List<TbItemParamItem> tbItemParamItems = tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if (tbItemParamItems.size() == 1 && tbItemParamItems != null)
            return tbItemParamItems.get(0);
        return null;
    }
}
