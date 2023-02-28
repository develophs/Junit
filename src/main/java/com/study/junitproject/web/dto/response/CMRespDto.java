package com.study.junitproject.web.dto.response;

import lombok.Getter;

@Getter
public class CMRespDto<T> {
    private Integer code; //예시)1 성공, -1 실패
    private String msg; // 에러메시지, 성공에 대한 메시지
    private T data;

    public CMRespDto() {
    }

    public CMRespDto(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
