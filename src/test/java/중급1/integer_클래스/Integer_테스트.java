package 중급1.integer_클래스;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class Integer_테스트
{
    @Test
    public void test()
    {

        HashMap<String, Hello> map1 = new HashMap<>();
        map1.put("key1", new Hello(123456));
        Hello value1 = map1.get("key1");
        value1.value += 2;

        System.out.println(map1.get("key1").value);

        HashMap<String, Bye> map2 = new HashMap<>();
        map2.put("key1", new Bye(123456));
        Bye value2 = map2.get("key1");
        value2.value += 2;

        System.out.println(map2.get("key1").value);
    }

    @AllArgsConstructor
    @Getter
    class Hello
    {
        int value;
    }

    @AllArgsConstructor
    @Getter
    class Bye
    {
        Integer value;
    }
}
