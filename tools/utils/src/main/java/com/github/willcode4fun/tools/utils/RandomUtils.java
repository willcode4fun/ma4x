package com.github.willcode4fun.tools.utils;

import static com.github.willcode4fun.tools.utils.MathUtils.*;

import java.util.PrimitiveIterator;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by DECOSTT on 25/09/2017.
 */
public class RandomUtils {
    public static Stream<MathUtils.SphericalCoordinates> sphericCoordsStream(){
        PrimitiveIterator.OfDouble iterator = new Random().doubles(0.0, 2.0).map(d -> d-1.0).iterator();
        return Stream.generate(() -> new CartesianCoordinates( iterator.next(), iterator.next(), iterator.next()))
                .map(c -> c.toSpherical())
                .filter(sph -> sph.r <= 1)
                .map(sph -> sph.setRadius(1.0));
    }

}
