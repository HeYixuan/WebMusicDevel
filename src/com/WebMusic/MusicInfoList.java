package com.WebMusic;

import java.util.List;

/**
 * <p>
 * ����ʱ�䣺2019��1��22�� ����6:21:21
 * <p>
 * ��Ŀ���ƣ�WebMusic
 * 
 * <p>
 * ��˵����
 * <p>
 * ����WebMusicInfo���͵ļ���
 * <p>
 * ע��ʱ��: 2019��1��22��
 * 
 * @version 1.0
 * @since JDK 1.8 �ļ����ƣ�MusicInfoList.java
 */
public interface MusicInfoList {
	/**
	 * <p>
	 * ��ȡ������Ϣ������һ��WebMusicInfo���͵�list�ӿ�
	 * <p>
	 * ��Ҫ���������������������ƣ�ҳ������
	 * <p>
	 * ҳ���������Ǳ���ģ�����������һ��Ҫ��
	 */
	public List<WebMusicInfo> getInfoList(String search_name, int maxpage);

	/**
	 * <p>
	 * ��ȡ������Ϣ������һ��WebMusicInfo���͵�list�ӿ�
	 * <p>
	 * ��Ҫ����һ����������������
	 */
	public List<WebMusicInfo> getInfoList(String search_name);

	/**
	 * <p>
	 * ��ȡ�赥��Ϣ������һ��WebMusicInfo���͵�list�ӿ�
	 * <p>
	 * ��Ҫ����һ���������赥����
	 */
	public List<WebMusicInfo> getMusicInfoList(String link);
	/**
	 * <p>
	 * ��ȡ������Ϣ������һ��WebMusicInfo���͵�list�ӿ�
	 * <p>
	 * ��Ҫ���������������������ƣ�ҳ�濪ʼλ�ã�ҳ�����λ��
	 */
	public List<WebMusicInfo> getInfoList(String search_name,int startPage , int endPage);
	
}
