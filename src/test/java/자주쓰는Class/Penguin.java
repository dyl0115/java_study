package 자주쓰는Class;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Penguin extends Animal implements Swimmable
{
    @Override
    public void swim()
    {
        log.info("펭귄이 수영을 한다~");
    }
}

