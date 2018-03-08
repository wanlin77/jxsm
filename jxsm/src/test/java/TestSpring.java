import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wl.jx.dao.FactoryDao;
import com.wl.jx.domain.Factory;

/**
 * @Description:
 * @Author: nutony
 * @Company: http://java.itcast.cn
 * @CreateDate: 2014-3-13
 */
public class TestSpring {

	@Test
	public void testService() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		FactoryDao oDao = (FactoryDao) ac.getBean("daoFactory");
		Factory f = oDao.get("1");
		System.out.println(f.getFullName());
	}
}
