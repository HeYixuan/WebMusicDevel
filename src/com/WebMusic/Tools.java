package com.WebMusic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 * <p>
 * 该功能类实现了大部分音乐类所需要的函数功能
 * 
 **/

public class Tools {
	/**
	 * <p>
	 * 定义的一些主要变量
	 * 
	 */
	public String str = "", tmp = "", line = "", cookie = "", ua = "", requestMenthod = "";

	/**
	 * <p>
	 * 定义的一些固定变量
	 * 
	 */
	public final static String cookie_bak = "",
			ua_bak = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36",
			match_s = "\"(.+?\")", kg_re_str = ":|,|\"", kg_match_str = "\":" + match_s,
			enc_Sec_Key = "8a0d839b115fc4b9a8a78a585139b6605a4ef155e06bd367972d0f0d383c2da69bc4d94a0bcd5b74418912764589c0e66e5dd2b32e625d2715e3b26d8c20df151c353fda16cfefa652d9ff5d043ac783cff8caa1b9aa9be19f47f62c80019fb74a033e299a09ea36da8529490038a9ee1b59ef35c6cbc1acfc77800646b44882";
	public int len = -1, size = -1, num = 0;
	public List<WebMusicInfo> list = null;
	public String array[] = null;
	/**
	 * <p>
	 * 定义的一些固定变量
	 * 
	 */
	public WebMusicInfo musicInfo, musicInfoArray[];
	
	public void MultiThreadDownload(String dirPath, String FileName, String url_name)
	{
		new Thread() {
			@Override
			public void run() {
				MusicDownload(dirPath, FileName, url_name);
			}
		}.start();
	}
	
	public void sleep(int time)
	{
		try {
			new Thread();
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>
	 * 用于下载音乐的函数
	 */

	public void MusicDownload(String dirPath, String FileName, String url_name) {
		try {
			File dir = new File(dirPath);
			if (!dir.exists()) {
				dir.mkdirs();
				MusicDownload(dirPath, FileName, url_name);
			} else {
				File file = new File(dirPath + "/" + FileName + ".mp3");
				if (file.exists()) {
					System.err.println("有重复的文件");
				} else {
					byte buff[] = new byte[1024];
					FileOutputStream fos = new FileOutputStream(file);
					InputStream input = checkCon(url_name).getInputStream();
					while ((len = input.read(buff)) != -1) {
						fos.write(buff, 0, len);
					}
					fos.close();
					input.close();
					System.err.println("download ok! -- save to " + file);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getByJson(String src , String name)
	{
		return getByString(src, "\""+name+"\":(.+?\")", name+"\":|\"");
	}
	
	public String getByString(String src, String reg, String re_str) {
		String t = "";
		Matcher m = Pattern.compile(reg).matcher(src);
		if (m.find()) {
			t = m.group().replaceAll(re_str, "");
		}
		return t;
	}

	/**
	 * <p>
	 * 用于配置请求头参数
	 */

	public HttpURLConnection checkCon(String url_name) throws Exception {
		HttpURLConnection huc = (HttpURLConnection) new URL(url_name).openConnection();
		huc.setRequestMethod("GET");
		huc.setRequestProperty("accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
		huc.setRequestProperty("accept-encoding", "gzip, deflate, br");
		huc.setRequestProperty("Content-Type", "text/plain");
		huc.setRequestProperty("Cache-Control", "max-age=0");
		huc.setRequestProperty("Connection", "keep-alive");
		huc.setRequestProperty("accept-language", "zh-CN,zh;q=0.9");
		if (!getUa().equals("")) {
			huc.setRequestProperty("user-agent", getUa());
		} else {
			huc.setRequestProperty("user-agent", ua_bak);
			setUa(ua_bak);
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
		str = "";
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
		str = "";
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
		tmp = "";
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
		str = "";
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
		char ch;
		while (m.find()) {
			tmp = m.group(2);
			ch = (char) Integer.parseInt(tmp, 16);
			str = m.group(1);
			unicode = unicode.replace(str, ch + "");
		}

		return unicode;
	}

	/**
	 * <p>
	 * 读取本地文件
	 */
	public String read(String path) {
		tmp = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(path)));
			while ((line = br.readLine()) != null) {
				tmp += line + "\n";
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tmp;
	}

	/**
	 * <p>
	 * 返回设置的cookie
	 */
	public String getCookie() {
		return cookie;
	}

	/**
	 * <p>
	 * 返回设置的ua
	 */
	public String getUa() {
		return ua;
	}

	/**
	 * <p>
	 * 设置cookie
	 */
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	/**
	 * <p>
	 * 设置ua
	 */
	public void setUa(String ua) {
		this.ua = ua;
	}

	/**
	 * <p>
	 * 返回设置的requestMenthod
	 */
	public String getRequestMenthod() {
		return requestMenthod;
	}

	/**
	 * <p>
	 * 设置requestMenthod
	 */
	public void setRequestMenthod(String requestMenthod) {
		this.requestMenthod = requestMenthod;
	}

	/**
	 * <p>
	 * 直接输出带有歌曲信息的链表
	 */

	public void printList(List<WebMusicInfo> l) {
		System.out.println(printListString(l));
	}

	/**
	 * <p>
	 * 返回一个字符串类型的歌曲信息
	 */
	public String printListString(List<WebMusicInfo> l) {
		str = "";
		int n = 0;
		for (WebMusicInfo w : l) {
			str += w.getAllToCloud() + "\n";
			n++;
		}
		System.err.println("一共遍历出: "+n+" 个数据\n");
		return str;
	}

	/**
	 * <p>
	 * 返回一个字符串数组类型的歌曲信息
	 */
	public String[] printListStringArray(List<WebMusicInfo> l) {
		return printListString(l).split("\n");
	}

	/**
	 * <p>
	 * 返回一个WebMusicInfo数组类型的歌曲信息
	 * 
	 * @param 传入一个带有WebMusicInfo的List
	 * @return WebMusicInfo
	 */
	public WebMusicInfo[] printListInfo(List<WebMusicInfo> l) {
		num = 0;
		size = l.size();
		int n = 0;
		musicInfoArray = new WebMusicInfo[size];
		for (WebMusicInfo w : l) {
			musicInfoArray[num] = w;
			num++;
			n++;
		}
		System.err.println("一共遍历出: "+n+" 个数据\n");
		return musicInfoArray;
	}
}
