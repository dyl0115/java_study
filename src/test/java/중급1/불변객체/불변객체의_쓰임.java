package 중급1.불변객체;

import lombok.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <h2>[정의] 불변(Immutable) 객체 </h2>
 * <ul>
 *     <li>처음 만든 객체에서 데이터를 수정할 수 없음</li>
 *     <li>모든 필드에 final</li>
 *     <li><u>객체의 원자성을 확실하게 보장함. -> 멀티스레드 환경에서 안전하다.</u></li>
 *     <li><u>객체의 원자성을 확실하게 보장함. -> 캐싱 및 공유자원 환경에서도 안전하다.</u></li>
 * </ul>
 * <h2>[정의] 가변(Mutable) 객체</h2>
 * <ul>
 *     <li>가변객체는 한 객체 및 쓰레드에서 혼자 사용하는 상황에서는 문제가 발생하지 않는다.</li>
 *     <li>여러 클래스에서 동시에 사용하거나, 여러 쓰레드에서 함께 사용할 때 문제가 터진다.</li>
 *     <li><u>한 객체에서 사용중인데, 다른 곳에서 수정을 할 경우 문제가 터지는 것이다.</u></li>
 *     <li><u>불변객체는 수정자체를 막아서 이러한 문제를 원천봉쇄해준다.</u></li>
 * </ul>
 */
public class 불변객체의_쓰임
{
    @AllArgsConstructor
    @Data
    class MutableData
    {
        int value;
    }

    /**
     * <h2>[가변객체는] b를 변경했는데, 의도치 않게 a까지 변경될 수 있음</h2>
     * <p>이 특징때문에 굉장히 위험한 상황이 올 수 있다.</p>
     * <ul>
     *     <li>멀티스레드 환경에서의 문제</li>
     *     <li>캐시 및 공유자원에서의 문제</li>
     * </ul>
     * <p>따라서 여러 곳에서 동시에 접근하는 객체라면 불변객체를 사용하는 것이 좋은 선택일 수 있다.</p>
     */
    @Test
    public void 가변객체는_의도치_않게_동작함()
    {
        MutableData a = new MutableData(10);
        MutableData b = a;

        //NOTE b를 변경했으나, a까지 변경되어 버렸다.
        b.value = 20;
        assertThat(a.value).isEqualTo(20);
    }

    int distance = 0;
    Object location;

    /**
     * <h2>[가변객체의 문제1] 가변객체 멀티스레드 환경에서의 문제</h2>
     * <p>게임 캐릭터와 세이브포인트(0,0) 사이의 거리를 구한다고 하자.</p>
     * <ul>
     *     <li>스레드1는 (1,1)좌표를 (10,10)으로 갱신함</li>
     *     <li>스레드2은 (x,y)의 거리 (x + y)를 계산함</li>
     *     <li>스레드1이 좌표를 갱신하는 중간시점 (1,10)에 스레드2가 거리를 구하면 문제가 발생함</li>
     * </ul>
     * <p>이를 불변 객체를 활용해서 해결할 수 있다.</p>
     */
    @Test
    public void 멀티스레드_환경에서의_문제() throws InterruptedException
    {
        @AllArgsConstructor
        class MutableLocation
        {
            int x;
            int y;
        }

        MutableLocation location = new MutableLocation(1, 1);

        //NOTE thread1는 (x,y)의 값을 (10,10)으로 갱신함
        //NOTE y를 가져오는데 시간이 오래걸린다고 가정
        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                location.x = 10;
                //NOTE y값을 갱신하는데 시간이 오래 걸림.
                try
                {
                    Thread.sleep(2000);
                    location.y = 10;
                }
                catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });

        //NOTE thread2은 (x,y)를 보고 거리(x+y)를 수행함
        Thread thread2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                int currentX = location.x;
                int currentY = location.y;
                distance = (currentX + currentY);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        //PROBLEM distance가 2, 혹은 20이 아닌 중간 결과 11이 리턴됨.
        System.out.println(distance);
    }

    /**
     * <h2>[가변객체 문제해결1] 가변객체를 불변객체로 수정</h2>
     * <p>불변객체를 사용하여 중간상태를 완전히 제거해서 문제를 해결한다.</p>
     * <ul>
     *     <li>불변객체를 사용함으로서 다른 스레드는 항상 완전한 상태(Old Location, New Location) 둘 중 하나만 볼 수 있음.</li>
     *     <li>위치 이동은 항상 <u><b>원자적</b></u>으로 이루어지게 됨</li>
     * </ul>
     */
    @Test
    public void 불변객체를_활용한_멀티스레드_정합성문제_해결() throws InterruptedException
    {
        @AllArgsConstructor
        class ImmutableLocation
        {
            final int x;
            final int y;
        }

        location = new ImmutableLocation(1, 1);

        //NOTE thread1는 (x,y)의 값을 (10,10)으로 갱신함
        //NOTE 불변객체를 사용해서 중간과정이 없어짐.
        Thread thread1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    location = new ImmutableLocation(10, 10);
                    Thread.sleep(2000);
                }
                catch (InterruptedException e)
                {
                    throw new RuntimeException(e);
                }
            }
        });

        //NOTE thread2은 (x,y)를 보고 거리(x+y)를 수행함
        Thread thread2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                ImmutableLocation loc = (ImmutableLocation) location;
                distance = (loc.x + loc.y);
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        //NOTE distance가 정합성이 보장되는 2혹은 20이 리턴됨
        System.out.println(distance);
    }

    /**
     * <h2>[가변객체 문제2] 캐시 및 공유자원에서의 문제</h2>
     * <p>가변객체는 의도치 않게 <u><b>외부에서 해당 객체가 변경될 여지</b></u>가 있다는 것이다.</p>
     * <p>아래 문제 발생 과정을 이해해 보자.</p>
     * <ol>
     *     <li>객체1에서 가변객체 key를 생성</li>
     *     <li>객체1에서 이를 HashMap에 (key, value)로 저장</li>
     *     <li>외부 로직에서 해당 키를 수정함. </li>
     *     <li>객체1에서 해당 키로 캐시에서 조회를 해보지만, 조회가 안됨.</li>
     * </ol>
     */
    @Test
    public void 캐시_및_공유자원에서의_문제()
    {
        @AllArgsConstructor
        @EqualsAndHashCode
        class MutableUser
        {
            String id;
            String password;
        }

        HashMap<MutableUser, Integer> moneyCache = new HashMap<>();

        MutableUser user1 = new MutableUser("id1", "password1");
        moneyCache.put(user1, 10000);

        //NOTE MutableKey는 가변객체라서 다른 외부로직에서 수정될 여지가 있고, 수정되었음.
        user1.id = "id100";

        //NOTE 개발자는 다른 외부로직에서 해당 key가 수정된 줄 모르고 그대로 사용.
        Integer money1 = moneyCache.get(user1);

        //PROBLEM 의도치않은 오동작 null이 반환됨
        assertThat(money1).isNull();
    }

    /**
     * <h2>[가변객체 문제해결2] 캐시 및 공유자원에서의 문제</h2>
     * <p>문제의 원인은 외부에서 의도치 않게 key가 변경되어 HashSet에서 조회가 되지 않는것.</p>
     * <p>key가 외부에서 변경되지 않도록 불변객체로 원천봉쇄한다.</p>
     */
    @Test
    public void 캐시_및_공유자원에서의_문제_불변객체로_원인제거()
    {
        @Value
        @AllArgsConstructor
        @Getter
        class ImmutableUser
        {
            final String id;
            final String password;
        }

        HashMap<ImmutableUser, Integer> moneyCache = new HashMap<>();
        ImmutableUser user1 = new ImmutableUser("user1", "password1");
        moneyCache.put(user1, 10000);

        //NOTE 외부 객체에서 해당 객체를 변경할 수 없음. 
        //NOTE 외부 객체로부터 해당 객체를 보호함.
        //user1.id = "user100";

        //NOTE 개발자의 의도대로 정상작동함.
        Integer money = moneyCache.get(user1);
        assertThat(money).isEqualTo(10000);
    }
}
