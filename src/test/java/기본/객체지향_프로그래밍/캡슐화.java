package 기본.객체지향_프로그래밍;

import org.junit.jupiter.api.Nested;

public class 캡슐화
{
    // 캡슐화는 private, public, protected, default로 구현한다.
    // 클라이언트 코드에게 꼭 필요한 메서드만 공개한다.

    // 1. 데이터 자체는 숨겨라.
    // 2. 내부에서만 사용하는 메서드는 숨겨라.

    @Nested
    class 캡슐화로_데이터를_보호한다
    {
        public void 비캡슐화_문제()
        {
            // 데이터에 직접 접근하게 되면,
            // 데이터를 다루는 로직을 뚫고 엉망이 된다.
            비캡슐화_Player player = new 비캡슐화_Player();
            player.hp = -1000;
        }

        public void 캡슐화로_데이터를_보호함()
        {
            캡슐화_Player player = new 캡슐화_Player();
            // player.hp = -1000; 접근이 안됨.
            player.damage(1000); // 데이터를 보호할 수 있음.
        }
    }

    class 비캡슐화_Player
    {
        public int hp = 10;
    }

    class 캡슐화_Player
    {
        private int hp = 10;

        public void damage(int damage)
        {
            if (hp >= damage) hp -= damage;
        }

        private void sendServer(int hp)
        {
            // ...
        }
    }
}


