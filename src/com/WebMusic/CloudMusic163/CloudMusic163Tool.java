package com.WebMusic.CloudMusic163;

import com.WebMusic.LinkList;
import com.WebMusic.Tools;
import com.WebMusic.WebMusicInfo;

/**
 * <p>
 * ����ʱ�䣺2019��1��22�� ����6:36:33
 * <p>
 * ��Ŀ���ƣ�WebMusic
 * 
 * <p>
 * ��˵����
 * <p>
 * ����������ר�ù��߼�
 * <p>
 * ע��ʱ��: 2019��1��22��
 * 
 * @version 1.0
 * @since JDK 1.8 �ļ����ƣ�CloudMusic163Tool.java
 */
public class CloudMusic163Tool extends Tools {
	/**
	 * <p>
	 * ��ȡ������Ϣ
	 * <p>
	 * ��Ҫ����һ������������id
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
