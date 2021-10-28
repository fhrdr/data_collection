package com.example.data_collection.controller;

import com.example.data_collection.result.ResponseResult;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;

@RestController
public class CaptchaController {

    /**
     * 获取验证码
     * @param request request
     * @param response reponse
     * @throws Exception 抛出错误
     */
    @RequestMapping("/getCaptcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        // 设置字体
        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        // 验证码存入session
        request.getSession().setAttribute("captcha", specCaptcha.text().toLowerCase());
        // 输出图片流
        specCaptcha.out(response.getOutputStream());
    }

    /**
     * 验证验证码
     * @param verCode 验证码
     * @param request request
     * @return 返回结果
     */
    @PostMapping("/judgeCaptcha")
    public ResponseResult login(String verCode, HttpServletRequest request){
        // 获取session中的验证码
        String sessionCode = (String) request.getSession().getAttribute("captcha");
        // 判断验证码
        if (verCode==null || !sessionCode.equals(verCode.trim().toLowerCase())) {
            return ResponseResult.FAILED("验证码不正确");
        }
        return ResponseResult.SUCCESS("验证码正确");
    }
}
