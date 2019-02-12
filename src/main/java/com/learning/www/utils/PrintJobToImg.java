package com.learning.www.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

public class PrintJobToImg {

	public static void printJobToImg(PrintImage tt,BufferedImage d,String job1,String need1,String amount1,String salary1,int y) {
		
		  
	      if(null != job1 && !job1.equals("")) {
	    	need1 = "岗位职责:"+need1;
	    	int strleth = need1.length()+5;
	    	int num = strleth/40;
	    	int subindex = 0;
	    	int j = 40;
	    	//y = -350;
	    	String[] s1 = new String[num+1];
	    	tt.setFont(new Font("黑体",Font.BOLD, 28));
	    	tt.modifyImage(d, "职位:"+job1, -50, y, new Color(0,191,255));
	    	tt.modifyImage(d, "人数:"+amount1, -30+(30*(3+job1.length())), y, new Color(210,105,30));
	    	tt.modifyImage(d, "月薪:"+salary1, -10+(30*(6+job1.length()+amount1.length())), y, new Color(178,34,34));
	    	y = y+25;
	    	tt.setFont(new Font("黑体",Font.PLAIN, 24));
		      if(num < 1 )	{
		    	  System.out.println(num);
		    	  tt.modifyImage(d, need1, -50, y+25,new Color(0,0,0));
		      }else {
			      for(int i = 0;i<num;i++) {
			    	  s1[i] = need1.substring(subindex, j);
			    	  tt.modifyImage(d, s1[i], -50, y,new Color(0,0,0));
			    	  subindex=j;
			    	  j+=40;
			    	  y+=25;
			      }
			      if(strleth%40 != 0) {
			    	  //System.out.println("不等于0");
			    	  s1[num] = need1.substring(num * 40);
			    	  tt.modifyImage(d, s1[num], -50, y,new Color(0,0,0));
			      }
		      }
	    	//tt.modifyImage(d, "岗位要求:"+need1, -50, y+25, new Color(0,0,0));
	      }
		
		
		
		
	}
	
}
