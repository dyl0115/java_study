package 기본.객체지향_프로그래밍.상속.발생할_수_있는_문제들;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class 다중상속_불가능
{
    /**
     * 문서를 Print하는 Printer클래스
     */
    class Printer
    {
        public void print()
        {
            log.info("문서를 Print 합니다.");
        }
    }

    /**
     * 문서를 Scan하는 Scanner클래스
     */
    class Scanner
    {
        public void scan()
        {
            log.info("문서를 Scan 합니다.");
        }
    }

    /**
     * 문서를 Print, Scan하는 Device클래스?<br>
     * <h3>
     * <u>하지만 다중상속이 불가능함</u>
     * </h3>
     */
    class Device extends Printer //, Scanner
    {
    }
}
