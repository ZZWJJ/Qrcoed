package com.learning.www.controller;

import java.util.ArrayList;
import java.util.List;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learning.www.entity.ComInfo;
import com.learning.www.entity.Com_Zph;
import com.learning.www.entity.User;
import com.learning.www.entity.ZphInfo;
import com.learning.www.service.CompanyInfoService;
import com.learning.www.service.UserMapperService;
import com.learning.www.service.Zph2ComService;
import com.learning.www.service.ZphInfoService;
import com.learning.www.utils.AdminExceptionHandler;

@RequestMapping("com")
@Controller
public class CompanyInfoController extends AdminExceptionHandler{

	@Autowired
	CompanyInfoService companyinfoservice;
	
	@Autowired
	ZphInfoService zphinfoservice;
	
	@Autowired
	UserMapperService userservice;
	
	@Autowired
	Zph2ComService zphcomservice;
	
	private static Logger logger = LoggerFactory.getLogger(ZphInfoController.class);
	
	
	@RequestMapping("toCom")
	public String toCom() {
		return "company/com_list";
	}
	
	@RequestMapping("toMyCom")
	public String toMyCom() {
		return "user/user_com";
	}
	
	/***
	 * GET：查询	招聘会信息
	 * @param model
	 * @return
	 */
	@RequestMapping("getComInfo")
	@ResponseBody
	public List<ComInfo> getComInfo(Model model) {
		List<ComInfo> comList = new ArrayList<ComInfo>();
		comList = companyinfoservice.getComInfoList();
		//logger.info("取得所有企业信息："+comList.toString());
		return comList; 		
	} 
	
	/***
	 * GET：查询	根据id查找公司信息
	 * @param id
	 * @return
	 */
	@RequestMapping("getComInfoById")
	@ResponseBody
	public List<ComInfo> getComInfoById(int id) {
		List<ComInfo> comList = new ArrayList<ComInfo>();
		ComInfo com = new ComInfo();
		com = companyinfoservice.getComInfoById(id);
		comList.add(com);
		
		return comList;
	}
	
	/***
	 * GET：查询	根据公司id查找招聘会信息
	 * @param comid
	 * @return
	 */
	@RequestMapping("getZphComById")
	@ResponseBody
	public List<Com_Zph> getZphComById(int id) {  
		int [] Zphid = zphcomservice.getZph2ComByComId(id);
		ZphInfo zph = new ZphInfo();
		Com_Zph com_zph = new Com_Zph();
		//List<ZphInfo> zphList = new ArrayList<ZphInfo>();
		List<Com_Zph> ComZphList = new ArrayList<Com_Zph>();
		
		for (int zphid : Zphid) {
			zph = zphinfoservice.getZphInfoById(zphid);
			com_zph = zphcomservice.getZph2ComByComIdZphId(id,zphid);
			if(null != zph) {
				com_zph.setZphtitle(zph.getTitle());
				com_zph.setZphtime(zph.getTime());
				com_zph.setZphstate(zph.getState());				
				ComZphList.add(com_zph);
			}			
			//logger.info(ComZphList.toString());
		}
		//logger.info(ComZphList.toString());
		
		return ComZphList;
	}
	
	/***
	 * GET：查询	根据uid查找公司信息
	 * @param uid
	 * @return
	 */
	@RequestMapping("getComInfoByUid")
	@ResponseBody
	public List<ComInfo> getComInfoByUid() {
		
		Subject sub = SecurityUtils.getSubject();
		String loginName = (String) sub.getPrincipal();
		logger.info(loginName);
		User user = userservice.getPasswordByUsername(loginName);
		List<ComInfo> comList = new ArrayList<ComInfo>();
		comList = companyinfoservice.getComInfoByUid(user.getId());
		
		
		return comList;
	}
	
	/***
	 * POST：新增  公司信息
	 * @return
	 */
	//@RequiresRoles("user")
	@RequestMapping("postComInfo")
	@ResponseBody
	public int postComInfo(ComInfo cominfo ) {
		
		if(null != companyinfoservice.getComByCname(cominfo.getCname()) && 
				!companyinfoservice.getComByCname(cominfo.getCname()).equals("")) {
			return 3; // 3：此企业用户已经注册
		}		
		// 企业用户
		User user = new User();
		user.setUsername(cominfo.getCname());
		user.setPassword(cominfo.getPhone());
		user.setType(2);
		userservice.postUserInfo(user);
		
		int ret = companyinfoservice.postComInfo(cominfo);		
		logger.info("新增公司的id："+cominfo.getId());
		return ret;				
	}
	
	
	/***
	 * DELETE：删除	指定ID的公司
	 * @param id
	 * @return
	 */
	@RequestMapping("delComInfo")
	@ResponseBody
	public int deleteComInfo(@RequestParam("ids[]") int[] ids) {
		int flag = 1;
		//int Id = Integer.parseInt(id);
		for (int id : ids) {
			//int Id = Integer.parseInt(id);
			int ret1 = userservice.deleteUserByUsername(companyinfoservice.getComNameInfoById(id));
			int ret = companyinfoservice.deleteComInfo(id);			
			//int ret1 = zphcomservice.deleteComInfoByComId(id);
			if(ret == 0 || ret1 == 0) {
				flag = 0;
				logger.info("删除公司信息失败：公司id为"+id);
				return flag;
			}
			logger.info("删除公司信息：公司id为"+id);
		}						
		return flag;		
	}
	
	/***
	 * PUT：更新	招聘会信息
	 * @param zphinfo
	 * @return
	 */
	@RequestMapping("putComInfoById")
	@ResponseBody
	public int putZphInfoById(ComInfo cominfo) { 
		
		int ret = companyinfoservice.putComInfoById(cominfo);
		logger.info("已经更新公司信息，id为："+cominfo.getId());
		
		return ret;
	}
	
	/***
	 * post 增加对应id公司参与的招聘会id
	 * @param com
	 * @param zph
	 * @return
	 */
	@RequestMapping("getZphComid")
	@ResponseBody
	public int getZphComid(@RequestParam("comid[]") int[] com,@RequestParam("zphid[]") int[] zph) {		
//		logger.info("com:"+com[0]);
//		logger.info("zph:"+zph[0]);
		
		int ret = 1;
		for (int i = 0; i < zph.length; i++) {
			ret = zphcomservice.postZph2Com(zph[i], com[0]);
			if(ret == 0) {
				return 0;
			}
		}		
		
		return ret;
	}
	
	/***
	 * del 删除参展招聘会
	 * @param zphid
	 * @return
	 */
	@RequestMapping("deleteZph2ComById")
	@ResponseBody
	public int deleteZph2ComByZphId(int id) {		
		
		int ret = zphcomservice.deleteZph2ComByZphId(id);
		
		return ret;
	}
	
	/***
	 * 更改参展状态
	 * @param id
	 * @param isjoin
	 * @return
	 */
	@RequestMapping("changeJoinStateByid")
	@ResponseBody
	public int changeJoinStateByZphid(int id, int isjoin) {
		
		logger.info("输出信息：id="+id);
		if(isjoin == 0) {
			isjoin = 1;
		}else {
			isjoin = 0;
		}
		int ret = zphcomservice.putJoinStateByZphid(id,isjoin);
				
		return ret;		
	}
	
	/***
	 * put 更新备注信息
	 * @param addinfoid
	 * @param addinfo
	 * @return
	 */
	@RequestMapping("putAddInfoById")
	@ResponseBody
	public int putAddInfoById(int addinfoid,String addinfo) {
		
		int ret = zphcomservice.putAddInfoById(addinfoid, addinfo);		
		return ret;		
	}
	
	/***
	 * put 企业转移
	 * @param uid
	 * @param uname
	 * @param comid
	 * @return
	 */
	@RequestMapping("putUserInfoById")
	@ResponseBody
	public int putUserInfoById(int uid,String uname,int comid) {
		
		int ret = companyinfoservice.putUserById(uid, uname, comid);
		
		return ret;		
	}
	
	/***
	 * GET：查询	用户信息
	 * @param model
	 * @return
	 */
	@RequestMapping("getUserInfo")
	@ResponseBody
	public List<User> getUserInfo(Model model) {              
		List<User> userList = new ArrayList<User>();
		userList = userservice.getUserInfo();
		logger.info("取得所有用户信息："+userList.toString());
		return userList; 		
	} 
}
