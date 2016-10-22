package cn.jsutils.wechat.utils;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by cikai on 16-10-22.
 */
public class CharsetEncodingFilter implements Filter {

    private String encoding = "UTF-8";

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            encoding = encodingParam;
        }
    }

    public void destroy() {
    }
}
