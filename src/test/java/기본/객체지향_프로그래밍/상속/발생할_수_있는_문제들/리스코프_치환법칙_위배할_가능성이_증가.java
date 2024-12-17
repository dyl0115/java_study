package 기본.객체지향_프로그래밍.상속.발생할_수_있는_문제들;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

/**
 * <b><u>NOTE 리스코프 치환 법칙</u><br>
 * 부모 타입의 객체를 <br>
 * 자식 타입의 객체로 갈아끼워도 <br>
 * 프로그램이 의도대로 작동해야 한다. <br>
 * </b>
 *
 * <h1>즉, 자식 클래스는 부모 클래스의 뜻을 거스르면 안된다!</h1>
 * <h1>자식이 부모의 뜻을 거스르게 되면 프로그램의 다형성이 깨지게 된다.</h1>
 * <h1><u>하지만 상속을 사용할 경우 자식이 부모의 뜻을 거스르게 될 가능성이 높아진다.</u></h1>
 */
public class 리스코프_치환법칙_위배할_가능성이_증가
{
    /**
     * 직사각형의 가로, 세로를 정하는 Rectangle 클래스<br>
     * <ul>
     *     <li>setWidth(int n)으로 가로를 지정</li>
     *     <li>setHeight(int n)으로 세로를 지정</li>
     *     <li><u>setWidth(int n)과 setHeight(int n)는 서로 독립적으로 작동한다.</u></li>
     * </ul>
     */
    class Rectangle
    {
        protected int width = 1;
        protected int height = 1;

        public void setWidth(int width)
        {
            this.width = width;
        }

        public void setHeight(int height)
        {
            this.height = height;
        }
    }

    /**
     * 정사각형의 가로, 세로를 정하는 Square 클래스<br>
     * <ul>
     *     <li>setWidth(int width) => 가로, 세로 모두 width로 지정</li>
     *     <li>setHeight(int height) => 가로, 세로 모두 height로 지정</li>
     *     <li><u>자식 클래스의 setWidth(), setHeight()는 서로 독립적으로 작동한다는 부모의 뜻을 거스른다.</u></li>
     * </ul>
     */
    class Square extends Rectangle
    {
        public void setWidth(int width)
        {
            this.width = width;
            this.height = width;
        }

        public void setHeight(int height)
        {
            this.width = height;
            this.height = height;
        }
    }

    @Test
    public void 리스코프_치환법칙_위배_테스트()
    {
        Rectangle nemo = new Square();
        assertThat(nemo.width).isEqualTo(1);
        assertThat(nemo.height).isEqualTo(1);

        //NOTE: 사용자는 Width만 변경되길 기대하지만, Height도 변경되어 버림.
        //NOTE: 부모객체를 자식객체로 변경하는 순간 문제가 발생함.
        nemo.setWidth(10);
        assertThat(nemo.height).isNotEqualTo(1);
    }
}
