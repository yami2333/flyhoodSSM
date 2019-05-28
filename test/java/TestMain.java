import com.neuedu.dao.UserMapper;
import com.neuedu.service.UserinfoService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMain {
    @Test
    public void test1(){
        ApplicationContext AC = new ClassPathXmlApplicationContext(new String[]{"spring.xml","spring-mybatis.xml"});
//        UserinfoService us = AC.getApplicationName(UserinfoService);

    }
}
