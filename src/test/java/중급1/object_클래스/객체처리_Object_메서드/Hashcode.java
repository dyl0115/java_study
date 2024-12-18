package 중급1.object_클래스.객체처리_Object_메서드;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class Hashcode
{
    @Test
    public void what_is_hashCode()
    {
        Object o = new Object();
        System.out.println(o.hashCode());
    }

    @Test
    public void default_hashCode()
    {
        Object m1 = new Object();
        Object m2 = new Object();

        //동일한 객체는 (같은 memory주소)는 같다.
        assertThat(m1.hashCode()).isEqualTo(m1.hashCode());
        assertThat(m1.equals(m1)).isTrue();

        //동등한 객체는 (다른 memory주소, 다른 내용)은 다르다.
        assertThat(m1.hashCode()).isNotEqualTo(m2.hashCode());
        assertThat(m1.equals(m2)).isFalse();
    }

    @AllArgsConstructor
    class Person
    {
        private String name;

        @Override
        public int hashCode()
        {
            return Objects.hashCode(name);
        }
    }

    @Test
    public void override_hashCode()
    {
        // Person은 hashcode()만 오버라이딩. equals()는 오버라이딩 하지 않음.
        // equals()는 내부적으로 hashCode()를 사용하지 않는다.
        // equals()와 hashCode()는 내부적으로 따로 구현되어있다.
        Person m1 = new Person("david");
        Person m2 = new Person("david");
        assertThat(m1.hashCode()).isEqualTo(m2.hashCode());
        assertThat(m1.equals(m2)).isFalse();
    }

    @AllArgsConstructor
    class Animal
    {
        String name;

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null) return false;
            if (getClass() != o.getClass()) return false;
            Animal that = (Animal) o;
            return (this.name.equals(that.name));
        }
    }

    @Test
    public void override_equals()
    {
        // Animal은 equals()만 오버라이딩, hashCode()는 오버라이딩 하지 않음.
        // hashCode() 역시 내부적으로 equals()를 사용하지 않음.
        // 둘은 서로 독립적으로 구현되어 있음.
        Animal m1 = new Animal("david");
        Animal m2 = new Animal("david");
        assertThat(m1.equals(m2)).isTrue();
        assertThat(m1.hashCode()).isNotEqualTo(m2.hashCode());
    }
}
