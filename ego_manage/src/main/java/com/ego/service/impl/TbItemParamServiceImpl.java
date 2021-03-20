package com.ego.service.impl;

import com.ego.commons.exception.DaoException;
import com.ego.commons.pojo.EasyUIDatagrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.util.IDUtils;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamChild;
import com.ego.service.TbItemParamService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TbItemParamServiceImpl implements TbItemParamService {
    @Reference
    private TbItemParamDubboService tbItemParamDubboService;
    @Reference
    private TbItemCatDubboService tbItemCatDubboService;

    /**
     * 根据id来显示商品参数
     * @param
     * @return
     */
    @Override
    public EgoResult selectById(Long catId) {
        TbItemParam tbItemParam = tbItemParamDubboService.selectById(catId);
        if (tbItemParam != null)
            return EgoResult.ok(tbItemParam);
        return EgoResult.error("获取失败");
    }

    /**
     * 分页显示商品参数列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDatagrid showItemParam(int page, int rows) {
        //通过provider提供的方法来查询出分页数据
        List<TbItemParam> list = tbItemParamDubboService.selectByPage(page, rows);
        //通过TbItemParamChild集合封装查询出来的信息
        List<TbItemParamChild> listChild = new ArrayList<>();
        for (TbItemParam param : list) {
            TbItemParamChild child = new TbItemParamChild();
            //通过同名属性把源对象中数据拷贝到新对象中
            BeanUtils.copyProperties(param,child);
            //根据tbItemParam中的ItemCatId来查询出商品
            TbItemCat tbItemCat = tbItemCatDubboService.selectByid(param.getItemCatId());
            //获取商品名称
            String catName = tbItemCat.getName();
            //为TbItemParamChild中的商品名称属性赋值
            child.setItemCatName(catName);
            //加入到集合中
            listChild.add(child);
        }
        long total = tbItemParamDubboService.selectCount();
        return new EasyUIDatagrid(listChild,total);
    }

    @Override
    public EgoResult insert(TbItemParam tbItemParam) {
        tbItemParam.setId(IDUtils.genItemId());
        Date date = new Date();
        tbItemParam.setCreated(date);
        tbItemParam.setUpdated(date);
        int insert = tbItemParamDubboService.insert(tbItemParam);
        if (insert ==1)
            return EgoResult.ok();


        return EgoResult.error("新增失败");
    }

    @Override
    public EgoResult delete(long [] ids) {
        try {
            int result = tbItemParamDubboService.delete(ids);
            if (result == 1)
                return EgoResult.ok();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return EgoResult.error("删除失败");
    }
}
