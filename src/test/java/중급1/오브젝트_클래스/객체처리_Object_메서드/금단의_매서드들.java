package 중급1.오브젝트_클래스.객체처리_Object_메서드;

import lombok.AllArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class 금단의_매서드들
{
    @AllArgsConstructor
    class Person
    {
        String name;

        @Override
        protected Object clone() throws CloneNotSupportedException
        {
            return super.clone();
        }

        @Override
        protected void finalize() throws Throwable
        {
            super.finalize();
        }
    }

    @Test
    public void clone테스트()
    {
        // 사용법을 모르겠음. 알필요도 없대.
        Person m1 = new Person("david");
        Assertions.assertThatThrownBy(() -> m1.clone()).isInstanceOf(CloneNotSupportedException.class);
    }

    @Test
    public void finalize테스트() throws Throwable
    {
        Person m1 = new Person("david");
//        m1.finalize();
    }
}
