package 오브젝트_클래스.객체처리_Object_메서드;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ToString메서드
{
    public boolean isMemoryAddress(String s)
    {
        return (s.contains("java.") && s.contains("@"));
    }

    @Test
    public void default_toString()
    {
        //기본적으로 toString()은 메모리주소를 나타냄.
        Object o = new Object();
        assertThat(isMemoryAddress(o.toString())).isTrue();

        //내부적으로 toString()은 해시코드를 사용함.
        assertThat(o.toString()).isEqualTo(
                o.getClass().getName() + '@' + Integer.toHexString(o.hashCode()));
    }

    @Test
    public void sout프린트()
    {
        // System.out.println()은 toString()의 값을 출력함.
        Object o = new Object();
        System.out.println(o);
        System.out.println(o.toString());
    }

    @Test
    public void 문자열더하기()
    {
        // 문자열 + 연산도 toString()값을 concat함.
        Object o = new Object();
        System.out.println("hello " + o);
        System.out.println("hello " + o.toString());
    }

    class OverrideToStringClass
    {
        @Override
        public String toString()
        {
            return "오버라이드 된 ToString";
        }
    }

    @Test
    public void override_ToString()
    {
        // toString()은 오버라이드해서 커스텀하게 쓸 수 있다.
        OverrideToStringClass o = new OverrideToStringClass();
        assertThat(isMemoryAddress(o.toString())).isFalse();
        assertThat(o.toString()).isEqualTo("오버라이드 된 ToString");
    }
}
