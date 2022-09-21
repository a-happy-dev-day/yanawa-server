package fashionable.simba.yanawaserver.global.context;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class SecurityContextHolderTest {

    @Test
    @DisplayName("기본 생성자를 생성하려 하면 예외가 발생합니다.")
    void create_exception() throws NoSuchMethodException {
        Constructor<SecurityContextHolder> constructor = SecurityContextHolder.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Assertions.assertThatThrownBy(constructor::newInstance).isInstanceOf(InvocationTargetException.class);
    }

    @Test
    @DisplayName("컨텍스트를 등록하려 할 때, 컨텍스트가 null이면 예외가 발생합니다.")
    void setContext_context_is_null() {
        Assertions.assertThatThrownBy(
            () -> SecurityContextHolder.setContext(null)
        ).isInstanceOf(IllegalStateException.class);
    }
}
