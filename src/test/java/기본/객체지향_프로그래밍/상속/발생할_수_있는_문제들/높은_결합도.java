package 기본.객체지향_프로그래밍.상속.발생할_수_있는_문제들;

import lombok.extern.slf4j.Slf4j;

/**
 * <h2>[높은 결합도란?] </h2>
 * <p>한 객체가 다른 객체의 코드를 직접 알고있는 것</p>
 * <p>한 객체가 다른 객체의 내부 구현에 직접 의존하게 되는 경우 발생한다.</p>
 */
@Slf4j
public class 높은_결합도
{
    /**
     * <h2>[문제점1] 서버 클래스 코드 변경이 어렵다.</h2>
     * <p>서버 클래스의 필드, 메서드가 변경되면 클라이언트 클래스도 모두 수정해야한다.<br>
     * 이 때문에 부모 클래스를 함부로 수정할 수 없다.
     * </p>
     */
    class 부모_클래스_코드변경이_어렵다
    {
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

    /**
     * <h2>[문제점2] 단위테스트가 어렵다.</h2>
     * <p>의존성이 강하면 객체들을 분리하기 어렵고, 각각의 객체별 단위테스트가 불가능하다.</p>
     */
    class 단위_테스트가_어려움
    {

    }

    /**
     * <h2>[문제점3] 재사용성이 떨어진다.</h2>
     * <p>객체를 다른 곳에 재상용하기 어렵다.</p>
     */
    class 재사용이_어려움
    {

    }
}
