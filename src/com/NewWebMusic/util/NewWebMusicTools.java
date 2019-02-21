package com.NewWebMusic.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.WebMusic.WebMusicInfo;

/**
 * <p>
 * ����ʱ�䣺2019��1��20�� ����11:04:11
 * <p>
 * ��Ŀ���ƣ�WebMusic
 * 
 * <p>
 * ��˵���� �ṩ��Ҫ�õ��Ĺ���
 * <p>
 * ע��ʱ��: 2019��1��22��
 * 
 * @version 1.0
 * @since JDK 1.8 �ļ����ƣ�Tools.java
 */
public class NewWebMusicTools {
	/**
	 * <p>
	 * ֱ��������и�����Ϣ������
	 */

	public void printList(List<WebMusicInfo> l) {
		System.out.println(printListString(l));
	}

	/**
	 * <p>
	 * ����һ���ַ������͵ĸ�����Ϣ
	 */
	public String printListString(List<WebMusicInfo> l) {
		String str = "";
		int n = 0;
		for (WebMusicInfo w : l) {
			str += w.getNewWebMusic() + "\n";
			n++;
		}
		System.err.println("һ��������: " + n + " ������\n");
		return str;
	}

	/**
	 * <p>
	 * ����һ���ַ����������͵ĸ�����Ϣ
	 */
	public String[] printListStringArray(List<WebMusicInfo> l) {
		return printListString(l).split("\n");
	}

	/**
	 * <p>
	 * ����һ��WebMusicInfo�������͵ĸ�����Ϣ
	 * 
	 * @param ����һ������WebMusicInfo��List
	 * @return WebMusicInfo
	 */
	public WebMusicInfo[] printListInfo(List<WebMusicInfo> l) {
		int num = 0;
		int n = 0;
		int size = l.size();
		WebMusicInfo[] musicInfoArray = new WebMusicInfo[size];
		for (WebMusicInfo w : l) {
			musicInfoArray[num] = w;
			num++;
			n++;
		}
		System.err.println("һ��������: " + n + " ������");
		return musicInfoArray;
	}
	
	public void MultiThreadDownload(String dirPath, String FileName, String url_name)
	{
		new Thread() {
			@Override
			public void run() {
				MusicDownload(dirPath, FileName, url_name);
			}
		}.start();
	}
	
	@SuppressWarnings("static-access")
	public void sleep(int time)
	{
		try {
			new Thread().sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * <p>
	 * ��������
	 * <p>
	 * ��Ҫ��������������������ļ���·����������ļ����ơ�Ŀ������
	 */
	public void MusicDownload(String dirPath, String fileName, String url) {
		File dirP = new File(dirPath);
		if (!dirP.exists()) {
			dirP.mkdirs();
			MusicDownload(dirPath, fileName, url);
		} else {
			File file = new File(dirPath + "/" + fileName + ".mp3");
			if (file.exists()) {
				System.err.println(file + " file exists !");
			} else {
				NetTools net = new NetTools();
				String headers[] = { "Connection: keep-alive" };
				byte buff[] = new byte[1024];
				int len = -1;
				try {
					net.setHeaders(headers);
					FileOutputStream fos = new FileOutputStream(file);
					InputStream input = net.checkURL(url).getInputStream();
					while ((len = input.read(buff)) != -1) {
						fos.write(buff, 0, len);
					}
					fos.close();
					input.close();
					System.err.println("download ok! -- save to " + file);
				} catch (Exception e) {

					e.printStackTrace();
				}

			}
		}
	}

	/**
	 * <p>
	 * ��ȡ�����ļ�
	 * <p>
	 * ��Ҫ����һ���������ļ�·��
	 */
	public String readFile(String filePath) {
		String str = "", line = "";
		File file = new File(filePath);
		if (file.exists()) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(file));
				while ((line = br.readLine()) != null) {
					str += line + "\n";
				}
				br.close();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

		} else {
			System.err.println("û������ļ� : " + filePath);
		}
		return str;
	}

}
