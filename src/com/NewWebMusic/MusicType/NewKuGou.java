package com.NewWebMusic.MusicType;

import com.NewWebMusic.MusicType.type.MusicTypeList;
import com.WebMusic.WebMusicInfo;
import com.WebMusic.KuGou.KuGou;

/**
 * <p>
 * ����ʱ�䣺2019��1��20�� ����10:45:01
 * <p>
 * ��Ŀ���ƣ�WebMusic
 * 
 * <p>
 * ��˵���� �°�ṷ����ģ��
 * 
 * <pre>
* ��Ȼ˵�����˽ӿڣ������ֹ������ɲ��þɰ��
* ��Ȼ����WebMusic�ӿ���ʵ�ֹ���
* �˰汾�����ṩset/getCookie��set/getUserAgent����
 * </pre>
 * <p>
 * ע��ʱ��: 2019��1��22��
 * 
 * @version 1.0
 * @since JDK 1.8 �ļ����ƣ�KuGou.java
 */
public class NewKuGou extends OtherMusic {
	public NewKuGou() {
		setType(MusicTypeList.TYPE_KUGOU);
	}

	@Override
	public void getMusicList(String link) {

		new KuGou().getMusicList(link);
	}

	@Override
	public String getMusicListToString(String link) {

		return new KuGou().getMusicListToString(link);
	}

	@Override
	public String[] getMusicListToStringArray(String link) {

		return new KuGou().getMusicListToStringArray(link);
	}

	@Override
	public void DownloadList(String link) {

		new KuGou().DownloadList(link);
	}

	@Override
	public void DownloadList(String link, String savePath) {

		new KuGou().DownloadList(link, savePath);
	}

	@Override
	public WebMusicInfo[] getMusicListToWebMusicInfo(String link) {

		return new KuGou().getMusicListToWebMusicInfo(link);
	}

}
