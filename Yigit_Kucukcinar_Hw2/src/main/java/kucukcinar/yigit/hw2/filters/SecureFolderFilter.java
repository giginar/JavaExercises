package kucukcinar.yigit.hw2.filters;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kucukcinar.yigit.hw2.mbeans.CustomerLoginBean;

/**
 * Servlet Filter implementation class SecureFolderFilter
 */
@WebFilter("/secure/*")
public class SecureFolderFilter implements Filter {

	@Inject
	private CustomerLoginBean loginBean;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		if (loginBean == null || !loginBean.isLoggedIn()) {
			
			String page = req.getRequestURI().replace(req.getContextPath(), "");
			page = page.replace("\\.xhtml", "");
			loginBean.setAccessPage(page);
			res.sendRedirect(req.getContextPath() + "/login.xhtml");
			FacesContext.getCurrentInstance().addMessage("Wrong credentials",
					new FacesMessage("Wrong credentials!!", "you need to be logged in."));
			return;
		}
		chain.doFilter(request, response);
	}
}
