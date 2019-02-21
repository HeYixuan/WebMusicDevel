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
 * 创建时间：2019年1月20日 上午11:04:11
 * <p>
 * 项目名称：WebMusic
 * 
 * <p>
 * 类说明： 提供需要用到的工具
 * <p>
 * 注释时间: 2019年1月22日
 * 
 * @version 1.0
 * @since JDK 1.8 文件名称：Tools.java
 */
public class NewWebMusicTools {
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
		String str = "";
		int n = 0;
		for (WebMusicInfo w : l) {
			str += w.getNewWebMusic() + "\n";
			n++;
		}
		System.err.println("一共遍历出: " + n + " 个数据\n");
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
		int num = 0;
		int n = 0;
		int size = l.size();
		WebMusicInfo[] musicInfoArray = new WebMusicInfo[size];
		for (WebMusicInfo w : l) {
			musicInfoArray[num] = w;
			num++;
			n++;
		}
		System.err.println("一共遍历出: " + n + " 个数据");
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
	 * 下载音乐
	 * <p>
	 * 需要传入三个参数：保存的文件夹路径、保存的文件名称、目标链接
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
	 * 读取本地文件
	 * <p>
	 * 需要传入一个参数：文件路径
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
			System.err.println("没有这个文件 : " + filePath);
		}
		return str;
	}

}
