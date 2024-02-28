package com.example.mission3.global.util;

import com.example.mission3.global.dto.ResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class CustomResponseUtil {

    public static void success(HttpServletResponse response, Object dto) {
        try {
            ObjectMapper om = new ObjectMapper();
            ResponseDto<?> responseDto = new ResponseDto<>(true, "로그인 성공", dto);
            String responseBody = om.writeValueAsString(responseDto);

            response.setContentType("application/json; charset=utf-8");
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().print(responseBody);
        } catch (Exception e) {
            log.error("서버 파싱 에러");
        }
    }

    public static void fail(HttpServletResponse response, String message, HttpStatus httpStatus) {
        try {
            ObjectMapper om = new ObjectMapper();
            ResponseDto<?> responseDto = new ResponseDto<>(false, message, null);
            String responseBody = om.writeValueAsString(responseDto);

            response.setContentType("application/json; charset=utf-8");
            response.setStatus(httpStatus.value());
            response.getWriter().print(responseBody);
        } catch (Exception e) {
            log.error("서버 파싱 에러");
        }
    }
}
