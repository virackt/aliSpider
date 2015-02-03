package me.veryvic.webMagic.ui;

import java.awt.*;

/**
 * Created by vic on 2015/2/1.
 */
public class Bootrap {
    public static void main(String[] args){
        MainFrame frame = new MainFrame();
        frame.setTitle("Ali商业信息爬虫");
        Toolkit kit = Toolkit.getDefaultToolkit();
        int width = 400;
        int height = 200;
        int screenWidth = kit.getScreenSize().width;
        int screenHeight = kit.getScreenSize().height;
        frame.setBounds((screenWidth - width)/ 2, (screenHeight - height) / 2 , width, height);
        frame.setResizable(false);
    }
}
