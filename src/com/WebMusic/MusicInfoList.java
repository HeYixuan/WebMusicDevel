package com.WebMusic;

import java.util.List;

/**
 * <p>
 * 创建时间：2019年1月22日 下午6:21:21
 * <p>
 * 项目名称：WebMusic
 * 
 * <p>
 * 类说明：
 * <p>
 * 返回WebMusicInfo类型的集合
 * <p>
 * 注释时间: 2019年1月22日
 * 
 * @version 1.0
 * @since JDK 1.8 文件名称：MusicInfoList.java
 */
public interface MusicInfoList {
	/**
	 * <p>
	 * 获取歌曲信息，返回一个WebMusicInfo类型的list接口
	 * <p>
	 * 需要传入两个参数：歌曲名称，页面数量
	 * <p>
	 * 页面数量不是必须的，但歌曲名称一定要有
	 */
	public List<WebMusicInfo> getInfoList(String search_name, int maxpage);

	/**
	 * <p>
	 * 获取歌曲信息，返回一个WebMusicInfo类型的list接口
	 * <p>
	 * 需要传入一个参数：歌曲名称
	 */
	public List<WebMusicInfo> getInfoList(String search_name);

	/**
	 * <p>
	 * 获取歌单信息，返回一个WebMusicInfo类型的list接口
	 * <p>
	 * 需要传入一个参数：歌单链接
	 */
	public List<WebMusicInfo> getMusicInfoList(String link);
	/**
	 * <p>
	 * 获取歌曲信息，返回一个WebMusicInfo类型的list接口
	 * <p>
	 * 需要传入三个参数：歌曲名称，页面开始位置，页面结束位置
	 */
	public List<WebMusicInfo> getInfoList(String search_name,int startPage , int endPage);
	
}
