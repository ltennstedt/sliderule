package sliderule.core.number

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

@CompileStatic
final class LongRationalGroovyTest {
    @Test
    void positive_should_succeed() {
        assert +LongRational.ZERO === LongRational.ZERO
    }
}
