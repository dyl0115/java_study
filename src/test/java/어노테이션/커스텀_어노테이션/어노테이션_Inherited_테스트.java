package 어노테이션.커스텀_어노테이션;

import org.junit.jupiter.api.Test;

import java.lang.annotation.*;

import static org.assertj.core.api.Assertions.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface 상속_어노테이션
{
}

@Retention(RetentionPolicy.RUNTIME)
@interface 비상속_어노테이션
{
}

public class 어노테이션_Inherited_테스트
{
    @상속_어노테이션
    class 상속_부모
    {
    }

    @비상속_어노테이션
    class 비상속_부모
    {
    }

    class 상속_자식 extends 상속_부모
    {
    }

    class 비상속_자식 extends 비상속_부모
    {
    }

    @Test
    public void 어노테이션_Inherited_테스트()
    {
        상속_자식 상속_자식 = new 상속_자식();
        비상속_자식 비상속_자식 = new 비상속_자식();

        // 상속_어노테이션은 자식에게로 이동 O
        assertThat(상속_자식.getClass().isAnnotationPresent(상속_어노테이션.class))
                .isTrue();

        // 비상속_어노테이션은 자식에게 이동 X
        assertThat(비상속_자식.getClass().isAnnotationPresent(비상속_어노테이션.class))
                .isFalse();
    }
}


