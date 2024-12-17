package 기본.절차지향_프로그래밍;

import org.junit.jupiter.api.Nested;

public class 절차지향_프로그래밍의_단점
{
    // 절차지향_프로그래밍 -> 데이터와 이를 다루는 메서드가 따로따로.
    // 객체지향_프로그래밍 -> 데이터와 이를 다루는 메서드가 하나의 "객체" 속에 존재.
    // 재사용성, 유지보수의 관점에서는 객체지향_프로그래밍이 훨씬 유리함.

    @Nested
    class 재사용성_클라이언트_코드에서_매번_새로작성_해야함
    {
        // 해당 기능을 사용하는 클라이언트코드에서 매번
        public void game1()
        {
            int level = 1;
            int hp = 10;
            hp--;
            hp++;
            hp++;
            hp--;
            level++;
        }

        public void game2()
        {
            int level = 1;
            int hp = 10;
            level++;
            hp--;
            hp--;
        }

        public void game3()
        {
            int level = 1;
            int hp = 10;
            hp++;
        }

        //...

        public void game1000()
        {
            int level = 1;
            int hp = 10;
            hp++;
            hp++;
        }
    }

    @Nested
    class 유지보수_수정시_일일이_모두_찾아서_수정해야함
    {
        // 게임의 정책이 변경됨.
        // hp가 10 -> 100으로 변경됨.
        // 모든 클라이언트 코드 1000개를 찾아가서 하나하나 수정해야 함

        public void game1()
        {
            int level = 1;
            int hp = 100;
        }

        //...

        public void game1000()
        {
            int level = 1;
            int hp = 100;
        }
    }
}