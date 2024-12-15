package 어노테이션.자주쓰는_어노테이션;

import org.junit.jupiter.api.Test;

public class 자주쓰는_어노테이션
{
    @Test
    @SuppressWarnings("deprecation")
    public void test()
    {
        자식 자식 = new 자식();
        자식.숨쉬기();

        // Deprecated Method;
        자식.떼쓰기();
    }
}

class 부모
{
    protected void 숨쉬기()
    {
    }
}

class 자식 extends 부모
{
    @Override
    protected void 숨쉬기()
    {
        super.숨쉬기();
    }

    @Deprecated
    public void 떼쓰기()
    {

    }
}
