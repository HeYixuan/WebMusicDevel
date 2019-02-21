package com.WebMusic.CloudMusic163;

import com.WebMusic.LinkList;
import com.WebMusic.Tools;
import com.WebMusic.WebMusicInfo;

/**
 * <p>
 * 创建时间：2019年1月22日 下午6:36:33
 * <p>
 * 项目名称：WebMusic
 * 
 * <p>
 * 类说明：
 * <p>
 * 网易云音乐专用工具集
 * <p>
 * 注释时间: 2019年1月22日
 * 
 * @version 1.0
 * @since JDK 1.8 文件名称：CloudMusic163Tool.java
 */
public class CloudMusic163Tool extends Tools {
	/**
	 * <p>
	 * 获取音乐信息
	 * <p>
	 * 需要传入一个参数：歌曲id
	 */
	public WebMusicInfo getSongInfo(String song_id) {
		String ss = getGzipPagesource(LinkList.CloudMusicSongInfoLink + song_id);
		musicInfo = new WebMusicInfo();
		musicInfo.setMusicHash(song_id);
		musicInfo.setSingerName(getByString(ss, "<span title=(.+?<a class)", "<span title=|<a class|>|\""));
		musicInfo.setMusicName(getByString(ss, "f-ff2\">\\S+</em>", "f-ff2\">|</em>"));
		musicInfo.setMusicImg(getByString(ss, " data-src(.+?.(jpg|png|jpeg))", "data-src=|\""));
		musicInfo.setAlbumName(getByString(ss, "class=\"s-fc7\">(.+?</a>)", "class=\"s-fc7|>|</a|\""));
		musicInfo.setAuxiliary(getByString(ss, "f-ff2\"\\W+</div", "f-ff2|</div|>|\""));
		musicInfo.setVideoId(getByString(ss, "/mv(.+?>)", "/mv\\?id=|\">"));
		musicInfo.setFileName(musicInfo.getSingerName() + "-" + musicInfo.getMusicName());
		musicInfo.setDownloadLink(LinkList.CloudMusicDownloadSongLink + musicInfo.getMusicHash());
		return musicInfo;
	}
}
