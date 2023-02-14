package com.whxiaoyu.auth.web;

import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义web异常
 * @author jinxiaoyu
 */
@Controller
public class CustomizeErrorController extends BasicErrorController {

    public CustomizeErrorController(ServerProperties serverProperties) {
        super(new DefaultErrorAttributes(),serverProperties.getError());
    }

    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        Map<String, Object> body = this.getErrorAttributes(request, this.getErrorAttributeOptions(request, MediaType.ALL));
        //输出自定义的Json格式
        Map<String, Object> map = new HashMap<>(body.size());
        map.put("msg", body.get("message"));
        map.put("code", status.value());
        return new ResponseEntity<>(map,status);
    }
}
