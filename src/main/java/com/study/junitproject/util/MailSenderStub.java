package com.study.junitproject.util;

import org.springframework.stereotype.Component;

// 가짜!!
// IoC 컨테이너 등록
@Component
public class MailSenderStub implements MailSender{

    @Override
    public boolean send() {
        return true;
    }

}
