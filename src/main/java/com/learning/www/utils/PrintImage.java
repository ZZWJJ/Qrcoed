package com.learning.www.utils;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
 
public class PrintImage {
 
    private Font       font     = new Font("黑体", Font.PLAIN, 25); // 添加字体的属性设置
 
    private Graphics2D g        = null;
 
    private int        fontsize = 0;
 
    /**
     * 导入本地图片到缓冲区
     */
    public BufferedImage loadImageLocal(String imgName) {
        try {
            return ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
 
    /**
     * 导入网络图片到缓冲区
     */
    public BufferedImage loadImageUrl(String imgName) {
        try {
            URL url = new URL(imgName);
            return ImageIO.read(url);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
 
    /**
     * 生成新图片到本地
     */
    public void writeImageLocal(String newImage, BufferedImage img) {
        if (newImage != null && img != null) {
            try {
                File outputfile = new File(newImage);
                ImageIO.write(img, "jpg", outputfile);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
 
    /**
     * 设定文字的字体等
     */
    public void setFont(Font font) {
        
        this.font = font;
    }
 
    /**
     * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
     */
    public BufferedImage modifyImage(BufferedImage img, Object content, int x, int y,Color color) {
        try {
            int w = img.getWidth();
            int h = img.getHeight();
            g = img.createGraphics();
            g.setBackground(Color.BLUE);
            
            
            //g.setColor(new Color(120, 120, 110));//设置字体颜色
            g.setColor(color);//设置字体颜色
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            g.setStroke(new BasicStroke(3));
            if (this.font != null)
                g.setFont(this.font);
            if (content != null) {
                g.translate(w / 2, h / 2);
                //g.rotate(8 * Math.PI / 180);
                g.drawString(content.toString(), x, y);
            }
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
 
        return img;
    }
 
    
    /**
     * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
     *
     * 时间:2007-10-8
     *
     * @param img
     * @return
     */
    public BufferedImage modifyImageYe(BufferedImage img) {
 
        try {
            int w = img.getWidth();
            int h = img.getHeight();
            g = img.createGraphics();
            g.setBackground(Color.WHITE);
            g.setColor(Color.blue);//设置字体颜色
            if (this.font != null)
                g.setFont(this.font);
            g.drawString("www.hi.baidu.com?xia_mingjian", w - 85, h - 5);
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
 
        return img;
    }
 
    public BufferedImage modifyImagetogeter(BufferedImage b, BufferedImage d) {
 
        try {
            int w = b.getWidth();
            int h = b.getHeight();
            g = d.createGraphics();
            g.drawImage(b, 100, 10, w, h, null);
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
 
        return d;
    }
    /***
     * 插入描边的字体
     * @param img
     * @param content
     * @param w
     * @param h
     * @return
     */
    public BufferedImage modifyShapImg(BufferedImage img, String content, int w, int h) {
//        int w = img.getWidth();
//        int h = img.getHeight();
        g = img.createGraphics();
    	
        //Font f = new Font("Courier New", Font.BOLD, 140);
        GlyphVector v = font.createGlyphVector(g.getFontMetrics(font).getFontRenderContext(), content);
        Shape shape = v.getOutline();
        if (content != null) {
            g.translate(w, h);
            //g.rotate(8 * Math.PI / 180);
            //g.drawString(content.toString(), x, y);
        }
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        g.setColor(new Color(0,90,160));
        g.fill(shape);
        g.setColor(new Color(248,248,255));
        g.setStroke(new BasicStroke(2));
        g.draw(shape);
        
    	return img;
    }
 
}

