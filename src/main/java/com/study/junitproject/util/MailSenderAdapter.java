package com.study.junitproject.util;

import org.springframework.stereotype.Component;

@Component
public class MailSenderAdapter implements MailSender{

    private Mail mail;

    public MailSenderAdapter(){
        this.mail = new Mail();
    }

    @Override
    public boolean send() {
        return mail.sendMail();
        //return true; 본코드 수정없이 어댑터만 바꾸면된다.
    }
}
