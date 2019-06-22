package czz.study.sendemail.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 发送邮件Api
 *
 * @author chenzhengzhou
 * @version 1.0
 * @date 2019/6/20 23:34
 */
@RestController
@RequestMapping("/send")
@AllArgsConstructor
public class SendMail {

    private final JavaMailSender mailSender;

    /**
     * 发送普通邮件
     *
     * @return 发送结果
     */
    @RequestMapping("/commonMail")
    public String commonMail() {
        //创建要发送的邮件实体
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom("3577XXXXX@qq.com");
        // 收件人,如果发送多个人，用数组来表示
        // String[] array = new String[]    {"sun111@163.com","sun222@sohu.com"};
        // mailMessage.setTo(array);
        message.setTo("3577XXXXX@qq.com");
        //主题
        message.setSubject("SpringBoot发送普通邮件");
        //正文
        message.setText("hello world!!");
        mailSender.send(message);
        System.out.println("普通邮件发送成功");
        return "ok";
    }

    /**
     * 发送HTML邮件
     *
     * @return 发送结果
     */
    @RequestMapping("/htmlMail")
    public String htmlMail() {
        //创建要发送的邮件实体
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //创建数据填充器，这个true代表是否创建支持替代文本、内联元素和附件的多功能消息
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //发件人
            helper.setFrom("3577XXXXX@qq.com");

            // 收件人,如果发送多个人，用数组来表示
            // String[] array = new String[]    {"sun111@163.com","sun222@sohu.com"};
            // mailMessage.setTo(array);
            helper.setTo("3577XXXXX@qq.com");
            //主题
            helper.setSubject("SpringBoot发送HTML邮件");
            //这里可以传入HTML语句，可以添加跳转链接等,镶嵌图片教程在下面这个方法
            String html = "<html>\n" +
                    "<body>\n" +
                    "<h1>HTML邮件标签</h1>" +
                    "<p>HTML邮件发送成功</p>" +
                    "</body>\n" +
                    "</html>";
            helper.setText(html, true);
            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("邮件发送失败");
        }
        System.out.println("HTML邮件发送成功");

        return "ok";
    }

    /**
     * 发送带图片邮件
     *
     * @return 发送结果
     */
    @RequestMapping("/pictureMail")
    public String pictureMail() {
        //创建要发送的邮件实体
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //创建数据填充器，这个true代表是否创建支持替代文本、内联元素和附件的多功能消息
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //发件人
            helper.setFrom("3577XXXXX@qq.com");

            // 收件人,如果发送多个人，用数组来表示
            // String[] array = new String[]    {"sun111@163.com","sun222@sohu.com"};
            // mailMessage.setTo(array);
            helper.setTo("3577XXXXX@qq.com");
            //主题
            helper.setSubject("SpringBoot发送图文邮件");

            //true 表示启动HTML格式的邮件
            helper.setText("<h1>发送带图片的HTML</h1><img src='cid:aaaa'/>", true);
            //指定要内联的文件
            FileSystemResource img = new FileSystemResource(new File("C:\\Users\\czz\\Desktop\\11.png"));
            //将文件和HTML内联
            helper.addInline("aaaa", img);
            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("邮件发送失败");
        }
        System.out.println("图片邮件发送成功");

        return "ok";
    }

    /**
     * 发送带附件邮件
     *
     * @return 发送结果
     */
    @RequestMapping("/attachmentMail")
    public String attachmentMail() {
        //创建要发送的邮件实体
        MimeMessage message = mailSender.createMimeMessage();
        try {
            //创建数据填充器，这个true代表是否创建支持替代文本、内联元素和附件的多功能消息
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //发件人
            helper.setFrom("3577XXXXX@qq.com");

            // 收件人,如果发送多个人，用数组来表示
            // String[] array = new String[]    {"sun111@163.com","sun222@sohu.com"};
            // mailMessage.setTo(array);
            helper.setTo("3577XXXXX@qq.com");
            //主题
            helper.setSubject("SpringBoot发送附件邮件");
            //正文
            helper.setText("发送附件");
            FileSystemResource attachment = new FileSystemResource(new File("C:\\Users\\czz\\Desktop\\11.png"));

            helper.addAttachment(attachment.getFilename(), attachment);
            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("邮件发送失败");
        }
        System.out.println("附件邮件发送成功");

        return "ok";
    }
}