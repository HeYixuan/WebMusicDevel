package com.NewWebMusic.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * ����ʱ�䣺2019��1��20�� ����11:44:27
 * <p>
 * ��Ŀ���ƣ�WebMusic
 * 
 * <p>
 * ��˵���� �ṩ�ַ��������Ĺ���
 * <p>
 * ע��ʱ��: 2019��1��22��
 * 
 * @version 1.0
 * @since JDK 1.8 �ļ����ƣ�StringTools.java
 */
public class StringTools {

	/**
	 * <p>
	 * �������򷵻�ƥ��һ�ε�����
	 * <p>
	 * ��Ҫ��������������Դ�ַ���������ȥ������������
	 * <p>
	 * ���һ�����Բ��ӣ�������ֱ�ӷ���û��ɾ����ƥ����
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
	 * ��ȡjson����
	 * <p>
	 * ��Ҫ��������������json��ʽ�ַ�������Ҫȡ��ֵ������
	 */
	public String getJsonByName(String data, String name) {
		return getByString(data, "\"" + name + "\":\"(.+?\")", name + "\":|\"");
	}

	/**
	 * <p>
	 * ���ڽ������ַ���ת��ΪURL������ʶ��
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
	 * ���ַ���ת��ΪUnicode����
	 * <p>
	 * ת��ǰ���Կ�
	 * <p>
	 * ת������˿���
	 */
	public String StringToUnicode(String string) {
		StringBuffer unicode = new StringBuffer();
		for (int i = 0; i < string.length(); i++) {
			// ȡ��ÿһ���ַ�
			char c = string.charAt(i);
			// ת��Ϊunicode
			unicode.append("\\u" + Integer.toHexString(c));
		}
		return unicode.toString();
	}

	/**
	 * <p>
	 * �ַ��������ת��,��Ҫ��������try-catch,�ɴ��װ�ɺ���һ������
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
	 * ��Unicode����ת��Ϊ�ַ���
	 * <p>
	 * ת��ǰ�����˿�
	 * <p>
	 * ת���Ŀ��Կ�
	 */
	public String UnicodeToString(String unicode) // UnicodeתString�õģ���Ϊ����ṷ���ַǳ����ߣ����������֡�����Ȼ�õĶ���Unicode���룬���ò�תһ�£���Ȼû����
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
