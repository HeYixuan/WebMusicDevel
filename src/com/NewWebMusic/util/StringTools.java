package com.NewWebMusic.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 创建时间：2019年1月20日 上午11:44:27
 * <p>
 * 项目名称：WebMusic
 * 
 * <p>
 * 类说明： 提供字符串操作的功能
 * <p>
 * 注释时间: 2019年1月22日
 * 
 * @version 1.0
 * @since JDK 1.8 文件名称：StringTools.java
 */
public class StringTools {

	/**
	 * <p>
	 * 输入正则返回匹配一次的数据
	 * <p>
	 * 需要传入三个参数：源字符串、正则、去除的文字内容
	 * <p>
	 * 最后一个可以不加，那样就直接返回没有删减的匹配结果
	 */
	public String getByString(String data, String reg, String re_str) {
		String str = "";
		Matcher m = Pattern.compile(reg).matcher(data);
		if (m.find()) {
			str = m.group().replaceAll(re_str, "");
		}
		return str;
	}

	/**
	 * <p>
	 * 获取json数据
	 * <p>
	 * 需要传入两个参数：json格式字符串、需要取出值的名称
	 */
	public String getJsonByName(String data, String name) {
		return getByString(data, "\"" + name + "\":\"(.+?\")", name + "\":|\"");
	}

	/**
	 * <p>
	 * 用于将中文字符串转义为URL可以认识的
	 */
	public String URLEncode(String src) {
		String tmp = "";
		try {
			tmp = URLEncoder.encode(src, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tmp;
	}

	/**
	 * <p>
	 * 将字符串转义为Unicode编码
	 * <p>
	 * 转义前可以看
	 * <p>
	 * 转义后不是人看的
	 */
	public String StringToUnicode(String string) {
		StringBuffer unicode = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			// 取出每一个字符
			char c = string.charAt(i);
			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}
		return unicode.toString();
	}

	/**
	 * <p>
	 * 字符串编码的转换,主要是懒得总try-catch,干脆封装成函数一键操作
	 */
	public String StringToEncode(String src, String encode) {
		String str = "";
		try {
			src = new String(src.getBytes(), encode);
			str = src;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;

	}

	/**
	 * <p>
	 * 将Unicode编码转义为字符串
	 * <p>
	 * 转义前不是人看
	 * <p>
	 * 转义后的可以看
	 */
	public String UnicodeToString(String unicode) // Unicode转String用的，因为这个酷狗音乐非常鸡肋，歌名、歌手、简介居然用的都是Unicode编码，不得不转一下，不然没法用
	{
		String tmp = "", str = "";
		Matcher m = Pattern.compile("(\\\\u(\\p{XDigit}{4}))").matcher(unicode);
		char ch;
		while (m.find()) {
			tmp = m.group(2);
			ch = (char) Integer.parseInt(tmp, 16);
			str = m.group(1);
			unicode = unicode.replace(str, ch + "");
		}

		return unicode;
	}

}
