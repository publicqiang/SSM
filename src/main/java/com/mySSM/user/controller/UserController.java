package com.mySSM.user.controller;

import com.mySSM.user.bean.User;
import com.mySSM.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param user
     * @return
     */
    @RequestMapping("index")
    public ModelAndView register(User user){
        logger.info("注册用户");
        Map<String ,Object> map = new HashMap<>();
        boolean result = userService.register(user);
        if(result){
            logger.info("注册成功");
            return new ModelAndView("redirect:findList");
        }else {
            logger.error("注册失败");
            map.put("error","注册失败！");
            RedirectAttributesModelMap ra = new RedirectAttributesModelMap();
            ra.addAllAttributes(map);
            return new ModelAndView("redirect:index",ra);
        }
    }

    /**
     * 查询用户
     * @param user 查询条件
     * @return
     */
    @RequestMapping("findList")
    public ModelAndView UserList(User user){
        Map<String ,Object> map = new HashMap<>();
        List<User> userList = userService.findList(user);
        map.put("userList",userList);
        return new ModelAndView("user/userList",map);

    }
}
