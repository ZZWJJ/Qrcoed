package com.learning.www.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learning.www.entity.User;
import com.learning.www.mapper.UserMapper;

/**
 * Created with IntelliJ IDEA
 *
 * @Author yuanhaoyue swithaoy@gmail.com
 * @Description 登陆 Controller
 * @Date 2018-04-03
 * @Time 22:28
 */
@Controller
public class LoginController {
    private final ResultMap resultMap;
    private final UserMapper userMapper;

    private static Logger log = LoggerFactory.getLogger(LoginController.class);
    
    @Autowired
    public LoginController(ResultMap resultMap, UserMapper userMapper) {
        this.resultMap = resultMap;
        this.userMapper = userMapper;
    }

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public String notLogin() {
        return "login";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    //@RequiresPermissions("admin:test")
    public String test() {
        return "index";
    }
    
    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    @ResponseBody
    public ResultMap notRole() {    
        return resultMap.success().message("您没有权限！");
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "login";
    }

    /**
     * 登陆
     * 0:手机注册用户，1：企业，3：错误
     * @param username 用户名
     * @param password 密码
     */
    @RequestMapping(value = "/login")
    @ResponseBody
    public int login(String username, String password) {
    	
    	log.info("username:"+username+"===="+"password:"+password);
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
			// 执行认证登陆
			subject.login(token);
		} catch (AccountException e) {
			// 账户或密码错误
			e.printStackTrace();
			log.info(e.getMessage());
			return 3;
		}
        //根据权限，指定返回数据
        User user = userMapper.getPasswordByUsername(username);
        if (null != user) {
            return user.getType();
        }
        return 3;
    }
    
    @RequestMapping("toTable")
	public String toTable() {
		return "bootstraptable";
	}
}


