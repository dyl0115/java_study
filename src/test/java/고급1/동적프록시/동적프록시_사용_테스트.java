package 고급1.동적프록시;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.*;

public class 동적프록시_사용_테스트
{
    @Test
    public void GoodProxy테스트()
    {
        Good goodProxy = (Good) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{Good.class},
                new MyHandler(new GoodImpl()));

        String hello = goodProxy.sayHello("David");
        assertThat(hello).isEqualTo("HELLO DAVID");

        Integer number = goodProxy.sayNumber();
        assertThat(number).isEqualTo(7777);
    }

    @Test
    public void BadProxy테스트()
    {
        Bad badProxy = (Bad) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{Bad.class},
                new MyHandler(new BadImpl()));

        String fuckYou = badProxy.sayFuckYou("David");
        assertThat(fuckYou).isEqualTo("FUCK YOU DAVID");

        Integer number = badProxy.sayNumber();
        assertThat(number).isEqualTo(4444);
    }
}

interface Good
{
    String sayHello(String name);

    Integer sayNumber();
}

class GoodImpl implements Good
{
    @Override
    public String sayHello(String name)
    {
        return "Hello " + name;
    }

    @Override
    public Integer sayNumber()
    {
        return 7777;
    }
}

interface Bad
{
    String sayFuckYou(String name);

    Integer sayNumber();
}

class BadImpl implements Bad
{
    @Override
    public String sayFuckYou(String name)
    {
        return "Fuck You " + name;
    }

    @Override
    public Integer sayNumber()
    {
        return 4444;
    }
}

@AllArgsConstructor
class MyHandler implements InvocationHandler
{
    private final Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        Object ret = method.invoke(target, args);
        if (ret.getClass().equals(String.class))
        {
            return ((String) ret).toUpperCase();
        }
        return ret;
    }
}


