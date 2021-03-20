package com.ego.controller;

import com.ego.commons.pojo.EgoResult;
import com.ego.service.TbItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TbItemParamItemController {
    @Autowired
    private TbItemParamItemService tbItemParamItemService;
    @RequestMapping("/rest/item/param/item/query/{id}")
    public EgoResult showTbParamItem(@PathVariable long id){
        return tbItemParamItemService.showItemParamItem(id);

    }

}
