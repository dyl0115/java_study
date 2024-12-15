package 어노테이션;

import org.junit.jupiter.api.Test;

import java.lang.annotation.*;

@중요(value = "어노테이션 Documented_테스트!")
public class 어노테이션_Documented_테스트
{
    @Test
    public void Documented_테스트()
    {
        // javadoc -d docs src/main/java/어노테이션/*.java
        // 이렇게 생성된 API 문서는 docs 디렉토리에서 확인할 수 있다.ㅣ
    }
}

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@interface 중요
{
    String value() default "";

    String description() default "";
}
