package 고급1.멀티스레드;

import org.junit.jupiter.api.Test;

public class 멀티스레드_동시성_문제
{
    /**
     * <h2>[문제1] 멀티스레드 환경에서의 문제</h2>
     */
    class BankAccount
    {
        int totalMoney;

        public void addMoney(int money)
        {
            int currentTotalMoney = totalMoney;

            try
            {
                Thread.sleep(1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            totalMoney = currentTotalMoney + money;
        }

        public int getTotalMoney()
        {
            return totalMoney;
        }
    }

    @Test
    public void 멀티스레드_환경의_가변객체_문제() throws InterruptedException
    {
        BankAccount myAccount = new BankAccount();

        //NOTE 10개의 스레드가 병행적으로 각각 100번씩 10원 입금.
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++)
        {
            threads[i] = new Thread(() ->
            {
                for (int i1 = 0; i1 < 100; i1++)
                {
                    myAccount.addMoney(10);
                }
            });
            threads[i].start();
        }

        //NOTE 모든 스레드가 완료될 때까지 대기.
        for (Thread thread : threads)
        {
            thread.join();
        }

        //NOTE 예상 총 금액은 10 * 10 * 100 = 10,000원
        //NOTE 하지만 실제 금액은 1040원 정도
        System.out.println("예상 총 금액: " + 10000);
        System.out.println("실제 총 금액: " + myAccount.totalMoney);
    }
}
