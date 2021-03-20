package com.ego.controller;

import com.ego.commons.pojo.EgoResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 页面显示的控制器
 */
@Controller
public class PageController {
    /**
     * 登录页面
     * @return login.jsp页面
     */
    @RequestMapping("/")
    public String login(){
        System.out.println("执行控制器");
        return "login";
    }

    /**
     * 显示主页面
     * @return index.jsp页面
     */
    @RequestMapping("/main")
    public String showIndex(){
        return "index";
    }

    /**
     * 登录成功后跳转的控制器
     * @return
     */
    @RequestMapping("/loginSuccess")
    @ResponseBody
    public EgoResult loginSuccess(){
        return EgoResult.ok();
    }

    /**
     * restful风格，url中使用{名称}特殊标记进行占位，通过@PathVariable取出占位内容
     * @param page 页面
     * @return page.jsp
     */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }

    @RequestMapping("/rest/page/item-edit")
    public String showEdit(){
        return "item-edit";
    }
}
