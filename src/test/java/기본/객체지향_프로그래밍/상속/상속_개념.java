package 기본.객체지향_프로그래밍.상속;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class 상속_개념
{
    // 1. 부모클래스의 기능에 새로운 기능을 추가한 클래스를 계속해서 확장할 수 있음.
    // 2. 부모클래스에 새로운 기능을 변경하면, 자식클래스도 자동 변경됨

    @Nested
    class 상속은_클래스_확장이_매우_쉽다
    {
        class Car
        {
            public void move()
            {
                System.out.println("자동차가 이동합니다.");
            }
        }

        class Avante extends Car
        {
            public void chargeGas()
            {
                System.out.println("가스를 충전합니다.");
            }
        }

        class Tesla extends Car
        {
            public void chargeElectricity()
            {
                System.out.println("전기를 충전합니다.");
            }
        }
    }

    @Nested
    class 상속은_부모클래스만_변경하면_자동으로_자식도_변경됨
    {
        class Car
        {
            public void move()
            {
//                System.out.println("자동차가 이동합니다.");
                System.out.println("자동차가 부릉부릉 이동합니다.");
            }
        }

        class Avante extends Car
        {
            public void chargeGas()
            {
                System.out.println("가스를 충전합니다.");
            }
        }

        class Tesla extends Car
        {
            public void chargeElectricity()
            {
                System.out.println("전기를 충전합니다.");
            }
        }
    }

    @Nested
    class 오버라이딩을_통해_부모메서드를_커스텀할_수_있다
    {

    }

    @Nested
    class 메서드는_오버라이딩된_메서드가_항상_우선권을_가진다
    {
        class Parent
        {
            String variable = "Parent";

            public String method()
            {
                return "Parent";
            }
        }

        class Child extends Parent
        {
            String variable = "Child";

            public String method()
            {
                return "Child";
            }
        }

        @Test
        public void 테스트()
        {
            Parent parent = new Parent();
            assertThat(parent.variable).isEqualTo("Parent");
            assertThat(parent.method()).isEqualTo("Parent");

            Child child = new Child();
            assertThat(child.variable).isEqualTo("Child");
            assertThat(child.method()).isEqualTo("Child");

            Parent entity = new Child();
            assertThat(entity.variable).isEqualTo("Parent");
            // 이 부분이 중요하다!
            assertThat(entity.method()).isEqualTo("Child");
        }

    }

    @Nested
    class 자식클래스의_생성자는_반드시_super메서드를_포함해야_함
    {
        class Super
        {
            final int age;

            public Super(int age)
            {
                this.age = age;
            }
        }

        class Sub extends Super
        {
            public Sub()
            {
                super(10);
            }
        }
    }

    @Nested
    class 자식클래스_생성시_부모클래스도_자동생성됨
    {
        static class ClassA
        {
            ClassA()
            {
                System.out.println("ClassA 생성됨");
            }

        }

        static class ClassB extends ClassA
        {
            ClassB()
            {
                System.out.println("ClassB 생성됨");
            }

        }

        static class ClassC extends ClassB
        {
            ClassC()
            {
                System.out.println("ClassC 생성됨");
            }

        }

        @Test
        public void 슈퍼클래스_서브클래스_실행순서()
        {
            ClassC classC = new ClassC();
        }

    }

    @Nested
    class final로_선언된_클래스는_상속이_불가능
    {
        final class Super
        {


        }
        // class Sub extends Super -> 오류

    }
}
