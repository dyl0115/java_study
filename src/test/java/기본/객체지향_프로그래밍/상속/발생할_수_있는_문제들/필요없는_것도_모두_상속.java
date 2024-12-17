package 기본.객체지향_프로그래밍.상속.발생할_수_있는_문제들;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.assertj.core.api.Assertions.*;

/**
 * 필요없는 메서드를 상속받아서 문제가 되는 경우가 있다.<br>
 * Vector클래스를 상속받는 Stack클래스가 좋은 예시이다.
 */
public class 필요없는_것도_모두_상속
{

    /**
     * <h1>[문제점] Stack클래스는 Vector클래스의 add()메서드를 강제로 상속받는다.</h1>
     * 이 때문에 자신의 push()메서드와 부모의 add()메서드가 충돌이난다.
     */
    @Test
    public void addAndPush()
    {
        Stack<String> stack = new Stack<>();
        stack.push("가");
        stack.push("나");
        stack.push("다");

        //NOTE push()가 아닌 add()를 사용한다.
        stack.add(0, "라");

        //NOTE peek()을 했을 때, "라"가 나올것으로 예상하지만, 예상과 다르다.
        assertThat(stack.peek()).isNotEqualTo("라");
    }
}
