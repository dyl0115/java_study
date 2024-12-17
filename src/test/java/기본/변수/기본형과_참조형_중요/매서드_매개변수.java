package 기본.변수.기본형과_참조형_중요;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class 매서드_매개변수
{
    // 자바는 항상 변수 속 값을 복사해서 저장한다.
    public void changePrimitive(int x)
    {
        x = 20;
    }

    public void changeReference(Data data)
    {
        data.value = 20;
    }

    public void changeInteger(Integer x)
    {
        x = 20;
    }

    @Test
    public void 기본형_매개변수_테스트()
    {
        int x = 10;
        changePrimitive(x);

        assertThat(x).isEqualTo(10);
    }

    @Test
    public void 참조형_매개변수_테스트()
    {
        Data data = new Data();
        data.value = 10;
        changeReference(data);

        assertThat(data.value).isNotEqualTo(10);
    }

    @Test
    public void Integer_매개변수_테스트()
    {
        // 뭐임???? Integer도 참조변수잖아???
        Integer x = 123456;
        changeInteger(x);
        assertThat(x).isEqualTo(123456);
    }

    class Data
    {
        int value;
    }
}
