package me.veryvic.webMagic.ui;

import me.veryvic.webMagic.core.AliJsonSearcher;
import me.veryvic.webMagic.core.AliPageSearcher;
import me.veryvic.webMagic.core.AliSpider;
import me.veryvic.webMagic.core.URLEncoderUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by vic on 2015/1/31.
 */
public class MainFrame extends JFrame {
    // 搜索文本框
    private JPanel panel;
    private JTextField textField;
    private boolean hasInput = false;
    private JButton button;
    private static Executor executor = Executors.newFixedThreadPool(10);

    public MainFrame() {
        panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        textField = new JTextField();
        textField.setBounds(this.getWidth() / 2 - 100, 20, 200, 50);
        textField.setText("请输入要搜索的关键字");
        textField.setFont(new Font("楷体", Font.LAYOUT_LEFT_TO_RIGHT, 15));
//        textField.addFocusListener(getTextFieldFocusListener());
        textField.setVisible(true);
        button = new JButton("搜索");
        button.setSize(100, 30);
        button.setFont(new Font("宋体", Font.LAYOUT_LEFT_TO_RIGHT, 15));
        button.addMouseListener(getButtonOnClickListener());
        this.addWindowListener(getCloseListener());
        this.getContentPane().add(panel);
        panel.add(textField);
        panel.add(button);
        this.pack();
        this.setVisible(true);
    }


    private FocusListener getTextFieldFocusListener() {
        return new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (e.getComponent().equals(textField)) {
                    if (!hasInput) {
                        textField.setText("");
                    }
                    hasInput = true;
                }

            }
        };
    }

    private MouseListener getButtonOnClickListener(){
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                executor.execute(new AliSpider(new AliJsonSearcher(), getJsonTargetUrl(), 3, "d:/test"));
                executor.execute(new AliSpider(new AliPageSearcher(), getPageTargetUrl(), 3, "d/test"));
            }
        };
    }

    private String getPageTargetUrl(){
        return "http://s.1688.com/selloffer/-" + URLEncoderUtil.getSearchHtml(textField.getText()) +".html";
    }

    private String getJsonTargetUrl(){
        return "http://s.1688.com/selloffer/rpc_offer_search.jsonp?keywords=" + URLEncoderUtil.getSearchHtml(textField.getText()) + "&from=marketSearch&async=true&asyncCount=20&startIndex=40&qrwRedirectEnabled=false&offset=0&isWideScreen=false&controls=_template_%5Eofferresult%2Cshopwindow%2CshopwindowOfferResult.vm%7C_moduleConfig_%5EshopwindowResultConfig%7CpageSize%5E60%7C_name_%5EofferResult%7Coffset%5E0&token=1451661909&callback=jQuery17207938945458019054_1422694555688";
    }

    private WindowListener getCloseListener(){
        return new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        };
    }

}
