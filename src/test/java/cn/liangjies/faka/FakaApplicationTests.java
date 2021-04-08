package cn.liangjies.faka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class FakaApplicationTests {
    @Autowired
    private JavaMailSender mailSender;
    @Test
    void contextLoads() {
        /*
        Map<String, String> jsonMap = new HashMap<>(6);
        jsonMap.put("name", "张耀烽");
        jsonMap.put("sex", "男");

//请求地址
        String url = "https://ll00.cn/Request/index.html";
        //入参

        RestTemplate restTemplate = new RestTemplate();
        String responseBean = restTemplate.postForObject(url, jsonMap, String.class);
        System.out.println(responseBean);

         */

//        System.out.println(System.currentTimeMillis()/1000);
////方法 二
//        System.out.println(Calendar.getInstance().getTimeInMillis());
////方法 三
//        System.out.println(new Date().getTime());
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("admin@faka.icewx.com");
//        message.setTo("1139629972@qq.com");
//        message.setSubject("商品购买成功");
//        message.setText("测试邮件内容");
//
//        mailSender.send(message);

    }

}
