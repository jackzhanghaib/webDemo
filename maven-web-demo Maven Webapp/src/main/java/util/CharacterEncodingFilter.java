package util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/** 
 * @ClassName: CharacterEncodingFilter 
 * @Description: TODO ��������Ĺ�����
 * @author zhb
 * @date 2018-3-23 ����9:57:45 
 * @type 
 */

public class CharacterEncodingFilter implements Filter{
	protected String encoding = null;    //��������ʽ����
	protected FilterConfig filterConfig = null; //������������ö���
	public void init(FilterConfig filterConfig){
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");  //��ȡ�����ļ��ƶ��ı����ʽ
	}
	/**
	 * �������Ľӿڷ���������ִ�й���ҵ��
	 */
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain){
		if(encoding != null){
			try {
				request.setCharacterEncoding(encoding);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//������Ӧ�������������(���������ʽ)
			response.setContentType("text/html;charset="+encoding);
		}
		try {
			chain.doFilter(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		this.filterConfig = null;
		this.encoding = null;
	}
}


