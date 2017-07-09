import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.Assert;


/**
 * Created by lixiaohao
 * Date:${DATA}
 * Description:
 */
public class LoginLogoutTest {

    @org.junit.Test
    public void testJDBCRealm() {

        //1、获取securityManager 工厂,此处使用 Ini配置文件初始化 SecurityManager
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");

        //将得到的securityManager实例,绑定到securityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);


        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","12345");

        try {

            subject.login(token);
        }catch (AuthenticationException e) {

            e.printStackTrace();
        }

        Assert.assertEquals(true,subject.isAuthenticated());


        //退出

        subject.logout();

    }

    public void tearDown() throws Exception {
        //退出时请解除绑定Subject到线程 否则对下次测试造成影响
        ThreadContext.unbindSubject();
    }

}
