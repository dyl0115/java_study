package 기본.변수.배열;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class 매서드_매개변수
{
    @Test
    public void 매서드_매개변수()
    {
        // arr는 {1,2,3,4,5}의 주소를 저장하는 참조형 변수.
        // int[] 는 결국 참조형 변수다.
        int[] arr = {1, 2, 3, 4, 5};
        changeArray(arr);
        assertThat(arr[0]).isEqualTo(100);
    }

    public void changeArray(int[] arr)
    {
        arr[0] = 100;
    }
}

