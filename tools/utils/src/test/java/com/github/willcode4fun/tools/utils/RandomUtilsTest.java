package com.github.willcode4fun.tools.utils;

import lombok.extern.slf4j.Slf4j;
import org.fest.assertions.Assertions;
import org.junit.Test;

/**
 * Created by DECOSTT on 25/09/2017.
 */
@Slf4j
public class RandomUtilsTest {

    @Test
    public void should_create_random_positions(){
        RandomUtils.sphericCoordsStream().limit(50).forEach(s -> {
            log.debug(" coords : {}",s);
            Assertions.assertThat(s.phy)
                    .isGreaterThan(-Math.PI)
                    .isLessThan(Math.PI);
        });
    }
}
