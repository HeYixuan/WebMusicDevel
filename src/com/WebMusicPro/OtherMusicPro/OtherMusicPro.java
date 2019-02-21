package com.WebMusicPro.OtherMusicPro;

import com.WebMusicPro.MusicTypePro.MusicTypePro;
import com.WebMusicPro.util.MusicFormat;
import com.WebMusicPro.util.Tools;
import com.WebMusicPro.util.WebMusicPro;
import com.WebMusicPro.util.WebMusicProInfo;

/**
* <p>����ʱ�䣺2019��1��30�� ����7:59:11
* <p>��Ŀ���ƣ�WebMusic
* 
* <p>��˵����
*  �������ָ߼���
* <p>��֪����:
* <p>��ȡ������Ϣ��Ҫ���ıȽ϶��ʱ��!
* <p>�������ĵȺ�
*  <pre>
* �����ԭ���汾����
 * ��ǰ�汾�������Ĺ���
 * ���߳����ظ������赥
 * ֧��ҳ��ѡ�񣬲��ٹ涨��ҳ��
 * ֧�ָ�������ѡ��
 * ֧�ָ赥����ѡ��
 * 
 * 
*  ��ǰ�汾Ҳ�޳���һЩ����
*  �����乾���֡�ϲ���������֡�ȫ��ȵȵ�֧��
*  �޳��ٶ����ָ赥
*  
*  </pre>
*  <pre>
*  
*  ����ʾ��:
*  
*  OtherMusicPro o = new OtherMusicPro();
*  o.getInfo("����");
*  
*  Ĭ��Ϊ������������
*  ��������������׸��������Ϣ
*  
*  �������Ҫ�������͵Ļ�
*  ����ͨ��setType��������������
*  
*  ʾ��:
*  
*  OtherMusicPro o = new OtherMusicPro();
*  o.setType(MusicTypePro.MusicProCLOUD163); //������������Ϊ������
*  o.getInfo("����");
*  
*  �������ʹ�ӡ���������������ȡ����������Ϣ
*  
*  ��Ȼ����Ҳ����ͨ��newһ���̳���������
*  ʾ��:
*  
*  	OtherMusicPro o = new KuGouMusicPro();
*  	o.getInfo("����");
*  
*  ������������ṷ�������͵���Ϣ
*  
*  ��������������������:
*  
*  OtherMusicPro o = new KuGouMusicPro();
*  o.setType(MusicTypePro.MusicProBAIDU);
*  o.getInfo("����");
*  
*  ��ֱ������ٶ��������͵���Ϣ!
*  
*  ���ǲ����Ƽ�����������Ϊ���������ñ��˸�죬�㲻����ʲô���
*  
*  ����֧��QQ���֣���ΪQQ�����Ƕ�����!O(��_��)O
*  </pre>
*  
*  <p>ע��ʱ��: 2019��2��1��
* @version 1.0
* @since JDK 1.8
* �ļ����ƣ�OtherMusicPro.java
* */
public class OtherMusicPro implements WebMusicPro{
	/**<p>Ĭ�ϻ�ȡ��������Ϊ20��*/
	private final static int music_num = 20;
	private int MusicNum = 1;
	/**<p>����������������*/
	private String type = "";
	/**<p>�������ñ���λ��*/
	private String savePath = "";
	/**<p>��������cookie*/
	private String cookie = "";
	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public int getMusicNum() {
		return MusicNum;
	}

	public void setMusicNum(int musicNum) {
		MusicNum = musicNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	/**<p>����cookie*/
	public OtherMusicProUtils checkCookie(String cookie)
	{
		OtherMusicProUtils o = new OtherMusicProUtils();
		if(cookie.equals(""))
		{
			o.setCookie("Tip_of_the_day=2; encrypt_data=657b19b1fbb02399ab10e088a7278468c28cee88caf7022cf624bbbe168274a86d50b827a5af7eb1b0dd938b25696754e64983848873477fa01b188c4285fc891fe37767d923b3ec4b4dcef6466e3dfd5e38e1784ba7e154b6e607c2e5378399d79e9135015bc5ccec111b5ebba8d7180222a9bfa76110ffcacb251e8e44a18c");
		}else
		{
			o.setCookie(cookie);
		}
		return o;
	}
	/**<p>���ø����ı���λ��*/
	public String checkSave(String save)
	{
		String s = "";
		if(save.equals(""))
		{
			s = "e:\\WebMusic\\OtherMusic\\";
		}else
		{
			s = save;
		}
		return s;
	}
	/**<p>���û�ȡ���ֵ�����*/
	public int checkNum(int num)
	{
		int i = 1;
		if(num > 10)
		{
			i = num;
		}else
		{
			i = music_num;
		}
		return i;
	}
	/**<p>������������*/
	public String checkType(String m_type)
	{
		String tt = "";
		if(m_type.equals(MusicTypePro.MusicProKUWO))
		{
			tt = MusicTypePro.MusicProKUWO;
		}else if(m_type.equals(MusicTypePro.MusicProCLOUD163))
		{
			tt = MusicTypePro.MusicProCLOUD163;
		}else if(m_type.equals(MusicTypePro.MusicProXIAMI))
		{
			tt = MusicTypePro.MusicProXIAMI;
		}else if(m_type.equals(MusicTypePro.MusicProKUGOU))
		{
			tt = MusicTypePro.MusicProKUGOU;
		}else if(m_type.equals(MusicTypePro.MusicProBAIDU))
		{
			tt = MusicTypePro.MusicProBAIDU;
		}else
		{
			tt = MusicTypePro.MusicProKUWO;
		}
		return tt;
	}
	
	@Override
	public void getInfo(String search_name) {
		new Tools().printList(checkCookie(getCookie()).getInfoList(search_name, checkNum(getMusicNum()), checkType(getType())));
	}

	@Override
	public void getMusicList(String link) {
		new Tools().printList(checkCookie(getCookie()).getMusicListInfo(link, checkType(getType())));
	}

	@Override
	public void getInfo(String search_name, int maxpage) {
		new Tools().printList(checkCookie(getCookie()).getInfoList(search_name , maxpage, checkNum(getMusicNum()), checkType(getType())));
	}

	@Override
	public String getInfoToString(String search_name) {
		return new Tools().printListString(checkCookie(getCookie()).getInfoList(search_name, checkNum(getMusicNum()), checkType(getType())));
	}

	@Override
	public String getInfoToString(String search_name, int maxpage) {
		return new Tools().printListString(checkCookie(getCookie()).getInfoList(search_name , maxpage, checkNum(getMusicNum()), checkType(getType())));
	}

	@Override
	public String getMusicListToString(String link) {
		return new Tools().printListString(checkCookie(getCookie()).getMusicListInfo(link, checkType(getType())));
	}

	@Override
	public String[] getInfoToStringArray(String search_name) {
		return new Tools().printListStringArray(checkCookie(getCookie()).getInfoList(search_name , checkNum(getMusicNum()), checkType(getType())));
	}

	@Override
	public String[] getInfoToStringArray(String search_name, int maxpage) {
		return new Tools().printListStringArray(checkCookie(getCookie()).getInfoList(search_name ,maxpage, checkNum(getMusicNum()), checkType(getType())));
	}

	@Override
	public String[] getMusicListToStringArray(String link) {
		return new Tools().printListStringArray(checkCookie(getCookie()).getMusicListInfo(link, checkType(getType())));
	}
	
	@Override
	public WebMusicProInfo[] getInfoToWebMusicProInfo(String search_name) {
		return new Tools().printListInfo(checkCookie(getCookie()).getInfoList(search_name , checkNum(getMusicNum()), checkType(getType())));
	}

	@Override
	public WebMusicProInfo[] getInfoToWebMusicProInfo(String search_name, int maxpage) {
		return new Tools().printListInfo(checkCookie(getCookie()).getInfoList(search_name ,maxpage, checkNum(getMusicNum()), checkType(getType())));
	}

	@Override
	public WebMusicProInfo[] getMusicListToWebMusicProInfo(String link) {
		return new Tools().printListInfo(checkCookie(getCookie()).getMusicListInfo(link, checkType(getType())));
	}
	
	@Override
	public void DownloadAll(String search_name) {
		DownloadAll(search_name, checkSave(getSavePath()), -1);
	}

	@Override
	public void DownloadAll(String search_name, String savePath) {
		DownloadAll(search_name, savePath, -1);
	}

	@Override
	public void DownloadAll(String search_name, String savePath, int maxpage) {
		DownloadAll(search_name, savePath, 1, maxpage);
	}

	@Override
	public void DownloadList(String link) {
		DownloadList(link, checkSave(getSavePath()));
	}

	@Override
	public void DownloadList(String link, String savePath) {
		int c = 0;
		for (WebMusicProInfo i : getMusicListToWebMusicProInfo(link)) {
			new Tools().MultiThreadDownlaodMusic(i.getDownloadLink() , i.getFileName(), savePath, MusicFormat.m4aEnd);
			c++;
		}
		System.err.println("��������: " + c + " ������");
	}

	@Override
	public void DownloadFlac(String search_name) {
		DownloadFlac(search_name, checkSave(getSavePath()));
	}

	@Override
	public void DownloadFlac(String search_name, int maxpage) {
		DownloadFlac(search_name, checkSave(getSavePath()), maxpage);
	}

	@Override
	public void DownloadApe(String search_name) {
		DownloadApe(search_name, checkSave(getSavePath()));
	}

	@Override
	public void DownloadApe(String search_name, int maxpage) {
		DownloadApe(search_name, checkSave(getSavePath()), maxpage);
	}

	@Override
	public void Download320k(String search_name) {
		Download320k(search_name, checkSave(getSavePath()));
	}

	@Override
	public void Download320k(String search_name, int maxpage) {
		Download320k(search_name, checkSave(getSavePath()), maxpage);

	}

	@Override
	public void Download128k(String search_name) {
		Download128k(search_name, checkSave(getSavePath()));
	}

	@Override
	public void Download128k(String search_name, int maxpage) {
		Download128k(search_name, checkSave(getSavePath()), maxpage);
	}

	@Override
	public void DownloadM4a(String search_name) {
		DownloadAll(search_name);
	}

	@Override
	public void DownloadM4a(String search_name, int maxpage) {
		DownloadAll(search_name, checkSave(getSavePath()), maxpage);
	}

	@Override
	public void DownloadFlacList(String link) {
		DownloadFlacList(link, checkSave(getSavePath()));
	}

	@Override
	public void DownloadApeList(String link) {
		DownloadApeList(link, checkSave(getSavePath()));
	}

	@Override
	public void Download320kList(String link) {
		Download320kList(link, checkSave(getSavePath()));
	}

	@Override
	public void Download128kList(String link) {
		Download128kList(link, checkSave(getSavePath()));
	}

	@Override
	public void DownloadM4aList(String link) {
		DownloadList(link, checkSave(getSavePath()));
	}

	@Override
	public void DownloadMusic(String musicPage, String format) {
		DownloadMusic(musicPage, checkSave(getSavePath()), format);
	}

	@Override
	public void DownloadMusicList(String musicPage, String format) {
		DownloadMusicList(musicPage, checkSave(getSavePath()), format);
	}

	@Override
	public void DownloadFlac(String search_name, String savePath) {
		DownloadFlac(search_name, savePath, -1);
	}

	@Override
	public void DownloadFlac(String search_name, String savePath, int maxpage) {
		DownloadFlac(search_name, savePath, 1, maxpage);
	}

	@Override
	public void DownloadApe(String search_name, String savePath) {

		DownloadApe(search_name, savePath, -1);
	}

	@Override
	public void DownloadApe(String search_name, String savePath, int maxpage) {
		DownloadApe(search_name, savePath, 1, maxpage);
	}

	@Override
	public void Download320k(String search_name, String savePath) {
		Download320k(search_name, savePath, -1);
	}

	@Override
	public void Download320k(String search_name, String savePath, int maxpage) {
		Download320k(search_name, savePath, 1, maxpage);
	}

	@Override
	public void Download128k(String search_name, String savePath) {
		Download128k(search_name, savePath, -1);
	}

	@Override
	public void Download128k(String search_name, String savePath, int maxpage) {
		Download128k(search_name, savePath, 1, maxpage);
	}

	@Override
	public void DownloadM4a(String search_name, String savePath) {
		DownloadAll(search_name, savePath, -1);
	}

	@Override
	public void DownloadM4a(String search_name, String savePath, int maxpage) {
		DownloadAll(search_name, savePath, maxpage);
	}

	@Override
	public void DownloadFlacList(String link, String savePath) {
		int c = 0;
		for (WebMusicProInfo i : getMusicListToWebMusicProInfo(link)) {
			new Tools().MultiThreadDownlaodMusic(i.getFLAC(), i.getFileName(), savePath, MusicFormat.FlacEnd);
			c++;
		}
		System.err.println("��������: " + c + " ������");
	}

	@Override
	public void DownloadApeList(String link, String savePath) {
		int c = 0;
		for (WebMusicProInfo i : getMusicListToWebMusicProInfo(link)) {
			new Tools().MultiThreadDownlaodMusic(i.getAPE(), i.getFileName(), savePath, MusicFormat.ApeEnd);
			c++;
		}
		System.err.println("��������: " + c + " ������");
	}

	@Override
	public void Download320kList(String link, String savePath) {

		int c = 0;
		for (WebMusicProInfo i : getMusicListToWebMusicProInfo(link)) {
			new Tools().MultiThreadDownlaodMusic(i.getM320k(), i.getFileName(), savePath, MusicFormat.QQ320kEnd);
			c++;
		}
		System.err.println("��������: " + c + " ������");
	}

	@Override
	public void Download128kList(String link, String savePath) {
		int c = 0;
		for (WebMusicProInfo i : getMusicListToWebMusicProInfo(link)) {
			new Tools().MultiThreadDownlaodMusic(i.getM128k(), i.getFileName(), savePath, MusicFormat.QQ128kEnd);
			c++;
		}
		System.err.println("��������: " + c + " ������");
	}

	@Override
	public void DownloadM4aList(String link, String savePath) {
		DownloadList(link, savePath);
	}

	@Override
	public void DownloadMusic(String musicPage, String savePath, String format) {
		String id = musicPage;
		if (id.length() > 13) {
			id = id.substring(id.lastIndexOf("/") + 1).replaceAll(".html", "");
		}
		Tools t = new Tools();
		String s[] = t.checkOhterFormat(format, id, checkType(getType())).split("--");
		if (s[1].length() > 10) {
			t.Downlaod(s[1], savePath, s[2], s[0]);
		} else {
			System.err.println("skip");
		}
	}

	@Override
	public void DownloadMusicList(String musicPage, String savePath, String format) {
		int c = 0;
		for (WebMusicProInfo w : getMusicListToWebMusicProInfo(musicPage)) {
			new Thread() {
				@Override
				public void run() {
					DownloadMusic(w.getMusicHash(), savePath, format);
				}
			}.start();
			new Tools().sleep(3500);
			c++;
		}
		System.err.println("��������: " + c + " ������");
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	@Override
	public void getInfo(String search_name, int startPage, int endPage) {
		new Tools().printList(new OtherMusicProUtils().getInfoList(search_name, startPage, endPage, checkNum(getMusicNum()), checkType(getType())));
	}

	@Override
	public String getInfoToString(String search_name, int startPage, int endPage) {
		return new Tools().printListString(new OtherMusicProUtils().getInfoList(search_name, startPage, endPage, checkNum(getMusicNum()), checkType(getType())));
	}

	@Override
	public String[] getInfoToStringArray(String search_name, int startPage, int endPage) {
		return new Tools().printListStringArray(new OtherMusicProUtils().getInfoList(search_name, startPage, endPage, checkNum(getMusicNum()), checkType(getType())));
	}

	@Override
	public WebMusicProInfo[] getInfoToWebMusicProInfo(String search_name, int startPage, int endPage) {
		return new Tools().printListInfo(new OtherMusicProUtils().getInfoList(search_name, startPage, endPage, checkNum(getMusicNum()), checkType(getType())));
	}

	@Override
	public void DownloadAll(String search_name, int startPage, int endPage) {
		DownloadAll(search_name,  checkSave(getSavePath()), startPage, endPage);
	}
	
	@Override
	public void DownloadAll(String search_name, String savePath, int startPage, int endPage) {
		int c = 0;
		if(startPage >0)
		{
			if(endPage >startPage)
			{
				for(WebMusicProInfo w :getInfoToWebMusicProInfo(search_name, startPage, endPage))
				{
					new Tools().MultiThreadDownlaodMusic(w.getDownloadLink(), savePath, w.getFileName(), MusicFormat.m4aEnd);
					c++;
				}
			}else
			{
				for(WebMusicProInfo w :getInfoToWebMusicProInfo(search_name, startPage, 1))
				{
					new Tools().MultiThreadDownlaodMusic(w.getDownloadLink(), savePath, w.getFileName(), MusicFormat.m4aEnd);
					c++;
				}
			}
		}else
		{
			for(WebMusicProInfo w :getInfoToWebMusicProInfo(search_name, 1, 1))
			{
				new Tools().MultiThreadDownlaodMusic(w.getDownloadLink(), savePath, w.getFileName(), MusicFormat.m4aEnd);
				c++;
			}
		}
		System.err.println("��������: "+c + " ������");
	}
	
	@Override
	public void DownloadFlac(String search_name, int startPage, int endPage) {
		DownloadFlac(search_name,  checkSave(getSavePath()), startPage, endPage);
	}

	@Override
	public void DownloadApe(String search_name, int startPage, int endPage) {
		DownloadApe(search_name,  checkSave(getSavePath()), startPage, endPage);
	}

	@Override
	public void Download320k(String search_name, int startPage, int endPage) {
		Download320k(search_name,  checkSave(getSavePath()), startPage, endPage);
	}

	@Override
	public void Download128k(String search_name, int startPage, int endPage) {
		Download128k(search_name,  checkSave(getSavePath()), startPage, endPage);
	}

	@Override
	public void DownloadM4a(String search_name, int startPage, int endPage) {
		DownloadAll(search_name, startPage, endPage);
		
	}

	@Override
	public void DownloadFlac(String search_name, String savePath, int startPage, int endPage) {
		int c = 0;
		if(startPage >0)
		{
			if(endPage >startPage)
			{
				for(WebMusicProInfo w :getInfoToWebMusicProInfo(search_name, startPage, endPage))
				{
					new Tools().MultiThreadDownlaodMusic(w.getFLAC(), savePath, w.getFileName(), MusicFormat.FlacEnd);
					c++;
				}
			}else
			{
				for(WebMusicProInfo w :getInfoToWebMusicProInfo(search_name, startPage, 1))
				{
					new Tools().MultiThreadDownlaodMusic(w.getFLAC(), savePath, w.getFileName(),  MusicFormat.FlacEnd);
					c++;
				}
			}
		}else
		{
			for(WebMusicProInfo w :getInfoToWebMusicProInfo(search_name, 1, 1))
			{
				new Tools().MultiThreadDownlaodMusic(w.getFLAC(), savePath, w.getFileName(),MusicFormat.FlacEnd);
				c++;
			}
		}
		System.err.println("��������: "+c + " ������");
	}

	@Override
	public void DownloadApe(String search_name, String savePath, int startPage, int endPage) {
		int c = 0;
		if(startPage >0)
		{
			if(endPage >startPage)
			{
				for(WebMusicProInfo w :getInfoToWebMusicProInfo(search_name, startPage, endPage))
				{
					new Tools().MultiThreadDownlaodMusic(w.getAPE(), savePath, w.getFileName(), MusicFormat.ApeEnd);
					c++;
				}
			}else
			{
				for(WebMusicProInfo w :getInfoToWebMusicProInfo(search_name, startPage, 1))
				{
					new Tools().MultiThreadDownlaodMusic(w.getAPE(), savePath, w.getFileName(),  MusicFormat.ApeEnd);
					c++;
				}
			}
		}else
		{
			for(WebMusicProInfo w :getInfoToWebMusicProInfo(search_name, 1, 1))
			{
				new Tools().MultiThreadDownlaodMusic(w.getAPE(), savePath, w.getFileName(),MusicFormat.ApeEnd);
				c++;
			}
		}
		System.err.println("��������: "+c + " ������");
	}

	@Override
	public void Download320k(String search_name, String savePath, int startPage, int endPage) {
		int c = 0;
		if(startPage >0)
		{
			if(endPage >startPage)
			{
				for(WebMusicProInfo w :getInfoToWebMusicProInfo(search_name, startPage, endPage))
				{
					new Tools().MultiThreadDownlaodMusic(w.getM320k(), savePath, w.getFileName(), MusicFormat.QQ320kEnd);
					c++;
				}
			}else
			{
				for(WebMusicProInfo w :getInfoToWebMusicProInfo(search_name, startPage, 1))
				{
					new Tools().MultiThreadDownlaodMusic(w.getM320k(), savePath, w.getFileName(),  MusicFormat.QQ320kEnd);
					c++;
				}
			}
		}else
		{
			for(WebMusicProInfo w :getInfoToWebMusicProInfo(search_name, 1, 1))
			{
				new Tools().MultiThreadDownlaodMusic(w.getM320k(), savePath, w.getFileName(),MusicFormat.QQ320kEnd);
				c++;
			}
		}
		System.err.println("��������: "+c + " ������");
	}

	@Override
	public void Download128k(String search_name, String savePath, int startPage, int endPage) {
		int c = 0;
		if(startPage >0)
		{
			if(endPage >startPage)
			{
				for(WebMusicProInfo w :getInfoToWebMusicProInfo(search_name, startPage, endPage))
				{
					new Tools().MultiThreadDownlaodMusic(w.getM128k(), savePath, w.getFileName(), MusicFormat.QQ128kEnd);
					c++;
				}
			}else
			{
				for(WebMusicProInfo w :getInfoToWebMusicProInfo(search_name, startPage, 1))
				{
					new Tools().MultiThreadDownlaodMusic(w.getM128k(), savePath, w.getFileName(),  MusicFormat.QQ128kEnd);
					c++;
				}
			}
		}else
		{
			for(WebMusicProInfo w :getInfoToWebMusicProInfo(search_name, 1, 1))
			{
				new Tools().MultiThreadDownlaodMusic(w.getM128k(), savePath, w.getFileName(),MusicFormat.QQ128kEnd);
				c++;
			}
		}
		System.err.println("��������: "+c + " ������");
	}

	@Override
	public void DownloadM4a(String search_name, String savePath, int startPage, int endPage) {
		DownloadAll(search_name, savePath, startPage, endPage);
	}
	
}
