package com.foal.liuliang.web;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.util.GsonTools;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unchecked")
public class BaseAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2269532542366958839L;
	protected AjaxBean ajaxBean;
	
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
	
	protected void setAttrToRequest(String key, Object obj) {
		this.getRequest().setAttribute(key, obj);
	}
	
	protected Object getAttrFromRequest(String key) {
		return this.getRequest().getAttribute(key);
	}
	
	protected HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	protected HttpSession getSession() {
		return this.getRequest().getSession();
	}
	
	protected void setAttrToSession(String key, Object obj) {
		this.getSession().setAttribute(key, obj);
	}
	
	protected Object getAttrFromSession(String key) {
		return this.getSession().getAttribute(key);
	}
	
	protected Map getApplication() {
		return ActionContext.getContext().getApplication();
	}
	
	protected void setAttrToApp(String key, Object obj) {
		this.getApplication().put(key, obj);
	}
	
	protected Object getAttrFromApp(String key) {
		return this.getApplication().get(key);
	}
	
	protected void write(String msg) {
		PrintWriter out = null;
		try {
			HttpServletResponse response = this.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
			out = response.getWriter();
			out.write(msg);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
	protected void ajaxWrite(AjaxBean ajaxBean){
		this.write(GsonTools.toJsonString(ajaxBean));
	}
	
	protected void ajaxWrite(String msg){
		this.write(msg);
	}
	
	protected void alertAndRedirect(String msg, String location) {
		PrintWriter out = null;
		try {
			HttpServletResponse response = this.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            out = response.getWriter();
			if (msg != null) {
				out.write("<script type='text/javascript'>alert('"+msg+"');</script>");
			}
			if (location != null) {
				out.write("<script type='text/javascript'>window.location.href='"+ Constant.PRO_CTX_VALUE + "/" + location +"'</script>");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
	protected void alertAndGoBack(String msg) {
		PrintWriter out = null;
		try {
			HttpServletResponse response = this.getResponse();
			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
            response.setHeader("Expires", "0");
            out = response.getWriter();
			if (msg != null) {
				out.write("<script type='text/javascript'>alert('"+msg+"');</script>");
			}
			out.write("<script type='text/javascript'>window.location.href='javascript:window.history.back();'</script>");
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
}
