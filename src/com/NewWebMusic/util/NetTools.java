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
 * ����ʱ�䣺2019��1��20�� ����10:29:38
 * <p>
 * ��Ŀ���ƣ�WebMusic
 * 
 * <p>
 * ��˵���� �ṩ��Ҫ�õ������繤��
 * <p>
 * ע��ʱ��: 2019��1��22��
 * 
 * @version 1.0
 * @since JDK 1.8 �ļ����ƣ�NewWebMusic.java
 */
public class NetTools {

	public String userAgent = "", requestMenthod = "";

	public String headers[] = null;

	/**
	 * <p>
	 * ����get�������
	 * <p>
	 * ��Ҫ����һ����������������
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
	 * ��ȡpost����ʽ��gzip��ҳ
	 * <p>
	 * ��Ҫ���������������������ơ����Ͳ���
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
	 * ��ȡgzipҳ������
	 * <p>
	 * ��Ҫ����һ����������������
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
	 * ��ȡҳ������
	 * <p>
	 * ��Ҫ����һ����������������
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
	 * ��ȡ���õ�����ͷ����
	 */
	public String[] getHeaders() {
		return headers;
	}

	/**
	 * <p>
	 * ��������ͷ����
	 * <p>
	 * ��Ҫ����һ����������������ͷ�������ַ�������
	 */
	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

	/**
	 * <p>
	 * ��ȡ���õ�UserAgent����
	 */
	public String getUserAgent() {
		return userAgent;
	}

	/**
	 * <p>
	 * ��ȡ���õ�RequestMenthod����
	 */
	public String getRequestMenthod() {
		return requestMenthod;
	}

	/**
	 * <p>
	 * ����UserAgent����
	 * <p>
	 * ��Ҫ����һ������������UserAgent���ַ���
	 */
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * <p>
	 * ����RequestMenthod����
	 * <p>
	 * ��Ҫ����һ������������RequestMenthod���ַ���
	 */
	public void setRequestMenthod(String requestMenthod) {
		this.requestMenthod = requestMenthod;
	}

}
