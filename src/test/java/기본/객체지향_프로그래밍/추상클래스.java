package 기본.객체지향_프로그래밍;

import org.junit.jupiter.api.Nested;

public class 추상클래스
{
    // 추상클래스는 기존 클래스에 제약을 주는 것이다.
    // 1. 직접 생성하는 것을 막는다.

    // 추상메서드도 기존의 메서드에 제약을 주는 것이다.
    // 1. 상속을 받는 클래스의 오버라이딩을 강제한다.

    @Nested
    class 추상클래스는_직접_생성할_수_없다
    {

    }

    @Nested
    class 추상메서드는_오버라이딩을_강제한다
    {

    }

    @Nested
    class 추상메서드가_있는_클래스는_반드시_추상클래스다
    {
        // 어찌보면 당연하다.
        // 추상 메서드가 있는 클래스는 그 자체로는 생성할 수 없다.
        // 추상 메서드의 내용을 적지 않았기 때문이다.
    }

    @Nested
    class 추상클래스는_다중상속_안된다
    {
        abstract class Bird
        {
            abstract void fly();
        }

        abstract class Iguana
        {
            abstract void smash();
        }

//        class Dragon extends Bird, Iguana
//        {
//            @Override
//            void fly()
//            {
//
//            }
//
//            @Override
//            void smash()
//            {
//
//            }
//        }
    }

}