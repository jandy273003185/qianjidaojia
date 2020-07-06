package com.qifenqian.bms.v2.common.mvc.config;

import com.github.pagehelper.PageHelper;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiBin
 * @Description: 分页参数解析器
 * @date 2020/4/2 15:50
 */
@Component
public class PageQueryArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(PageQuery.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        PageQuery pageQuery = new PageQuery();
        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        if (StringUtils.isBlank(pageNum)) {
            pageNum = "1";
        }
        if (StringUtils.isBlank(pageSize)) {
            pageSize = "10";
        }
        pageQuery.setPageNum(Integer.valueOf(pageNum));
        pageQuery.setPageSize(Integer.valueOf(pageSize));
        PageHelper.startPage(pageQuery.getPageNum(), pageQuery.getPageSize());
        return pageQuery;
    }

}
