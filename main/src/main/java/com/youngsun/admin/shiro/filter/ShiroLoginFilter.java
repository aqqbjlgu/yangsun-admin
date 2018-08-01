package com.youngsun.admin.shiro.filter;

import com.youngsun.admin.util.JsonUtils;
import com.youngsun.admin.vo.CmsUserVo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShiroLoginFilter extends AdviceFilter {

    private static final Logger log = LoggerFactory.getLogger(ShiroLoginFilter.class);
    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        CmsUserVo cmsUserVo = (CmsUserVo) httpServletRequest.getSession().getAttribute("userSession");
        if (null == cmsUserVo && !StringUtils.contains(httpServletRequest.getRequestURI(), "/login")) {
            String requestedWith = httpServletRequest.getHeader("X-Requested-With");
            if (StringUtils.isNotEmpty(requestedWith) && StringUtils.equals(requestedWith, "XMLHttpRequest")) {//如果是ajax返回指定数据
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json");
                httpServletResponse.getWriter().write(JsonUtils.objectToJson(httpServletResponse));
                return false;
            } else {//不是ajax进行重定向处理
                httpServletResponse.sendRedirect("/login/local");
                return false;
            }
        }
        return true;
    }
}
