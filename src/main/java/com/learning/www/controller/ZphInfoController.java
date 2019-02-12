package com.learning.www.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learning.www.entity.ComInfo;
import com.learning.www.entity.ZphInfo;
import com.learning.www.service.CompanyInfoService;
import com.learning.www.service.Zph2ComService;
import com.learning.www.service.ZphInfoService;

/***
 * GET：查询，POST：新增，PUT：更新，DELETE：删除
 * @author Administrator
 *
 */
@RequestMapping("zph")
@Controller
public class ZphInfoController {
	
	@Autowired
	private ZphInfoService zphservice;
	
	@Autowired
	private CompanyInfoService comservice;
	
	@Autowired
	Zph2ComService zphcomservice;
	
	private static Logger logger = LoggerFactory.getLogger(ZphInfoController.class);
	
	
	@RequestMapping("toZph")
	public String toZph() {
		return "zph/list_zph";
	}
	
	/***
	 * GET：查询	招聘会信息
	 * @param model
	 * @return
	 */
	@RequestMapping("getZphInfo")
	@ResponseBody
	public List<ZphInfo> getZphInfo(Model model) { 
				
		List<ZphInfo> zphinfolist = new ArrayList<ZphInfo>();
		zphinfolist = zphservice.getZphInfoList();
		model.addAttribute("zphinfolist", zphinfolist);
//		for (ZphInfo zphInfo : zphinfolist) {
//			logger.info(zphInfo.toString());
//		}		
		return zphinfolist;
		
	} 
	
	@RequestMapping("getZphInfobyid")
	@ResponseBody
	public List<ZphInfo> getZphInfoById(int id) { 
		
		//int zphid = Integer.parseInt(id);
		ZphInfo zphinfo = new ZphInfo();
		if(null == zphservice.getZphInfoById(id)) {			
			logger.info("未查找到信息！");
			return null;
		}
		List<ZphInfo> zpList = new ArrayList<ZphInfo>();
		zphinfo = zphservice.getZphInfoById(id);
		zpList.add(zphinfo);
		return zpList; 
		
	}
	
	/***
	 * POST：新增  招聘会信息
	 * @return
	 */
	@RequestMapping("postZphInfo")
	@ResponseBody
	public int postZphInfo(ZphInfo zphinfo) { 
		
		int ret = zphservice.postZphInfo(zphinfo);		
		logger.info("新增招聘会的id："+zphinfo.getId());
		return ret;				
	}
	
	
	/***
	 * DELETE：删除	指定ID的招聘会
	 * @param id
	 * @return
	 */
	@RequestMapping("delZphInfo")
	@ResponseBody
	public int deleteZphInfo(@RequestParam("ids[]") int[] ids) {
		int flag = 1;
		//int Id = Integer.parseInt(id);
		for (int id : ids) {
			//int Id = Integer.parseInt(id);
			int ret = zphservice.deleteZphInfo(id);
			int ret1 = zphcomservice.deleteZphInfoByZphId(id);
			if(ret == 0 || ret1 == 0) {
				flag = 0;
				logger.info("删除缓存失败：招聘会id为"+id);
				return flag;
			}
			logger.info("删除缓存：招聘会id为"+id);
		}						
		return flag;		
	}
	
	/***
	 * PUT：更新	招聘会信息
	 * @param zphinfo
	 * @return
	 */
	@RequestMapping("putZphInfoById")
	@ResponseBody
	public int putZphInfoById(ZphInfo zphinfo) { 
		
		int ret = zphservice.putZphInfoById(zphinfo);
		logger.info("已经更新，id为："+zphinfo.getId());
		
		return ret;
	}
		
	/***
	 * GET：查询	招聘会信息
	 * @param model
	 * @return
	 */
	@RequestMapping("getZphInfo2Com")
	@ResponseBody
	public List<ZphInfo> getZphInfo2Com(int comid) { 
		logger.info("comid:"+comid);
		int zphid[] = zphcomservice.getZph2ComByComId(comid);
		
		List<ZphInfo> zphinfolist = new ArrayList<ZphInfo>();
		zphinfolist = zphservice.getZphInfoList();
		
		for (int id : zphid) {
			zphinfolist.remove(zphservice.getZphInfoById(id));
		}
		logger.info("剩余的招聘会："+zphinfolist.toString());
		return zphinfolist;
		
	}
	
	/***
	 * GET：查询	招聘会信息
	 * @param model
	 * @return
	 */
	@RequestMapping("getZphInfo2ComByZphid")
	@ResponseBody
	public List<ComInfo> getZphInfo2ComByZphid(int zphid) { 
		logger.info("comid:"+zphid);
		int comid[] = zphcomservice.getZph2ComByZphId(zphid);
		List<ComInfo> comList = new ArrayList<ComInfo>();
		for (int id : comid) {
			ComInfo com = comservice.getComInfoById(id);			
			if(null != com) {				
				comList.add(com);
			}
		}		
		return comList;
		
	}
	
	/***
	 * put 更新招聘会状态
	 * @param zphid	0:进行中   1：已完结
	 * @param zphstate
	 * @return
	 */
	@RequestMapping("putZphStateById")
	@ResponseBody
	public int putZphStateById(int zphid,int zphstate) {
		//logger.info(zphinfo.getState() +"==="+ zphinfo.getId());
		ZphInfo zphinfo = zphservice.getZphInfoById(zphid);
		if(zphstate == 0) {
			zphinfo.setState(1);
		}else {
			zphinfo.setState(0);
		}
		int ret = zphservice.putZphStateById(zphinfo);
		
		return ret;
	}
}
