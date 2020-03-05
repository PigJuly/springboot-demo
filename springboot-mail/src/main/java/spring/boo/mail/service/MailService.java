package spring.boo.mail.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @Author July
 * @Date Created in 2020/3/5 12:04
 * @Description 发送邮件
 * @Modified By
 * @Version 1.0
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    private static final Logger log= LoggerFactory.getLogger(MailService.class);

    /**
     * 普通文本消息
     * @param to  发送给谁
     * @param subject 主题
     * @param content 类容
     */
    public void sendSimpleMail(String to,String subject,String content){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        //由于谁来发送
        message.setFrom(from);
        mailSender.send(message);
    }

    /**
     * html邮件
     * @param to 发给谁
     * @param subject 主题
     * @param content 内容
     * @throws MessagingException
     */
    public void sendHtmlMail(String to,String subject,String content) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content,true);
        mailSender.send(message);
    }

    /**
     * 发送附件
     * @param to 发给谁
     * @param subject 主题
     * @param content 内容
     * @param filePath 图片路径
     */
    public void sendAttachmentsMail(String to,String subject,String content,String filePath)throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(message,true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content,true);
        //转换文件
        FileSystemResource file=new FileSystemResource(new File(filePath));
        //获取附件名称
        String fileName=file.getFilename();
        helper.addAttachment(fileName,file);
        mailSender.send(message);
    }


    /**
     * 发送图片
     * @param to 发给谁
     * @param subject 主题
     * @param content 内容
     * @param rscPath 图片路径
     * @param rscId
     */
    public  void sendInlinResourceMail(String to,String subject,String content,String rscPath,
                                       String rscId)throws MessagingException{
        MimeMessage messages = mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(messages,true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content,true);
        FileSystemResource res=new FileSystemResource(new File(rscPath));
        helper.addInline(rscId,res);
        mailSender.send(messages);
    }

}
