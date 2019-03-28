package com.atguigu.bookstore.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.Book;

public class WebUtils {
	/**
	 * 截取分页对象需要的路径的方法
	 * @return
	 */
	public static String getPath(HttpServletRequest request) {
		//动态获取 本次查询分页数据的url地址  /BookStore_EG04/BookManagerServlet
    	String requestURI = request.getRequestURI();//访问服务器时资源定位符 从服务器8080开始到？中间的内容
    	//获取查询时的字符串 type=findPage&pageNumber=1
    	String queryString = request.getQueryString();
    	if(queryString!=null && queryString.contains("&pageNumber")) {
    		queryString = queryString.substring(0, queryString.indexOf("&pageNumber"));
    	}
    	//System.out.println(requestURI+"?"+queryString);
    	return requestURI+"?"+queryString;
	}
	
	/**
	 * 将请求参数自动设置给javabean的方法
	 * 	- 请求参数的name属性值必须和javabean的属性名一样
	 * @param t
	 * @param request
	 * @return
	 */
	public static<T> T param2Bean(T t,HttpServletRequest request) {
    	//获取请求参数的Map<String name,String[] values>
    	Map<String, String[]> map = request.getParameterMap();
    	//准备要设置数据的对象
    	
    	//使用BeanUtils设置数据:需要导入相关jar包  需要beanutils.jar和依赖包 logging.jar
    	//System.out.println("设置前的Book："+t);
    	try {
			BeanUtils.populate(t, map);
		//	System.out.println("设置后的Book："+t);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//返回设置过数据的对象
		return t;
	}
}
