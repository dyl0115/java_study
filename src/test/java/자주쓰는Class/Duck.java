package 자주쓰는Class;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Duck extends Animal implements Flyable
{
    @Override
    public void fly()
    {
        log.info("오리가 하늘을 난다~");
    }
}
