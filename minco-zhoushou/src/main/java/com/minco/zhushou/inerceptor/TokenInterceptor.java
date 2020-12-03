package com.minco.zhushou.inerceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉
 * <br>
 *
 * @author 明科
 * @create 2020/6/23 20:19
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestURI = request.getRequestURI();
        //response.setHeader(HeaderConst.START_TIME, "" + Instant.now().toEpochMilli());
        //
        //boolean checkToken = TokenUtils.checkToken(requestURI);
        //if (!checkToken) {
        //    return true;
        //}
        //
        //if (requestURI.startsWith("/error")) {
        //    throw new BsException(CommonCodeEnum.NOT_FOUND_ERROR);
        //}
        //
        //String authToken = request.getHeader(HeaderConst.AUTH_TOKEN);
        //if (Objects.isNull(authToken)) {
        //    throw new BsException(UserCodeEnum.TOKEN_NOT_EXIST);
        //}
        //
        //TokenDTO tokenDTO = null;
        //try {
        //    tokenDTO = TokenUtils.getTokenDTOByAuthToken(authToken);
        //
        //} catch (Exception e) {
        //    throw new BsException(UserCodeEnum.TOKEN_WRONG_FUL);
        //}
        //
        //if (!TokenUtils.checkPermission(requestURI, tokenDTO.getPermissionDTOS())) {
        //    throw new BsException(UserCodeEnum.USER_NOT_THIS_PERMISSION);
        //}
        ////设置 userName
        //request.setAttribute(HeaderConst.USER_NAME, tokenDTO.getUserName());
        //if (!StringUtil.isEmpty(tokenDTO.getUserId())) {
        //    request.setAttribute(HeaderConst.USER_ID, tokenDTO.getUserId());
        //}
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //String startTime = response.getHeader(HeaderConst.START_TIME);
        //long cs = System.currentTimeMillis() - Long.valueOf(startTime);
        //log.info("接口耗时 url={} 耗时={}", request.getRequestURI(), cs);
        ////操作日志
        //String name = (String) request.getAttribute(HeaderConst.USER_NAME);
        //if (Objects.nonNull(name)) {
        //    log.info("操作日志 url={} operator={}", request.getRequestURI(), name);
        //}
    }


}
