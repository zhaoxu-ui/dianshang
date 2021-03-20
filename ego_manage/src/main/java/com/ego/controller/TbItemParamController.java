package com.ego.controller;

import com.ego.commons.pojo.EasyUIDatagrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbItemParam;
import com.ego.service.TbItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TbItemParamController {
    @Autowired
    private TbItemParamService tbItemParamService;
    @RequestMapping("/item/param/query/itemcatid/{id}")
    @ResponseBody
    public EgoResult showItemParam(@PathVariable Long id){
        EgoResult egoResult = tbItemParamService.selectById(id);
        return egoResult;
    }
    @RequestMapping("/item/param/list")
    @ResponseBody
    public EasyUIDatagrid showItemParamList(int page,int rows){
        return tbItemParamService.showItemParam(page, rows);
    }
    @RequestMapping("/item/param/save/{catId}")
    @ResponseBody
    public EgoResult insert(TbItemParam tbItemParam,@PathVariable Long catId){
        tbItemParam.setItemCatId(catId);
        return tbItemParamService.insert(tbItemParam);
    }
    @RequestMapping("/item/param/delete")
    @ResponseBody
    public EgoResult delete(long [] ids){
        return tbItemParamService.delete(ids);
    }

}
