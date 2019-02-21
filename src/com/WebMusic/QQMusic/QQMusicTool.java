package com.WebMusic.QQMusic;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import com.WebMusic.LinkList;
import com.WebMusic.Tools;
import com.WebMusic.WebMusicInfo;

/**
 * <p>
 * ����ʱ�䣺2019��1��22�� ����5:31:39
 * <p>
 * ��Ŀ���ƣ�WebMusic
 * 
 * <p>
 * ��˵����
 * <p>
 * QQ���ֹ��߼�
 * <p>
 * ע��ʱ��: 2019��1��22��
 * 
 * @version 1.0
 * @since JDK 1.8 �ļ����ƣ�QQMusicTool.java
 */
public class QQMusicTool extends Tools {
	/**
	 * <p>
	 * ��ȡ������Ϣ
	 * <p>
	 * ��Ҫ������������������id����Ƶid����������
	 * <p>
	 * ���������Ǳ���ģ�������id��һ��Ҫ��
	 */
	public WebMusicInfo getSongInfo(String song_id, String mvid, String aux) {
		musicInfo = new WebMusicInfo();
		String ss = getGzipPagesource(LinkList.QQMusicSongInfoLink + song_id + ".html");
		String value_str = "";
		Matcher JsonArray = Pattern.compile("g_SongData =(.+?\\};)").matcher(ss);
		Matcher Img = Pattern.compile("background-image:url(.+?\\))").matcher(ss);
		if (JsonArray.find() && Img.find()) {
			value_str = JsonArray.group().replaceAll("g_SongData = ", "");
			musicInfo.setMusicHash(getByJson(value_str, "songmid"));
			musicInfo.setMusicName(getByJson(value_str, "songname"));
			musicInfo.setSingerName(getByJson(value_str, "name"));
			musicInfo.setAlbumName(getByJson(value_str, "albumname"));
			musicInfo.setDownloadLink(getDownLink(musicInfo.getMusicHash()));
			musicInfo.setMusicImg(Img.group().replaceAll("background-image:url|\\(|\\)", ""));
			musicInfo.setVideoId(mvid);
			musicInfo.setAuxiliary(aux);
			musicInfo.setFileName(musicInfo.getSingerName() + "-" + musicInfo.getMusicName());
		}
		return musicInfo;
	}

	/**
	 * <p>
	 * ��ȡ����id����Ƶid(mv_id)����������
	 * <p>
	 * ��Ҫ������������,������ϸ��Ϣҳ�桢WebMusicInfo���͵�list�ӿ�
	 */
	public void PriInfo(String data, List<WebMusicInfo> l) {
		Matcher id = Pattern.compile("songmid\":\"(.+?\")").matcher(data);
		Matcher mid = Pattern.compile("vid\":\"(.+?\")").matcher(data);
		Matcher aux = Pattern.compile("lyric\":\"(.+?\")").matcher(data);
		while (id.find() && mid.find() && aux.find()) {
			l.add(getSongInfo(id.group().replaceAll("songmid|:|\"", ""),
					mid.group().replaceAll("vid|:|\"|(\\}|\\{|\\]|\\[)|,", ""),
					aux.group().replaceAll("lyric|:|\"|,", "")));
		}
	}

	/**
	 * <p>
	 * ��ȡ��������������
	 * <p>
	 * ��Ҫ����һ������������ID
	 */
	public String getDownLink(String song_id) {
		String downloadLink = getGzipPagesource(
				LinkList.QQMusicSongDownloadPage + song_id + LinkList.QQMusicSongDownloadPageEnd);
		downloadLink = LinkList.QQMusicSongDownloadHead + getByJson(downloadLink, "purl");
		return downloadLink;
	}

	/**
	 * <p>
	 * ��ȡ�赥ҳ������
	 * <p>
	 * ��Ҫ����һ���������赥����
	 */
	public String getMusicListPage(String link) {
		String page = "";
		HttpURLConnection c = null;
		try {
			c = (HttpURLConnection) new URL(LinkList.QQMusicListLink).openConnection();
			c.setRequestMethod("POST");
			c.setRequestProperty("accept", "application/json, text/javascript, */*; q=0.01");
			c.setRequestProperty("accept-encoding", "gzip, deflate, br");
			c.setRequestProperty("origin", "https://y.qq.com");
			c.setRequestProperty("referer", link);
			c.setRequestProperty("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
			c.setDoOutput(true);
			c.setDoInput(true);
			OutputStreamWriter w = new OutputStreamWriter(c.getOutputStream());
			w.write("type=1&json=1&utf8=1&disstid="
					+ link.substring(link.lastIndexOf("/") + 1).replaceAll(".ht(m|ml)", ""));
			w.flush();
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new GZIPInputStream(c.getInputStream()), "utf-8"));
			while ((line = br.readLine()) != null) {
				page += line + "\n";
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	/**
	 * <p>
	 * �������nλ����������
	 * <p>
	 * ��Ҫ����һ�����������ȵ�λ,����
	 */
	public String ran(int n) {
		String s = "";
		Random r = new Random();
		for (int i = 0; i < n; i++) {
			s += r.nextInt(10);
		}
		return s;
	}

}