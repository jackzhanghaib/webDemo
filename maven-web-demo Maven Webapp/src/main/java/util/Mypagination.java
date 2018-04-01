package util;

import java.util.ArrayList;
import java.util.List;

import entity.Diary;

/** 
 * @ClassName: Mypagination 
 * @Description: TODO ��ҳjavaBean
 * @author zhb
 * @date 2018-3-21 ����9:59:45 
 * @type 
 */

public class Mypagination {
	public List<Diary> list = null;
	private int  recordCount=0; //�����¼�����ı���
	private int pagessize = 0;  //����ÿҳ��ʾ�ļ�¼���ı���
	private int maxPage = 0;   //�������ҳ���ı���
	//��ѯҳ����ÿҳ��¼��������
	public List<Diary> getInitPage(List<Diary> list,int Page,int pagesize){
		List<Diary> newList = new ArrayList<Diary>();
		this.list = list;
		recordCount = list.size();
		this.pagessize = pagesize;
		this.maxPage = getMaxPage();
		try {
			for(int i=(Page -1)*pagesize;i<=Page*pagesize-1;i++){
				
				try {
					if(i >= recordCount ){
						break;
						}
				}catch (Exception e) {
					e.printStackTrace();
					}
				newList.add((Diary)list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newList;
	}
	//��ѯָ��ҳ��������
	public List<Diary> getAppointPage(int Page){
		List<Diary> newList = new ArrayList<Diary>();
		try {
			for(int i = (Page - 1)*pagessize;i<=(Page*pagessize)-1;i++){
				try {
					if(i>=recordCount){break;}
				} catch (Exception e) {
					
				}
				newList.add((Diary)list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newList;
	}
	//��ȡ����¼��
	public int getMaxPage(){
		maxPage = (recordCount % pagessize == 0 )?(recordCount/pagessize):(recordCount/pagessize+1);
		return maxPage;
	}
	//��ȡ�ܼ�¼��
	public int getRecordSize(){
		return recordCount;
	}
	//��ȡ��ǰҳ��
	public int getPage(String str){
		if(str == null){
			str = "0";
		}
		int Page = Integer.parseInt(str);
		if(Page < 1){
			Page = 1;
		}else{
			if((Page -1)*pagessize > recordCount){
				Page = maxPage;
			}
		}
		return Page;
	}
	//�����¼����
	public String printCtrl(int Page,String url,String para){
		String strHtml = "<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td height='24' align='right'>��ǰҳ��:��"+Page+"/"+maxPage+"��&nbsp;";
		try {
			if(Page >1){
				strHtml = strHtml+"<a href='"+url+"&Page=1"+para+"'>��һҳ</a>";
				strHtml = strHtml+"<a href='"+url+"&Page="+(Page-1)+para+"'>��һҳ</a>";
			}
			if(Page<maxPage){
				strHtml = strHtml+"<a href='"+url+"&Page="+(Page+1)+"'>��һҳ</a><a href='"+url+"&Page="+maxPage+para+"'>���һҳ&nbsp;</a>";
			}
			strHtml = strHtml+"</td></tr> </table>";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strHtml;
	}
}


