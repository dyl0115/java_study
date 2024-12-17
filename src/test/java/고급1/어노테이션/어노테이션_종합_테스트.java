package 고급1.어노테이션;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.annotation.*;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@사람(
        name = "david",
        age = 20,
        hobbies = {"게임", "혼술", "산책"},
        grade = Grade.BRONZE,
        scores = {
                @성적(subject = "Java", score = 10),
                @성적(subject = "Spring", score = 30),
                @성적(subject = "MySQL", score = 20)}
)
public class 어노테이션_종합_테스트
{
    @Test
    public void sampleMethod()
    {
        어노테이션_종합_테스트 test = new 어노테이션_종합_테스트();
        사람 annotation = test.getClass().getAnnotation(사람.class);

        assertThat(annotation.name()).isEqualTo("david");
        assertThat(annotation.age()).isEqualTo(20);
        assertThat(annotation.grade()).isEqualTo(Grade.BRONZE);

        성적[] scores = annotation.scores();
        assertThat(scores.length).isEqualTo(3);

        for (성적 score : scores)
        {
            log.info(score.subject());
            log.info(String.format("%d", score.score()));
        }
    }
}

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface 사람
{
    String name();

    int age() default 1;

    String[] hobbies() default {};

    Grade grade() default Grade.BRONZE;

    성적[] scores() default {};

    // 사람[] friends() default {}; 재귀는 안 됨.
}

@Retention(RetentionPolicy.RUNTIME)
@interface 성적
{
    String subject();

    int score();
}

@AllArgsConstructor
@Getter
enum Grade
{
    GOLD(2, null),
    SILVER(1, GOLD),
    BRONZE(0, SILVER);

    private int value;
    private Grade next;
}