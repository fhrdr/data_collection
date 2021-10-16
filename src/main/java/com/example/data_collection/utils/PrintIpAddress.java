package com.example.data_collection.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

// 获取 IP 地址
@Slf4j
public class PrintIpAddress {
    // 打印日志 ， 返回ip
    public static String getIpAddress(HttpServletRequest request , String info) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.hasLength(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                log.info(ip.substring(0, index) + " " + info);
                return ip;
            } else {
                log.info(ip + " " + info);
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.hasLength(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            log.info(ip + " " + info);
            return ip;
        }
        log.info(request.getRemoteAddr() + " " + info);
        return request.getRemoteAddr();
    }

    // 不打印日志 ， 返回ip
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.hasLength(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip;
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (StringUtils.hasLength(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }
}
