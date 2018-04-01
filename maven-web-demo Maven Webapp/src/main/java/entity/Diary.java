package entity;

import java.sql.Date;

/** 
 * @ClassName: Diary 
 * @Description: TODO 实体类对象
 * @author zhb
 * @date 2018-3-23 下午10:34:22 
 * @type 
 */

public class Diary {
	private int id = 0;     //日记ID号
	private String title = "";//日记标题
	private String address = "";//日记图片地址
	private Date writeTime = null; //写日记时间
	private int userid = 0; //用户ID
	private String username = ""; //用户名
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(Date writeTime) {
		this.writeTime = writeTime;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}


