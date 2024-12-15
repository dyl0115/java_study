package 어노테이션;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@타입_어노테이션
public class 어노테이션_Target_테스트
{
    @필드_어노테이션
    private String name;
    @필드_어노테이션
    private static Integer age;

    @생성자_어노테이션
    public 어노테이션_Target_테스트(@매개변수_어노테이션 String name)
    {
        this.name = name;
    }

    @메서드_어노테이션
    public void sayHello(@매개변수_어노테이션 String name)
    {
        @로컬_어노테이션
        String message = "Hello";

        System.out.println(message + " " + name);
    }
}

@Target(ElementType.PACKAGE)
@interface 패키지_어노테이션
{

}

@Target(ElementType.TYPE)
@interface 타입_어노테이션
{
}

@Target(ElementType.CONSTRUCTOR)
@interface 생성자_어노테이션
{

}

@Target(ElementType.FIELD)
@interface 필드_어노테이션
{
}

@Target(ElementType.METHOD)
@interface 메서드_어노테이션
{
}

@Target(ElementType.LOCAL_VARIABLE)
@interface 로컬_어노테이션
{
}

@Target(ElementType.PARAMETER)
@interface 매개변수_어노테이션
{

}



