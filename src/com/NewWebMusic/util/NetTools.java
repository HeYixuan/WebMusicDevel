package com.NewWebMusic.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * <p>
 * 创建时间：2019年1月20日 上午10:29:38
 * <p>
 * 项目名称：WebMusic
 * 
 * <p>
 * 类说明： 提供需要用到的网络工具
 * <p>
 * 注释时间: 2019年1月22日
 * 
 * @version 1.0
 * @since JDK 1.8 文件名称：NewWebMusic.java
 */
public class NetTools {

	public String userAgent = "", requestMenthod = "";

	public String headers[] = null;

	/**
	 * <p>
	 * 配置get请求参数
	 * <p>
	 * 需要传入一个参数：链接名称
	 */
	public HttpURLConnection checkURL(String url_name) {
		HttpURLConnection con = null;
		try {
			con = (HttpURLConnection) new URL(url_name).openConnection();
			con.setRequestMethod("GET");

			if (getHeaders().length > 1) {
				for (String s : getHeaders()) {
					String array[] = s.split(":");
					con.setRequestProperty(array[0], array[1].replaceAll(" ", ""));
				}
			}

			if (!getUserAgent().equals("")) {
				con.setRequestProperty("user-agent", getUserAgent());
			} else {
				con.setRequestProperty("user-agent",
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	/**
	 * <p>
	 * 获取post请求方式的gzip网页
	 * <p>
	 * 需要传入两个参数：链接名称、发送参数
	 */
	public String getGzipPageOnPost(String url_name, String param) {
		String str = "", line = "";
		try {
			HttpURLConnection con = (HttpURLConnection) new URL(url_name).openConnection();
			con.setRequestMethod("POST");
			if (getHeaders().length > 1) {
				for (String s : getHeaders()) {
					String array[] = s.split(":");
					con.setRequestProperty(array[0], array[1].replaceAll(" ", ""));
				}
			}

			if (!getUserAgent().equals("")) {
				con.setRequestProperty("user-agent", getUserAgent());
			} else {
				con.setRequestProperty("user-agent",
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
			}

			con.setDoOutput(true);
			con.setDoInput(true);
			OutputStreamWriter w = new OutputStreamWriter(con.getOutputStream());
			w.write(param);
			w.flush();
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
			while ((line = br.readLine()) != null) {
				str += line + "\n";
			}
			br.close();
			w.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * <p>
	 * 获取gzip页面数据
	 * <p>
	 * 需要传入一个参数：链接名称
	 */
	public String getGzipPage(String url_name) {
		String str = "", line = "";
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new GZIPInputStream(checkURL(url_name).getInputStream()), "utf-8"));
			while ((line = br.readLine()) != null) {
				str += line + "\n";
			}
			br.close();
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return str;
	}

	/**
	 * <p>
	 * 获取页面数据
	 * <p>
	 * 需要传入一个参数：链接名称
	 */
	public String getPage(String url_name) {
		String str = "", line = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(checkURL(url_name).getInputStream(), "utf-8"));
			while ((line = br.readLine()) != null) {
				str += line + "\n";
			}
			br.close();
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return str;
	}

	/**
	 * <p>
	 * 获取设置的请求头内容
	 */
	public String[] getHeaders() {
		return headers;
	}

	/**
	 * <p>
	 * 设置请求头内容
	 * <p>
	 * 需要传入一个参数：带有请求头参数的字符串数组
	 */
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	/**
	 * <p>
	 * 获取设置的UserAgent内容
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * <p>
	 * 获取设置的RequestMenthod内容
	 */
	public String getRequestMenthod() {
		return requestMenthod;
	}

	/**
	 * <p>
	 * 设置UserAgent内容
	 * <p>
	 * 需要传入一个参数：带有UserAgent的字符串
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * <p>
	 * 设置RequestMenthod内容
	 * <p>
	 * 需要传入一个参数：带有RequestMenthod的字符串
	 */
	public void setRequestMenthod(String requestMenthod) {
		this.requestMenthod = requestMenthod;
	}

}
