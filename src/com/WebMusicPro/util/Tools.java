package com.WebMusicPro.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import com.WebMusic.LinkList;
import com.WebMusicPro.OtherMusicPro.OtherMusicProUtils;
import com.WebMusicPro.QQMusicPro.QQMusicProUtils;

/**
* <p>创建时间：2019年1月29日 下午6:32:02
* <p>项目名称：WebMusic
* 
* <p>类说明：
*
* @version 1.0
* @since JDK 1.8
* 文件名称：Tools.java
* */
public class Tools {
	
	private String cookie = "" , userAgent = "";
	private final static int time = 3500;
	
	public void MultiThreadDownlaodMusic(String type , String fileName , String savePath , String format)
	{
		new Thread() {@Override
			public void run() {
				Downlaod(type, savePath, fileName, format);
			}}.start();
			sleep(time);
	}
	
	public void sleep(int times)
	{
		try {
			new Thread();
			Thread.sleep(times);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	public String checkOhterFormat(String format , String song_id , String Mtype)
	{
		WebMusicProInfo w = new OtherMusicProUtils().Info(song_id, Mtype);
		String f = "" , r = "" , type = "";
		if(format.equals("ape"))
		{
			f = MusicFormat.ApeEnd;
			type = w.getAPE();
		}else if(format.equals("flac"))
		{
			f = MusicFormat.FlacEnd;
			type = w.getFLAC();
		}else if(format.equals("m320k"))
		{
			f = MusicFormat.QQ320kEnd;
			type = w.getM320k();
		}else if(format.equals("m128k"))
		{
			f = MusicFormat.QQ128kEnd;
			type = w.getM128k();
		}else
		{
			f = MusicFormat.m4aEnd;
			type = w.getM4a();
		}
		r = f+"--"+type+"--"+w.getFileName();
		return r;
	}
	
	public String checkFormat(String format , String song_id)
	{
		WebMusicProInfo w = new QQMusicProUtils().getSongInfo(song_id, "", "");
		String f = "" , r = "" , type = "";
		if(format.equals("ape"))
		{
			f = MusicFormat.ApeEnd;
			type = w.getAPE();
		}else if(format.equals("flac"))
		{
			f = MusicFormat.FlacEnd;
			type = w.getFLAC();
		}else if(format.equals("m320k"))
		{
			f = MusicFormat.QQ320kEnd;
			type = w.getM320k();
		}else if(format.equals("m128k"))
		{
			f = MusicFormat.QQ128kEnd;
			type = w.getM128k();
		}else
		{
			f = MusicFormat.m4aEnd;
			type = w.getM4a();
		}
		r = f+"--"+type+"--"+w.getFileName();
		return r;
	}
	
	public void Downlaod(String url_name , String dirPath , String fileName , String format)
	{
		if(url_name.equals(""))
		{
			System.err.println("skip : "+fileName);
		}else if(url_name.length() < 10)
		{
			System.err.println("skip : "+fileName);
		}else
		{
			File d = new File(dirPath);
			if(!d.exists())
			{
				d.mkdirs();
				Downlaod(url_name, dirPath, fileName, format);
			}else
			{
				File f = new File(dirPath+"/"+fileName+format);
				if(f.exists())
				{
					System.err.println("file is exists : skip ------ " +f);
				}else
				{
					try {
						InputStream in = checkCon(url_name).getInputStream();
						FileOutputStream fos = new FileOutputStream(f);
						int len = -1;
						byte buff[] = new byte[1024];
						System.err.println("start download : "+url_name);
						while((len = in.read(buff)) != -1)
						{
							fos.write(buff, 0, len);
						}
						fos.close();
						in.close();
						System.err.println("downlaod ok ! ---> " + f);
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	public String getMusicListPage(String link) {
		String page = "" , line = "";
		HttpURLConnection c = null;
		try {
			c = (HttpURLConnection) new URL(LinkList.QQMusicListLink).openConnection();
			c.setRequestMethod("POST");
			c.setRequestProperty("accept", "application/json, text/javascript, */*; q=0.01");
			c.setRequestProperty("accept-encoding", "gzip, deflate, br");
			c.setRequestProperty("origin", "https://y.qq.com");
			c.setRequestProperty("referer", link);
			c.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
			c.setDoOutput(true);
			c.setDoInput(true);
			OutputStreamWriter w = new OutputStreamWriter(c.getOutputStream());
			w.write("type=1&json=1&utf8=1&disstid="
					+ link.substring(link.lastIndexOf("/") + 1).replaceAll(".ht(m|ml)", ""));
			w.flush();
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new GZIPInputStream(c.getInputStream()), "utf-8"));
			while ((line = br.readLine()) != null) {
				page += line + "\n";
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}
	
	
	public HttpURLConnection checkCon(String url_name) throws Exception {
		String ua_bak = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36";
		HttpURLConnection huc = (HttpURLConnection) new URL(url_name).openConnection();
		huc.setRequestMethod("GET");
		huc.setRequestProperty("accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		huc.setRequestProperty("accept-encoding", "gzip, deflate, br");
		huc.setRequestProperty("Content-Type", "text/plain");
		huc.setRequestProperty("Cache-Control", "max-age=0");
		huc.setRequestProperty("Connection", "keep-alive");
		huc.setRequestProperty("accept-language", "zh-CN,zh;q=0.9");
		if (!getUserAgent().equals("")) {
			huc.setRequestProperty("user-agent", getUserAgent());
		} else {
			huc.setRequestProperty("user-agent", ua_bak);
			setUserAgent(ua_bak);
		}
		if (!getCookie().equals("")) {
			huc.setRequestProperty("cookie", getCookie());
		}
		return huc;
	}
	
	/**
	 * <p>
	 * 获取普通网页,html格式
	 */

	public String getPagesource(String url_name) {
		String str = "" , line = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(checkCon(url_name).getInputStream(), "utf-8"));
			while ((line = br.readLine()) != null) {
				str += line + "\n";
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * <p>
	 * 获取gzip传输格式的网页内容
	 */

	public String getGzipPagesource(String url_name) {
		String str = "" , line = "";
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new GZIPInputStream(checkCon(url_name).getInputStream()), "utf-8"));
			while ((line = br.readLine()) != null) {
				str += line + "\n";
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
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
		Matcher m = Pattern.compile("(\\\\u(\\p{XDigit}{4}))").matcher(unicode);
		String tmp = "" , str = "";
		char ch;
		while (m.find()) {
			tmp = m.group(2);
			ch = (char) Integer.parseInt(tmp, 16);
			str = m.group(1);
			unicode = unicode.replace(str, ch + "");
		}

		return unicode;
	}
	
	protected String getByJson(String src , String name)
	{
		return getByString(src, "\""+name+"\":(.+?\")", name+"\":|\"");
	}
	
	protected String getByString(String src, String reg, String re_str) {
		String t = "";
		Matcher m = Pattern.compile(reg).matcher(src);
		if (m.find()) {
			t = m.group().replaceAll(re_str, "");
		}
		return t;
	}
	
	public String getCookie() {
		return cookie;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * <p>
	 * 直接输出带有歌曲信息的链表
	 */

	public void printList(List<WebMusicProInfo> l) {
		System.out.println(printListString(l));
	}

	/**
	 * <p>
	 * 返回一个字符串类型的歌曲信息
	 */
	public String printListString(List<WebMusicProInfo> l) {
		String str = "";
		int n = 0;
		for (WebMusicProInfo w : l) {
			str += w.getQQProAll() + "\n";
			n++;
		}
		System.err.println("一共遍历出: "+n+" 个数据");
		return str;
	}

	/**
	 * <p>
	 * 返回一个字符串数组类型的歌曲信息
	 */
	public String[] printListStringArray(List<WebMusicProInfo> l) {
		return printListString(l).split("\n");
	}

	/**
	 * <p>
	 * 返回一个WebMusicInfo数组类型的歌曲信息
	 * 
	 * @param 传入一个带有WebMusicInfo的List
	 * @return WebMusicInfo
	 */
	public WebMusicProInfo[] printListInfo(List<WebMusicProInfo> l) {
		int num = 0,size = l.size();
		int n = 0;
		WebMusicProInfo musicInfoArray[] = new WebMusicProInfo[size];
		for (WebMusicProInfo w : l) {
			musicInfoArray[num] = w;
			num++;
			n++;
		}
		System.err.println("一共遍历出: "+n+" 个数据");
		return musicInfoArray;
	}
}
