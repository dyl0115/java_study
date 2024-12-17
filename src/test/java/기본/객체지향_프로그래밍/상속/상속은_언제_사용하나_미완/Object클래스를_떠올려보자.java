package 기본.객체지향_프로그래밍.상속.상속은_언제_사용하나_미완;

/**
 * 이 부분은 내 주관적인 생각임<br>
 * 사람들이 상속을 쓰지 말라는데, 가장 중요한 클래스인 Object는 모든 클래스들이 상속하도록 구현됨<br>
 * Object클래스는 모든 클래스들의 부모클래스인데 문제가 발생하지 않는다.
 */
public class Object클래스를_떠올려보자
{
    /**
     * <h1>[고려1] 자식클래스가 부모클래스의 모든 메서드를 가지도록 강제할 때</h1>
     * <ul>
     *     <li>서브클래스가 부모의 모든 필드, 메서드를 가지도록 강제할 때 좋음.</li>
     *     <li>is - a 관계</li>
     *     <li>하지만 이런 경우가 굉장히 들물다고 함.</li>
     * </ul>
     */
    abstract class Game
    {
        public abstract void initGame();

        public abstract void playGame();

        public abstract void endGame();
    }

    class FootballGame extends Game
    {
        @Override
        public void initGame()
        {
        }

        @Override
        public void playGame()
        {
        }

        @Override
        public void endGame()
        {

        }
    }

    /**
     * <h1>[고려2] 프레임워크의 핵심동장 흐름을 제어할 때</h1>
     */
    class MyServlet extends HttpServlet
    {

    }

    class HttpServlet
    {


    }
}
