package 동적프록시;

import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class 동적프록시_장점_테스트
{
    public void 여러_인터페이스를_하나의_동적프록시로_처리가능()
    {
        
    }
}

interface UserService
{
    void save(String user);
}

class UserServiceImpl implements UserService
{
    @Override
    public void save(String user)
    {
        System.out.println(user + " 저장 완료!");
    }
}

interface OrderService
{
    void order(String itemName);
}

class OrderServiceImpl implements OrderService
{
    @Override
    public void order(String itemName)
    {
        System.out.println(itemName + " 주문 완료!");
    }
}

@AllArgsConstructor
class TimeHandler implements InvocationHandler
{
    private final Object target;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        long start = System.currentTimeMillis();
        method.invoke(target, args);
        System.out.println("처리 시간: " + (System.currentTimeMillis() - start) + "ms");
        return null;
    }
}


