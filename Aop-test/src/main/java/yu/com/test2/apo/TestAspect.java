package yu.com.test2.apo;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import yu.com.test2.TestPojo;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname TestAspect
 * @Date 2021/4/6 15:43
 * @Created by yufx
 */

@Aspect // FOR AOP
@Order(-99) // 控制多个Aspect的执行顺序，越小越先执行
@Configuration // 配置类
public class TestAspect {

    @Pointcut( "execution( * yu.com.test2..*.*(..))")
    public void doPointcut(){
    }

    @Before("doPointcut()")
    public void doBefore(JoinPoint joinPoint) throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        // 请求开始时间
//        startTime.set(System.currentTimeMillis());
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        // 获取请求头数据
        String headValue = request.getHeader("userId");
//        System.out.println(headValue);
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        int x = 0;
        for (Object argItem : obj) {
//            System.out.println("---->now-->argItem:" + argItem);
            if (argItem instanceof Object) {
                Class type = argItem.getClass();
                Map map = new HashMap();
                BeanInfo beanInfo = Introspector.getBeanInfo(type);

                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (int i = 0; i < propertyDescriptors.length; i++) {
                    PropertyDescriptor descriptor = propertyDescriptors[i];
                    String propertyName = descriptor.getName();
                    if (!propertyName.equals("class")) {
                        Method readMethod = descriptor.getReadMethod();
                        Object result = readMethod.invoke(argItem, new Object[0]);
                        if (result != null) {
                            map.put(propertyName, result);
                        } else {
                            map.put(propertyName, "");
                        }
                    }
                }
                map.put("userId",headValue);
                String s = JSON.toJSONString(map);

                Object o = JSON.parseObject(s, Object.class);
                System.out.println(argItem);
                obj[x] = o;
                System.out.println(argItem);
                TestPojo testPojo = (TestPojo) argItem;
                testPojo.setUserId(headValue);
                x++;
            }
        }


    }

    @After("doPointcut()")
    public void doAfter(){
        System.out.println("==doAfter==");
    }

    @AfterReturning("doPointcut()")
    public void doAfterReturning(){
        System.out.println("==doAfterReturning==");
    }

    /**
     * 环绕通知返回值类型Object，对应所有被拦截方法的返回值类型，不能为void
     */
    @Around("doPointcut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint)throws Throwable{
        System.out.println("==doAround.before==");
        Object ret=proceedingJoinPoint.proceed();
        System.out.println("==doAround.after=="+ret);
        return ret;
    }

    /*
    * 打卡 ：惨不忍睹 签字盖章 问题很多 1.打卡制度不是了解
    * 2.漏卡
    * 3.外勤打卡
    * 打卡事情留意
    * 加班时间 过九点 和客户对接 改了大半天 来回对时间 针对 公司加班制度
    * 1.加班当月 工资里体现 平时加班过9点 不用加班申请
    * 2.加班周六周天 假期 申请加班
    *
    * 工作日清还行
    * 养成习惯日清避免不了
    * 尽量放宽
    * 互相理解
    * */
}

