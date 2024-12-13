import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.TreeSet;

import static org.assertj.core.api.Assertions.*;

// equals(), hashcode() 모두 오버라이딩하지 않은 클래스
@AllArgsConstructor
@Getter
class DefaultHello
{
    String name;
}

// equals()만 오버라이딩한 클래스
@AllArgsConstructor
@Getter
class EqualsHello
{
    String name;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        EqualsHello that = (EqualsHello) o;
        return Objects.equals(name, that.name);
    }
}

// hashcode()만 오버라이딩한 클래스
@AllArgsConstructor
@Getter
class HashcodeHello
{
    String name;

    @Override
    public int hashCode()
    {
        return Objects.hashCode(name);
    }
}

// equals(), hashcode() 모두 오버라이딩한 클래스
@AllArgsConstructor
@Getter
@EqualsAndHashCode
class AllHello
{
    String name;
}

public class EqualsAndHashcode
{
    @Test
    public void defaultHello()
    {
        DefaultHello m1 = new DefaultHello("hello");
        DefaultHello m2 = new DefaultHello("hello");

        assertThat(m1).isNotEqualTo(m2); //isEqualsTo()는 equals()로 비교함.
        assertThat(m1.equals(m2)).isFalse();
        assertThat(m1.hashCode()).isNotEqualTo(m2.hashCode());
    }

    @Test
    public void equalsHello()
    {
        EqualsHello m1 = new EqualsHello("hello");
        EqualsHello m2 = new EqualsHello("hello");

        assertThat(m1).isEqualTo(m2);
        assertThat(m1.equals(m2)).isTrue();

        //equals()가 같다고 hashcode()가 같아지는 것은 아니다.
        assertThat(m1.hashCode()).isNotEqualTo(m2.hashCode());
    }

    @Test
    public void hashcodeHello()
    {
        HashcodeHello m1 = new HashcodeHello("hello");
        HashcodeHello m2 = new HashcodeHello("hello");

        //hashcode()가 같다고 equals()가 같아지는 것은 아니다.
        assertThat(m1).isNotEqualTo(m2);
        assertThat(m1.equals(m2)).isFalse();

        assertThat(m1.hashCode()).isEqualTo(m2.hashCode());
    }

    @Test
    public void allHello()
    {
        AllHello m1 = new AllHello("hello");
        AllHello m2 = new AllHello("hello");

        assertThat(m1).isEqualTo(m2);
        assertThat(m1.equals(m2)).isTrue();
        assertThat(m1.hashCode()).isEqualTo(m2.hashCode());
    }

    @Test
    public void Default클래스는_HashSet에서_다르게_인식()
    {
        HashSet<DefaultHello> set = new HashSet<>();
        DefaultHello m1 = new DefaultHello("hello");
        DefaultHello m2 = new DefaultHello("hello");
        set.add(m1);
        set.add(m2);

        assertThat(set.size()).isEqualTo(2);

        // 해시코드가 다르면 -> 다른 버킷에 담김. -> 다르게 인식.
    }

    @Test
    public void Equals클래스는_HashSet에서_다르게_인식()
    {
        HashSet<EqualsHello> set = new HashSet<>();
        EqualsHello m1 = new EqualsHello("hello");
        EqualsHello m2 = new EqualsHello("hello");
        set.add(m1);
        set.add(m2);

        assertThat(set.size()).isEqualTo(2);

        // 해시코드가 다르면 -> 다른 버킷에 담김. -> 다르게 인식.
    }

    @Test
    public void Hashcode클래스는_HashSet에서_다르게_인식()
    {
        HashSet<HashcodeHello> set = new HashSet<>();
        HashcodeHello m1 = new HashcodeHello("hello");
        HashcodeHello m2 = new HashcodeHello("hello");
        set.add(m1);
        set.add(m2);

        assertThat(set.size()).isEqualTo(2);

        // 해시코드가 같으면 -> 같은 버킷에 담김.
        // 하지만 같은 버킷 내부에서 equals로 비교 -> 다르게 인식.
    }

    @Test
    public void All클래스는_HashSet에서_똑같다고_인식()
    {
        HashSet<AllHello> set = new HashSet<>();
        AllHello m1 = new AllHello("hello");
        AllHello m2 = new AllHello("hello");
        set.add(m1);
        set.add(m2);

        assertThat(set.size()).isEqualTo(1);

        // 해시코드가 다르면 -> 다른 버킷에 담김.
        // 버킷 내부에서 -> equals로 비교 -> 똑같으므로 똑같다고 인식.
    }

    @Test
    public void Default클래스를_TreeSet에_넣어보면_어떨까()
    {
        TreeSet<DefaultHello> set = new TreeSet<>();
        DefaultHello m1 = new DefaultHello("hello");
        DefaultHello m2 = new DefaultHello("hello");

        try
        {
            set.add(m1);
            set.add(m2);
            assertThat(set.size()).isEqualTo(2);
            fail("TreeMap, TreeSet은 정의할 때 크다/작다/같다/다르다 조건을 넣어줘야 함.");
            fail("위 조건을 넣지 않으면 set.add()조차 실행되지 않음.");
            fail("오름차순/내림차순으로 넣어야 하는데, 같고 다름 조차 정의되지 않을 수는 없음.");
        }
        catch (ClassCastException e)
        {

        }
    }

    @Test
    public void Default클래스를_TreeSet에_넣어보자()
    {
        TreeSet<DefaultHello> set = new TreeSet<>(new Comparator<DefaultHello>()
        {
            @Override
            public int compare(DefaultHello o1, DefaultHello o2)
            {
                return o1.getName().compareTo(o2.getName());
            }
        });

        DefaultHello m1 = new DefaultHello("hello");
        DefaultHello m2 = new DefaultHello("hello");
        set.add(m1);
        set.add(m2);
        assertThat(set.size()).isEqualTo(1);

        //TreeSet은 Comparator 인터페이스로 비교하여 오름차순/내림차순으로 정렬해서 데이터를 넣어준다.
        //따라서 equals(), hashcode()를 정의하지 않아도 된다.
    }
}

