package 오브젝트_클래스;

import org.junit.jupiter.api.Test;
import SampleClass.*;

import static org.assertj.core.api.Assertions.*;

public class Object는_모든_클래스들의_부모
{
    @Test
    public void 각_클래스들의_상속관계_테스트()
    {
        Animal a1 = new Animal();
        assertThat(a1 instanceof Object).isTrue();
        assertThat(a1 instanceof Animal).isTrue();

        Duck d1 = new Duck();
        assertThat(d1 instanceof Object).isTrue();
        assertThat(d1 instanceof Duck).isTrue();
        assertThat(d1 instanceof Flyable).isTrue();
        assertThat(d1 instanceof Swimmable).isFalse();

        Penguin p1 = new Penguin();
        assertThat(p1 instanceof Object).isTrue();
        assertThat(p1 instanceof Penguin).isTrue();
        assertThat(p1 instanceof Flyable).isFalse();
        assertThat(p1 instanceof Swimmable).isTrue();
    }
}

