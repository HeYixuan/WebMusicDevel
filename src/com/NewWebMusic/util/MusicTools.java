package com.NewWebMusic.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.WebMusic.LinkList;
import com.WebMusic.WebMusicInfo;

/**
 * <p>
 * 创建时间：2019年1月20日 下午12:14:59
 * <p>
 * 项目名称：WebMusic
 * 
 * <p>
 * 类说明： 提供音乐信息的工具
 * <p>
 * 注释时间: 2019年1月22日
 * 
 * @version 1.0
 * @since JDK 1.8 文件名称：MusicTools.java
 */
public class MusicTools extends NewWebMusicTools {
	/**
	 * <p>
	 * 获取音乐信息
	 * <p>
	 * 需要传入四个参数：歌曲名称、音乐类型、页面数、WebMusicInfo类型的list接口
	 */
	public void InfoList(String music_name, String type, int page, List<WebMusicInfo> list) {
		StringTools st = new StringTools();
		music_name = st.URLEncode(music_name);
		String url_name = LinkList.NewWebMusicHost + "/?name=" + music_name + "&type=" + type, tmp = "";
		NetTools net = new NetTools();
		String headers[] = { "Accept: application/json, text/javascript, */*; q=0.01", "Accept-Encoding: gzip, deflate",
				"Accept-Language: zh-CN,zh;q=0.9", "Connection: keep-alive",
				"Content-Type: application/x-www-form-urlencoded; charset=UTF-8", "Host: music.cccyun.cc",
				"Origin: " + LinkList.NewWebMusicHost, "Referer: " + url_name, "X-Requested-With: XMLHttpRequest" };
		try {
			net.setHeaders(headers);
			tmp = net.getGzipPageOnPost(url_name,
					"input=" + music_name + "&filter=name&type=" + type + "&page=" + page);
			tmp = st.UnicodeToString(tmp);
			Matcher m = Pattern.compile("\\{(.+?\\})").matcher(tmp);
			while (m.find()) {
				WebMusicInfo webMusicInfo = new WebMusicInfo();
				webMusicInfo.setMusicName(st.getJsonByName(m.group().replaceAll("\\{\"data\":\\[", ""), "title"));
				webMusicInfo.setSingerName(st.getJsonByName(m.group().replaceAll("\\{\"data\":\\[", ""), "author"));
				webMusicInfo.setMusicHash(st.getJsonByName(m.group().replaceAll("\\{\"data\":\\[", ""), "songid"));
				webMusicInfo.setMusicImg(
						st.getJsonByName(m.group().replaceAll("\\{\"data\":\\[", ""), "pic").replaceAll("\\\\", ""));
				webMusicInfo.setDownloadLink(
						st.getJsonByName(m.group().replaceAll("\\{\"data\":\\[", ""), "url").replaceAll("\\\\", ""));
				webMusicInfo.setAuxiliary(st.getJsonByName(m.group().replaceAll("\\{\"data\":\\[", ""), "link"));
				webMusicInfo.setFileName(webMusicInfo.getSingerName() + "-" + webMusicInfo.getMusicName());
				webMusicInfo.setAuxiliary("新版不再提供此功能");
				webMusicInfo.setVideoId("新版不再提供此功能");
				list.add(webMusicInfo);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 获取音乐信息
	 * <p>
	 * 需要传入三个参数：音乐名称、音乐类型、页面数量
	 */
	public List<WebMusicInfo> getInfoList(String music_name, String type, int maxpage) {
		return getInfoList(music_name, 1, maxpage, type);
	}

	public List<WebMusicInfo> getInfoList(String music_name, int startPage, int endPage, String type) {
		List<WebMusicInfo> list = new ArrayList<>();
		if (startPage > 0) {
			if (endPage > startPage) {
				for (int i = startPage; i <= endPage; i++) {
					InfoList(music_name, type, i, list);
				}
			} else {
				InfoList(music_name, type, startPage, list);
			}
		} else {
			InfoList(music_name, type, 1, list);
		}
		return list;
	}

	/**
	 * <p>
	 * 获取音乐信息
	 * <p>
	 * 需要传入两个参数：音乐名称、音乐类型
	 */
	public List<WebMusicInfo> getInfoList(String music_name, String type) {
		return getInfoList(music_name, type, 1);
	}
}
