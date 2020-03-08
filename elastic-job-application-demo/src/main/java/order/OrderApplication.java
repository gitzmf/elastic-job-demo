package order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author zmf
 * @version 1.0
 * @ClassName OrderApplication
 * @Description: ${todo}
 * @date 2020/3/8 16:08
 */
@SpringBootApplication
/**开启事务管理*/
@EnableTransactionManagement
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
