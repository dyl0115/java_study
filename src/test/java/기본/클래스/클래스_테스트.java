package 기본.클래스;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class 클래스_테스트
{
    class Person
    {
        String name;
        int age;
    }

    boolean isMemoryAddress(String address)
    {
        return address.contains("@");
    }

    @Test
    public void new연산자는_주소값을_반환()
    {
        System.out.println((new Person()));
        assertThat(isMemoryAddress((new Person()).toString())).isTrue();
    }

    @Test
    public void 변수는_new연산자가_반환한_주소를_저장한다()
    {
        Person person = new Person();
        System.out.println(person);
        assertThat(isMemoryAddress(person.toString())).isTrue();
    }

    @Test
    public void 같은_클래스_다른_객체()
    {
        Person p1 = new Person();
        Person p2 = new Person();

        // 클래스는 같다.
        assertThat(p1.getClass()).isEqualTo(p2.getClass());

        // 하지만 객체(메모리 주소)는 다르다.
        assertThat(p1 == p2).isFalse();
    }

    @Test
    public void 객체_vs_인스턴스()
    {
        // 객체는 객체만을 강조
        // 인스턴스는 클래스-객체의 "관계"를 강조함.

        // 하지만 둘을 구분하지 않는다.
    }
}


