package util;

import java.util.ArrayList;
import java.util.List;

import entity.Diary;

/** 
 * @ClassName: Mypagination 
 * @Description: TODO 分页javaBean
 * @author zhb
 * @date 2018-3-21 下午9:59:45 
 * @type 
 */

public class Mypagination {
	public List<Diary> list = null;
	private int  recordCount=0; //保存记录总数的变量
	private int pagessize = 0;  //保存每页显示的记录数的变量
	private int maxPage = 0;   //保存最大页数的变量
	//查询页数和每页记录数的数据
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
	//查询指定页数的数据
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
	//获取最大记录数
	public int getMaxPage(){
		maxPage = (recordCount % pagessize == 0 )?(recordCount/pagessize):(recordCount/pagessize+1);
		return maxPage;
	}
	//获取总记录数
	public int getRecordSize(){
		return recordCount;
	}
	//获取当前页数
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
	//输出记录导航
	public String printCtrl(int Page,String url,String para){
		String strHtml = "<table width='100%' border='0' cellspacing='0' cellpadding='0'><tr><td height='24' align='right'>当前页数:【"+Page+"/"+maxPage+"】&nbsp;";
		try {
			if(Page >1){
				strHtml = strHtml+"<a href='"+url+"&Page=1"+para+"'>第一页</a>";
				strHtml = strHtml+"<a href='"+url+"&Page="+(Page-1)+para+"'>上一页</a>";
			}
			if(Page<maxPage){
				strHtml = strHtml+"<a href='"+url+"&Page="+(Page+1)+"'>下一页</a><a href='"+url+"&Page="+maxPage+para+"'>最后一页&nbsp;</a>";
			}
			strHtml = strHtml+"</td></tr> </table>";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strHtml;
	}
}


