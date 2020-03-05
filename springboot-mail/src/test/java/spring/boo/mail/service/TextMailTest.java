package spring.boo.mail.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import spring.boo.mail.service.MailService;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TextMailTest {

    @Resource
    MailService textMail;

    @Resource
    TemplateEngine templateEngine;

    @Test
    public void sendSimplMailTest(){
        textMail.sendSimpleMail("4173844@qq.com","这是一个测试","你好呀！");
    }

    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String content="<html>\n"+
                "<body>\n"+
                "<h2> hello mail .这是一个测试html邮件!</h2>"+
                "</body>"+
                "</html>";
        textMail.sendHtmlMail("4173844@qq.com","这是html邮箱",content);
    }

    @Test
    public void sendAttachmentsMailTest() throws MessagingException {
        String content="<html>\n"+
                "<body>\n"+
                "<h2> hello mail .这是一个测试html邮件!</h2>"+
                "</body>"+
                "</html>";
        textMail.sendAttachmentsMail("4173844@qq.com","这是html邮箱",content,"C:\\Users\\Admin\\Desktop\\我的文档\\记录.txt");
    }

    @Test
    public void sendInlinResourceMailTest() throws  MessagingException{
        String rscId="12312";
        String content="<html><body>这是一张图片:\n<img src=\'cid:"+rscId+"\'></img></body></html>";
        textMail.sendInlinResourceMail("4173844@qq.com","这是html邮箱",content,"D:\\blog\\110446.png",rscId);
    }

    @Test
    public void moban() throws  MessagingException{
        Context context=new Context();
        context.setVariable("id","008");
        String emailContent=templateEngine.process("mail",context);
        textMail.sendHtmlMail("4173844@qq.com","这是一个模版文件",emailContent);
    }
}
