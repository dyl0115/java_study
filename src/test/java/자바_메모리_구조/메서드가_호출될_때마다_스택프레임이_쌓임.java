package 자바_메모리_구조;

import org.junit.jupiter.api.Test;

public class 메서드가_호출될_때마다_스택프레임이_쌓임
{
    @Test
    public void 테스트()
    {
        method1();
        System.out.println("method1() 종료");
        printMethodStack();
    }

    public void method1()
    {
        System.out.println("method1() 시작");
        printMethodStack();
        method2();
        System.out.println("method2() 종료");
        printMethodStack();
    }

    public void method2()
    {
        System.out.println("method2() 시작");
        printMethodStack();
        method3();
        System.out.println("method3() 종료");
        printMethodStack();
    }

    public void method3()
    {
        System.out.println("method3() 시작");
        printMethodStack();
        method4();
        System.out.println("method4() 종료");
        printMethodStack();
//        throw new IllegalStateException();
    }

    public void method4()
    {
        System.out.println("method4() 시작");
        printMethodStack();
    }

    // 현재 쓰레드의 메서드 스택을 출력해준다.
    public void printMethodStack()
    {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        for (StackTraceElement e : stackTrace)
        {
            String className = e.getClassName();
            String methodName = e.getMethodName();

            if (!className.contains("junit") &&
                    !className.contains("java") &&
                    !className.contains("gradle") &&
                    !className.contains("jdk"))
            {
                System.out.println("    [method name] : " + methodName);
            }
        }
    }
}