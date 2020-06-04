package czz.study.sendemail;

import czz.study.sendemail.api.SendMail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 用于发送邮件的demo
 *
 * @author chenzhengzhou
 * @version 1.0
 * @date 2019/6/22 10:40
 */
@SpringBootApplication
public class SendEmailApplication {

    public static void main(String[] args) {
        SpringApplication.run(SendEmailApplication.class, args);
    }

}
