package 중급1.string클래스;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StringBuilder클래스
{
    /**
     * <h2>[개념] 단순한 String 덧셈은 편하게~</h2>
     * <p>String은 불변객체, 원래 매 연산마다 새로운 String객체를 생성함</p>
     * <p>하지만 컴파일러가 자동으로 StringBuilder코드로 변환해줌.<br> 덕분에 속도가 빨라진다.</p>
     * <ul>
     *     <li>s = s1 + s2 + s3
     *     <li>변환 &rArr;  s = new StringBuilder(s1).append(s2).append(s3);</li>
     * </ul>
     */
    @Test
    public void 간단한_덧셈은_괜찮음()
    {
        String s1 = "hello";
        String s2 = " david";
        String s3 = " marry";
        String s4 = " alex";

        //NOTE 컴파일러가 StringBuilder를 사용하는 코드로 변환
        String s = s1 + s2 + s3 + s4;
        assertThat(s).isEqualTo("hello david marry alex");
    }

    /**
     * <h2>[개념] 하지만, 루프 안에서는 StringBuilder를 사용해라! </h2>
     * <ul>
     * <li>--변환전--<br>
     *     for(int i = 0; i < 100; i++)<br>
     * {<br>
     *  &nbsp;&nbsp; s += "hello";<br>
     * }<br>
     * </li>
     * <li>--변환 후--<br>
     * for(int i = 0; i < 100; i++)<br>
     *       {<br>
     *       &nbsp;&nbsp; s = new StringBuilder(s).append("hello").toString();<br>
     *       }<br>
     * </li>
     * </ul>
     * <p>반복문이 쓰이는 상황에서는 컴파일러가 변환해줘도 느리다.<br>따라서 직접 StringBuilder코드를 작성해야 한다.</p>
     */
    @Test
    public void 반복문에서는_직접_StringBuilder를_사용해야_함()
    {
        String s = "hello";
        Long time1, time2, time3;
        Long start;
        Long end;

        //NOTE 직접 + 연산
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)
        {
            s += " hello";
        }
        end = System.currentTimeMillis();
        time1 = (end - start);

        //NOTE String.concat()
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++)
        {
            s = s.concat(" hello");
        }
        end = System.currentTimeMillis();
        time2 = (end - start);

        //NOTE StringBuilder() 테스트
        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < 100000; i++)
        {
            sb.append(" hello");
        }
        end = System.currentTimeMillis();
        time3 = (end - start);

        //NOTE 결과 time1=5749, time2=21286, time3 = 3
        //NOTE StringBuilder가 압도적으로 빠르다.
        System.out.println("time1 " + time1);
        System.out.println("time2 " + time2);
        System.out.println("time3 " + time3);
    }

    /**
     * <h2>[단점] 멀티쓰레드에서는 StringBuffer를 써야한다.</h2>
     * <p>StringBuilder는 가변객체이며, 동시에 동기화를 지원하지 않는다.<br>
     * 때문에 여러군데에서 동시에 접근하면 문제가 발생한다.<br>
     * 이때는 StringBuffer를 써야한다.
     * </p>
     */
    @Test
    public void 멀티쓰레드_환경에서는_StringBulider를_쓰면_안된다() throws InterruptedException
    {
        StringBuilder sb = new StringBuilder();

        Thread t1 = new Thread(() ->
        {
            for (int i = 0; i < 10000; i++)
            {
                sb.append("1");
            }
        });

        Thread t2 = new Thread(() ->
        {
            for (int i = 0; i < 10000; i++)
            {
                sb.append("2");
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // 예상 길이: 20000
        // 실제 길이는 이보다 작을 것임
        System.out.println("Length: " + sb.length());
        assertNotEquals(20000, sb.length());
    }

    @Test
    public void 멀티쓰레드_환경에서는_StringBuffer를_쓴다() throws InterruptedException
    {
        StringBuffer sb = new StringBuffer();

        Thread t1 = new Thread(() ->
        {
            for (int i = 0; i < 10000; i++)
            {
                sb.append("1");
            }
        });

        Thread t2 = new Thread(() ->
        {
            for (int i = 0; i < 10000; i++)
            {
                sb.append("2");
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // 예상 길이: 20000
        // 실제 길이도 동일함
        System.out.println("Length: " + sb.length());
        assertEquals(20000, sb.length());
    }
}
