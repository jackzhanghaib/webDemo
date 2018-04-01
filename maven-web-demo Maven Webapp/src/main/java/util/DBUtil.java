package util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBUtil {

	/**�������ݿ�Ĺ�����
	 * @param args
	 */
	public static Connection connection = null; //����Connection�����ʵ��
	public Statement stmt = null;        //����Statement�����ʵ��
	public ResultSet rs = null;			//����ResultSet�����ʵ��
	public int result;
	private static String propFileName = "../resources/connDB.properties"; //ָ�������ļ���·��
	private static Properties prop = new Properties();
	private static String dbClassName = "com.mysql.jdbc.Driver";
	private static String dbUrl = "jdbc:mysql://127.0.0.1:3306/db_9griddiary?user=root&password=123456&useUnicode=true";
	public DBUtil(){
		try{
			//��properties�ļ���ȡ��IntputStream������
			InputStream in = getClass().getResourceAsStream(propFileName);
			prop.load(in);
			dbClassName = prop.getProperty("DB_CLASS_NAME");
			dbUrl = prop.getProperty("DB_URL",dbUrl);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	//�������ݿ����ӷ���
	public static Connection getConnection(){
		try {
			Class.forName(dbClassName).newInstance();
			connection = DriverManager.getConnection(dbUrl);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(connection == null){
			System.err.println("����:��ȡ���ݿ�����ʧ��.\r\n����"+dbClassName+"\r\n����λ��"+dbUrl);
		}
		return connection;
	}
	//ִ�в�ѯ����
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
	//ִ�и��¹���
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
	//�ر����ӷ���
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
		System.out.println("���ӳɹ�");
	}

}
