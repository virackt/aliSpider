package me.veryvic.webMagic.core;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by vic on 2015/1/31.
 */
public class URLEncoderUtil {
    public static void main(String[] args){
        try{
            String result = URLEncoder.encode("电子烟", "GBK");
            System.out.println(result);
            System.out.println(result.replaceAll("\\%", ""));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String getURLEncodeStr(String input){
        try{
            return URLEncoder.encode(input, "GBK");
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return "";
    }

    public static String getSearchHtml(String input){
        return getURLEncodeStr(input).replaceAll("\\%", "");
    }
}
