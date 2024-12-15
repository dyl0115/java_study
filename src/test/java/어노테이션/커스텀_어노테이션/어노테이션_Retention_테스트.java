package 어노테이션.커스텀_어노테이션;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@소스_어노테이션
@클래스_어노테이션
@런타임_어노테이션
public class 어노테이션_Retention_테스트
{
    @Test
    public void retension_테스트()
    {
        Annotation[] annotations = 어노테이션_Retention_테스트.class.getAnnotations();

        boolean found_소스_어노테이션 = false;
        boolean found_클래스_어노테이션 = false;
        boolean found_런타임_어노테이션 = false;

        for (Annotation annotation : annotations)
        {
            String annotationName = annotation.annotationType().getSimpleName();

            switch (annotationName)
            {
                case "소스_어노테이션" -> found_소스_어노테이션 = true;
                case "클래스_어노테이션" -> found_클래스_어노테이션 = true;
                case "런타임_어노테이션" -> found_런타임_어노테이션 = true;
            }
        }

        for (Annotation annotation : annotations)
        {
            log.info(annotation.annotationType().getSimpleName());
        }

        // 소스_어노테이션, 클래스_어노테이션은 모두 사라짐.
        assertThat(found_소스_어노테이션).isFalse();
        assertThat(found_클래스_어노테이션).isFalse();

        // 런타임_어노테이션 만 존재함!
        assertThat(found_런타임_어노테이션).isTrue();
    }
}

@Retention(RetentionPolicy.SOURCE)
@interface 소스_어노테이션
{
    // 컴파일러 만나기 전에 사라짐
    // 예시: @Override, @SuppressWarnings
}

@Retention(RetentionPolicy.CLASS)
@interface 클래스_어노테이션
{
    // 컴파일러 만나고, 바이트 코드 생성까지 존재
    // 예시: @Getter, @Setter
}

@Retention(RetentionPolicy.RUNTIME)
@interface 런타임_어노테이션
{
    // 컴파일러 만나고, 바이트코드 생성, 실행까지 항상 존재
    // 예시: @Test, @Entity, @Controller, @Service ... @Slf4j
}
