package 기본.객체지향_프로그래밍.상속.발생할_수_있는_문제들;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class 높은_결합도
{
    /**
     * <h1>[문제점] 부모 클래스 코드가 변하면 자식코드의 변경이 강제된다.</h1>
     * Animal을 상속하는 자식 클래스 Human<br>
     *
     *  <ul>
     *       <li>int health</li>
     *      <li>boolean breath()</li>
     *     <li>void dead()</li>
     * </ul>를 직접 사용한다.
     */
    class Animal
    {
        int health = 3;

        public boolean breath()
        {
            if (health > 0)
            {
                log.info("호흡을 한다.");
                return true;
            }
            return false;
        }

        public void dead()
        {
            log.info("죽었다.");
        }
    }

    class Human extends Animal
    {
        int money = 0;

        public void work()
        {
            while (breath())
            {
                log.info("일을 한다.");
                money++;
                health--;
            }
            dead();
        }
    }
}
