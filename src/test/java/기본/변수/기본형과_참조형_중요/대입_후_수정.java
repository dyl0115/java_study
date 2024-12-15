package 기본.변수.기본형과_참조형_중요;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class 대입_후_수정
{
    // a = b
    // a에 b가 가진 값을 저장하는 것.
    // a에 b 자체를 저장하는 것이 아니다.

    @Test
    public void 기본형_대입_후_수정()
    {
        int a = 10; // a[10]
        int b = a; // b[10]
        a = 20; // a[20] b[10]

        assertThat(a).isEqualTo(20);
        assertThat(b).isEqualTo(10);
    }

    @Test
    public void 참조형_대입_후_수정()
    {
        Data a = new Data();
        a.value = 1; // a[힙주소1] ---------------> 힙주소1[Data(1)]

        Data b = a;  // b[힙주소1] ---------------> 힙주소1[Data(1)]
        a.value = 2; // a[힙주소1] ---------------> 힙주소1[Data(2)]
        // b[힙주소1] ---------------> 힙주소1[Data(2)]

        assertThat(a == b).isTrue(); // 같은 주소값을 가짐.
        assertThat(b.value).isEqualTo(2);
    }

    @Test
    public void Integer_대입_후_수정이_아님_얘는_새로운_객체를_생성_후_할당하는것()
    {
        // 스택영역의 변수들이 힙주소를 보관                   힙영역
        Integer a = 123456; // a[힙주소1] ---------------> 힙주소1[123456]
        Integer b = a;      // b[힙주소1] ---------------> 힙주소1[123456]
        a = 654321;        // a[힙주소2] ---------------> 힙주소2[654321]

        assertThat(a).isEqualTo(654321);
        assertThat(b).isEqualTo(123456);
    }

    class Data
    {
        int value;
    }
}
