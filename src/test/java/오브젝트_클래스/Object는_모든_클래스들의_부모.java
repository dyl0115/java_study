package 오브젝트_클래스;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class Object는_모든_클래스들의_부모
{
    @Test
    public void 각_클래스들의_상속관계_테스트()
    {
        Object o1 = new Car();
        Object o2 = new Dog();

        Object[] arr = new Object[2];
        arr[0] = o1;
        arr[1] = o2;

        for (Object o : arr)
        {
            if (o instanceof Car car)
            {
                car.sound();
            }
            else if (o instanceof Dog dog)
            {
                dog.sound();
            }
        }
    }
}

class Car
{
    public void sound()
    {
        System.out.println("빵빵");
    }
}

class Dog
{
    public void sound()
    {
        System.out.println("멍멍");
    }
}

