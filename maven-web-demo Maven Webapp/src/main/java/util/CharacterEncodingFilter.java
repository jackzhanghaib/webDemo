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
 * @Description: TODO 处理乱码的过滤器
 * @author zhb
 * @date 2018-3-23 下午9:57:45 
 * @type 
 */

public class CharacterEncodingFilter implements Filter{
	protected String encoding = null;    //定义编码格式变量
	protected FilterConfig filterConfig = null; //定义过滤器配置对象
	public void init(FilterConfig filterConfig){
		this.filterConfig = filterConfig;
		this.encoding = filterConfig.getInitParameter("encoding");  //获取配置文件制定的编码格式
	}
	/**
	 * 过滤器的接口方法，用于执行过滤业务
	 */
	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain){
		if(encoding != null){
			try {
				request.setCharacterEncoding(encoding);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//设置响应对象的内容类型(包括编码格式)
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


