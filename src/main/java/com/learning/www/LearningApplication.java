package com.learning.www;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

import com.google.zxing.WriterException;
import com.learning.www.utils.PrintImage;
import com.learning.www.utils.PrintJobToImg;
import com.learning.www.utils.QrCodeUtil;

@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
@MapperScan("com.learning.www.mapper")
public class LearningApplication extends SpringBootServletInitializer {


	/**
	 * 修改启动类，继承 SpringBootServletInitializer 并重写 configure 方法
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(LearningApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LearningApplication.class, args);
//	      PrintImage tt = new PrintImage();
//	      BufferedImage d = tt.loadImageLocal("D:\\test\\muban.jpg");
//	      String title = "撒大大是多少有限公司";
//	      int x = title.length() * 96;
//	      // 公司标题 72号字体==96px
//	      tt.setFont(new Font("造字工房力黑（非商用）常规体", Font.BOLD, 76));
//	      //tt.modifyImage(d, title, (1920-x)/2-960, -420, new Color(65,105,225));
//	      //tt.modifyShapImg(d, title, (1920-x)/2-960, -420);
//	      tt.modifyShapImg(d, title, (1920-x)/2, 130);
//	      
//	      //公司简介，限定字数
//	      tt.setFont(new Font("黑体",Font.PLAIN, 30));
//	      String str = "功能：可以实现在图片模板上写内容并保存"+"功能：可以实现在图片模板上写内容并保存"+"功能：可以实现在图片模板上写内容并保存"
//	      +"功能：可以实现在图片模板上写内容并保存"+"功能：可以实现在图片模板上写内容并保存"+"功能：可以实现在图片模板上写内容并保存"+"功能：可以实现在图片模板上写内容并保存"+"功能：可以实现在图片模板上写内容并保存"
//	      +"功能：可以实现在图片模板上写内容并保存"+"功能：可以实现在图片模板上写内容并保存"+"功能：可以实现在图片模板上写内容并保存"+"功能：可以实现在图片模板上写内容并保存"+"功能：可以实现在图片模板上写内容并保存"
//	      +"功能：可以实现在图片模板上写内容并保存"+"功能：可以实现在图片模板上写内容并保存"+"功能：可以实现在图片模板上写内容并保存"+"功能：可以实现在图片模板上写内容并保存"+"功能：可以实现在图片模板上写内容并保存";
//	      System.out.println(str.length());
//	      //计算字符串长度
//	      int strleth=str.length();
//	      //计算循环次数
//	      int num = strleth/20;
//	      //字符串截取第一位
//	      int subindex = 0;
//	      //字符串截取第二位
//    	  int j = 20;
//    	  //距离y轴的位置
//    	  int y = -350;
//	      String[] s = new String[num+1];
//	      if(num < 1 )	{
//	    	  System.out.println(num);
//	    	  tt.modifyImage(d, str, -830, y,new Color(0,0,0));
//	      }else {
//		      for(int i = 0;i<num;i++) {
//		    	  s[i] = str.substring(subindex, j);
//		    	  tt.modifyImage(d, s[i], -830, y,new Color(0,0,0));
//		    	  subindex=j;
//		    	  j+=20;
//		    	  y+=35;
//		      }
//		      if(strleth%20 != 0) {
//		    	  //System.out.println("不等于0");
//		    	  s[num] = str.substring(num * 20);
//		    	  tt.modifyImage(d, s[num], -830, y,new Color(0,0,0));
//		      }
//	      }
//	      // 公司岗位6个
//	      String job1 = "普工";
//	      String amount1 = "3人";
//	      String salary1 = "4000元/月";
//	      String need1 = "吃苦耐劳，具有专业的技术能力。吃苦耐劳，具有专业的技术能力。吃苦耐劳，具有专业的技术能力。吃苦耐劳，具有专业的技术能力。吃苦耐劳，具有专业的技术能力。"
//	      		+ "吃苦耐劳，具有专业的技术能力。";
//	      y = -350;
//	      PrintJobToImg.printJobToImg(tt, d, job1, need1, amount1, salary1, y);
//	      PrintJobToImg.printJobToImg(tt, d, job1, need1, amount1, salary1, y+110);
//	      PrintJobToImg.printJobToImg(tt, d, job1, need1, amount1, salary1, y+220);
//	      PrintJobToImg.printJobToImg(tt, d, job1, need1, amount1, salary1, y+330);
//	      PrintJobToImg.printJobToImg(tt, d, job1, need1, amount1, salary1, y+440);
//	      PrintJobToImg.printJobToImg(tt, d, job1, need1, amount1, salary1, y+550);
//	      
//	      // 联系方式和抵地址
//	      String name = "张先生";
//	      String tel = "12334343443";
//	      String company = "盐都区高新区振兴路汇鑫大厦";
//	      tt.setFont(new Font("黑体",Font.BOLD, 40));
//	      tt.modifyImage(d, name, -650, 360,new Color(0,0,0));
//	      tt.modifyImage(d, tel, -450, 360,new Color(0,0,0));
//	      tt.modifyImage(d, company, -650, 440,new Color(0,0,0));
//	      
//	      
//	      //tt.modifyImage(d, str, -830, -100);
//	      tt.writeImageLocal("D:\\test\\cc_1.jpg", d);
//	      System.out.println("success");
//	      System.out.println(s[0]);
//	      System.out.println(s[0].length());
	      //查阅本地的字体库
//          GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//          String[] fontList = ge.getAvailableFontFamilyNames();
//          for(int i=0;i<fontList.length;i++)
//          {
//               System.out.println("字体："+fontList[i]);
//          }
//		try {
//			QrCodeUtil.createQrCode("D:\\test\\qrcode.jpg","http://www.yanchengjj.com:8099/wxuser/getComInfoById?id=17",1500,"JPEG");
//		} catch (WriterException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
