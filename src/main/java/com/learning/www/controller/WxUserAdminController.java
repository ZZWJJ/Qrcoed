package com.learning.www.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.WriterException;
import com.learning.www.entity.ComInfo;
import com.learning.www.entity.User;
import com.learning.www.entity.WxUser;
import com.learning.www.service.CompanyInfoService;
import com.learning.www.service.UserMapperService;
import com.learning.www.service.WxUserService;
import com.learning.www.utils.QrCodeUtil;

@Controller
@RequestMapping("wxAdmin")
public class WxUserAdminController {

	@Autowired
	WxUserService wxUserService;
	@Autowired
	UserMapperService userService;
	@Autowired
	CompanyInfoService comService;
	
	/***
	 * 求职者管理页面
	 * @return
	 */
	@RequestMapping("toWxUserAdmin")
	public String toWxUserAdmin() {
		return "back_wxuser/back_wxuser";
	}
	
	/***
	 * 求职者列表
	 * @return
	 */
	@RequestMapping("getWxUserList")
	@ResponseBody
	public List<WxUser> getWxUserList(){
		
		List<WxUser> wxUserList = new ArrayList<WxUser>();
		wxUserList = wxUserService.getWxUserList();
		return wxUserList;
	}
	
	/***
	 * 新增微信用户
	 * @param wxuser
	 * @return
	 */
	@RequestMapping("postWxUser")
	@ResponseBody
	public int postWxUser(WxUser wxuser) {		
		int ret = 0;
		
		if(null != wxUserService.getWxUserByTelphone(wxuser.getTelphone())) {
			return 4;// 4: 代表 此号码已经注册过
		}else {
			ret = wxUserService.postWxUser(wxuser);
			User user = new User();
			user.setUsername(wxuser.getTelphone());
			String password = wxuser.getTelphone().substring(5);
			user.setPassword(password);
			user.setType(0);//0 : 代表 微信用户
			userService.postUserInfo(user);
		}		
		return ret;
	}
	
	/***
	 * 更新微信用户
	 * @return
	 */
	@RequestMapping("putWxUser")
	@ResponseBody
	public int putWxUser(WxUser wxuser) {
		
		int ret = wxUserService.putWxUserById(wxuser);
				
		return ret;		
	}
	
	/***
	 * DELETE：删除	指定ID的用户
	 * @param id
	 * @return
	 */
	@RequestMapping("deleteWxUser")
	@ResponseBody
	public int deleteWxUser(@RequestParam("ids[]") int[] ids) {
		int flag = 1;
		for (int id : ids) {			
			WxUser wxuser = wxUserService.getWxUserById(id);
			int ret1 = userService.deleteUserByUsername(wxuser.getTelphone());
			wxUserService.deleteWxUser_JobByUserId(id);
			int ret =wxUserService.deleteWxUser(id);
			if(ret == 0 || ret1 == 0) {
				flag = 0;
				return flag;
			}			
		}					
		return flag;		
	}
	
	/***
	 * 生成二维码
	 * @param id
	 * @param cname
	 * @return
	 */
	@RequestMapping("createQrCode")
	@ResponseBody
	public Boolean createQrCode(int id , String cname) {
		Boolean ret = false;
		try {
			ret = QrCodeUtil.createQrCode("D:\\QrCode\\"+cname+".jpg","http://www.yanchengjj.com:8099/wxuser/getComInfoById?id="+id,1500,"JPEG");
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret ;				
	}	
	
	/***
	 * 一键生成所有二维码
	 */
	@RequestMapping("downLoadQrcode")
	@ResponseBody
	public Boolean downLoadQrcode() {
		Boolean ret = false;
		List<ComInfo> comInfoList = comService.getComList(); 
		try {
			for (ComInfo comInfo : comInfoList) {
				ret = QrCodeUtil.createQrCode("D:\\QrCode\\"+comInfo.getCname()+".jpg",
						"http://www.yanchengjj.com:8099/wxuser/getComInfoById?id="+comInfo.getId(),1500,"JPEG");
				if(ret != true) {
					return false;
				}
			}
			
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return ret;
	
	}
}
