package com.NewWebMusic.MusicType;

import com.NewWebMusic.MusicType.type.MusicTypeList;
import com.WebMusic.WebMusicInfo;
import com.WebMusic.KuGou.KuGou;

/**
 * <p>
 * 创建时间：2019年1月20日 上午10:45:01
 * <p>
 * 项目名称：WebMusic
 * 
 * <p>
 * 类说明： 新版酷狗音乐模块
 * 
 * <pre>
* 虽然说更换了接口，但部分功能依旧采用旧版的
* 依然采用WebMusic接口来实现功能
* 此版本不再提供set/getCookie，set/getUserAgent功能
 * </pre>
 * <p>
 * 注释时间: 2019年1月22日
 * 
 * @version 1.0
 * @since JDK 1.8 文件名称：KuGou.java
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
