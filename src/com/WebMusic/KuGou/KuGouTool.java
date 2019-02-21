package com.WebMusic.KuGou;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.WebMusic.LinkList;
import com.WebMusic.Tools;
import com.WebMusic.WebMusicInfo;

/**
 * <p>
 * ����ʱ�䣺2019��1��22�� ����6:31:11
 * <p>
 * ��Ŀ���ƣ�WebMusic
 * 
 * <p>
 * ��˵���� �ṷ����ר�ù��߼�
 * <p>
 * ע��ʱ��: 2019��1��22��
 * 
 * @version 1.0
 * @since JDK 1.8 �ļ����ƣ�KuGouTool.java
 */
public class KuGouTool extends Tools {
	/**
	 * <p>
	 * ��ȡ������ϣֵ
	 * <p>
	 * ��Ҫ������������������ҳ�棬WebMusicInfo����list�ӿ�
	 */
	public void InfoList(String data, List<WebMusicInfo> l) {
		Matcher MusicHash = Pattern.compile("0,\"FileHash" + kg_match_str).matcher(data);
		Matcher Auxiliary = Pattern.compile("Auxiliary" + kg_match_str).matcher(data);
		while (MusicHash.find() && Auxiliary.find()) {
			str = getGzipPagesource(
					LinkList.KuGouSearchSongHashLink + MusicHash.group().replaceAll("0,\"FileHash|\"|:", ""));
			str = UnicodeToString(str);
			PrivateInfoList(str, Auxiliary.group().replaceAll("Auxiliary|" + kg_re_str, ""), l);
		}
	}

	/**
	 * <p>
	 * ��ȡ������Ϣ
	 * <p>
	 * ��Ҫ������������������ҳ�桢����������WebMusicInfo����list�ӿ�
	 * <p>
	 * �����������Ǳ���
	 */
	public void PrivateInfoList(String data, String Auxiliary, List<WebMusicInfo> l) {
		musicInfo = new WebMusicInfo();
		musicInfo.setMusicHash(getByJson(data, "hash"));
		musicInfo.setMusicName(getByJson(data, "song_name"));
		musicInfo.setAlbumName(getByJson(data, "album_name"));
		musicInfo.setSingerName(getByJson(data, "author_name"));
		musicInfo.setDownloadLink(getByJson(data, "play_url").replaceAll("\\\\", ""));
		musicInfo.setVideoId(getByJson(data, "video_id"));
		musicInfo.setMusicImg(getByJson(data, "img").replaceAll("\\\\", ""));
		if (Auxiliary.equals("")) {
			musicInfo.setAlbumName("û���ҵ���������");
		} else {
			musicInfo.setAlbumName(Auxiliary);
		}
		musicInfo.setFileName(musicInfo.getSingerName() + "-" + musicInfo.getMusicName());
		l.add(musicInfo);
	}
}
