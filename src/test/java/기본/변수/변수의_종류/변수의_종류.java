package 기본.변수.변수의_종류;

public class 변수의_종류
{
    // 필드변수
    public static int 클래스_변수; // JVM의 클래스로더가 클래스 로딩시, 메서드 영역에 저장,
    public int 인스턴스_변수; // 힙영역에 저장

    public static void 클래스_메서드(int 매개변수)
    {
        int 지역변수; // 스택 영역의 스택프레임 내부에 저장
    }

    public void 인스턴스_메서드(int 매개변수)
    {
        int 지역_변수; // 스택영역의 스텍프레임 내부에 저장
    }
}
