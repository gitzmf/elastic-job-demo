package app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author zmf
 * @version 1.0
 * @ClassName JobApplication
 * @Description: 测试启动类
 * @date 2020/3/8 14:45
 */
public class JobApplication {

    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("elastic-job.xml");
    }
}
