import dao.TestDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * User: shenzhang
 * Date: 6/3/14
 * Time: 10:56 PM
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        TestDao dao = context.getBean("fish", TestDao.class);
        System.out.println(dao.random());
    }
}

