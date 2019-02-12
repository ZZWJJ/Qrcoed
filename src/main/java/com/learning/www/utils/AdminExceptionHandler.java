package com.learning.www.utils;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class AdminExceptionHandler {
    
	private static final Logger logger = LoggerFactory.getLogger(AdminExceptionHandler.class);
	
    /***
     * 全局异常
     * @param ex
     * @return
     */
	@ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Map javaExceptionHandler(Exception ex) {
        logger.error("捕获到Exception异常",ex);
        //异常日志入库
        Map map = new HashMap<>();
        map.put("code", 100);
        map.put("msg", ex.getMessage());
        return map;
    }

	/***
	 * 自定义异常捕获处理
	 * @param ex
	 * @return
	 */
   @ResponseBody
   @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
   public ModelAndView myShiroErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
	   
	   logger.info("捕获到shiro权限异常");
	   response.setContentType("application/json;charset=utf-8");
	   PrintWriter writer = response.getWriter();
	   writer.write("shirError");
	   writer.flush();
	   return null;
   }

   
   private ModelAndView createModelAndView(HttpServletRequest request, String viewName, HttpStatus status, Exception e)
   {
      ModelAndView mav = new ModelAndView();
      if (e != null)
      {
         mav.addObject("error", e);
      }
      mav.addObject("url", request.getRequestURI());
      mav.addObject("method", request.getMethod());
      if (status != null)
      {
         mav.setStatus(status);
      }
      mav.setViewName(viewName);
      return mav;
   }

}