package 기본.객체지향_프로그래밍;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class 인터페이스
{
    @Nested
    class 인터페이스는_다중_구현이_가능하다
    {
        @Test
        public void 다중_인터페이스_테스트()
        {
            Flyable fish1 = new FlyFish();
            fish1.fly();
            fish1.live();

            Swimmable fish2 = new FlyFish();
            fish2.swim();
            fish2.live();

            FlyFish fish3 = new FlyFish();
            fish3.fly();
            fish3.swim();
            fish3.live();
        }
    }

    @Nested
    class 인터페이스는_구체적인_구현이_없어서_다중구현해도_충돌나지_않는다
    {

    }


    interface Flyable
    {
        public void fly();

        public void live();
    }

    interface Swimmable
    {
        public void swim();

        public void live();
    }

    class FlyFish implements Flyable, Swimmable
    {
        @Override
        public void fly()
        {
            System.out.println("날치가 날아간다.");
        }

        @Override
        public void swim()
        {
            System.out.println("날치가 헤엄친다.");
        }

        @Override
        public void live()
        {
            System.out.println("날치가 행복하게 살아간다.");
        }
    }
}
