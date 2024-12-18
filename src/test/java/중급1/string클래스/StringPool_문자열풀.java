package 중급1.string클래스;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringPool_문자열풀
{
    /**
     * <h2>[개념] String str = "hello"</h2>
     * <p>이 코드는 <u>문자열풀</u>을 사용한다. <br>
     * 문자열풀은 ("문자열의 해시값" - "참조객체의 주소")를 보관한다.
     * </p>
     * <ol>
     *     <li>문자열풀(HashMap)에 hash("hello")가 존재하는지 확인한다.</li>
     *     <li>만약 hash("hello")가 존재한다면 참조주소를 반환</li>
     *     <li>만약 hash("hello")가 존재하지 않으면 새로운 String객체를 생성하고, (hash("hello"), 참조주소값)을 저장 후, 주소를 반환</li>
     * </ol>
     * <p>따라서 다음과 같이 작동한다.</p>
     * <ul>
     *     <li>s1.equals(s2) 참 (동등성 O)</li>
     *     <li>s1==s2 참 (동일성 O)</li>
     * </ul>
     */
    @Test
    public void String_는_테스트()
    {
        String s1 = "hello";
        String s2 = "hello";

        //NOTE 동등비교는 당연히 같다.
        assertThat(s1.equals(s2)).isTrue();

        //NOTE
        // 동일비교는 문자열풀을 사용하기 때문에 주소도 동일하다.
        // intern()을 통해 문자열 풀의 참조를 얻음
        assertThat(s1 == s2).isTrue();
    }

    /**
     * <h2>[개념] String str = new String("hello")는 문자열풀을 사용하지 않는다.</h2>
     * <p>문자열풀을 전혀 이용하지 않고, 무조건 새로운 객체를 생성한다.</p>
     * <p>따라서 다음과 같이 동작한다.</p>
     * <ul>
     *     <li>s1.equals(s2) 참 (동등성 O)</li>
     *     <li>s1 == s2 거짓 (동일성 X)</li>
     * </ul>
     */
    @Test
    public void String_newString테스트()
    {
        String s1 = new String("hello");
        String s2 = new String("hello");

        assertThat(s1).isEqualTo(s2);
        assertThat(s1 == s2).isFalse();
    }
}
