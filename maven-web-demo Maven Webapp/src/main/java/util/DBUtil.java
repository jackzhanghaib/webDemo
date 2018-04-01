package util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {

	/**连接数据库的工具类
	 * @param args
	 */
	public static Connection connection = null; //申明Connection对象的实例
	public Statement stmt = null;        //声明Statement对象的实例
	public ResultSet rs = null;			//声明ResultSet对象的实例
	public int result;
	private static String propFileName = "../resources/connDB.properties"; //指定配置文件的路径
	private static Properties prop = new Properties();
	private static String dbClassName = "com.mysql.jdbc.Driver";
	private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/db_9griddiary?user=root&password=123456&useUnicode=true";
	public DBUtil(){
		try{
			//将properties文件读取到IntputStream对象中
			InputStream in = getClass().getResourceAsStream(propFileName);
			prop.load(in);
			dbClassName = prop.getProperty("DB_CLASS_NAME");
			dbUrl = prop.getProperty("DB_URL",dbUrl);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//创建数据库连接方法
	public static Connection getConnection(){
		try {
			Class.forName(dbClassName).newInstance();
			connection = DriverManager.getConnection(dbUrl);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(connection == null){
			System.err.println("警告:获取数据库连接失败.\r\n连接"+dbClassName+"\r\n连接位置"+dbUrl);
		}
		return connection;
	}
	//执行查询功能
	public ResultSet executeQuery(String sql){
		try {
			connection = getConnection();
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return rs;
	}
	//执行更新功能
	public int excuteUpdate(String sql){
		try {
			connection = getConnection();
			stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			result = stmt.executeUpdate(sql);
		} catch (Exception e) {
			result = 0;
			e.printStackTrace();
		}
		return result;
	}
	//关闭连接方法
	public void close(){
		try {
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(connection != null){
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	public static void main(String[] args) {
		getConnection();
		System.out.println("连接成功");
	}

}
