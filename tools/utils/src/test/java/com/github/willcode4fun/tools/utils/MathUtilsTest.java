package com.github.willcode4fun.tools.utils;

import static org.fest.assertions.Assertions.*;
import static java.lang.Math.*;

import lombok.extern.slf4j.Slf4j;
import org.fest.assertions.Delta;
import org.junit.Test;

/**
 * Created by DECOSTT on 05/09/2017.
 */
@Slf4j
public class MathUtilsTest {

    private double[][] cartesianCoords= {
            {0,0,0},
            {0,0,1},
            {0,1,0},
            {1,0,0}};

    private double[][] sphericCoords= {
            {0,0,0},
            {1,0,0},
            {1,PI/2,PI/2},
            {1,PI/2,0}};

    @Test
    public void should_convert_cartesian_to_spherical(){
        for (int i = 0; i < cartesianCoords.length; i++) {
            double[] src = cartesianCoords[i];
            double[] dst = sphericCoords[i];
            MathUtils.CartesianCoordinates cartesian = new MathUtils.CartesianCoordinates(src[0], src[1], src[2]);
            MathUtils.SphericalCoordinates spherical = cartesian.toSpherical();
            log.debug("xyz({}) = > rtd({})",cartesian, spherical);
            assertThat(spherical.r).isEqualTo(dst[0]);
            assertThat(spherical.theta).isEqualTo(dst[1]);
            assertThat(spherical.phy).isEqualTo(dst[2]);
        }
    }


    @Test
    public void should_convert_spherical_to_cartesian(){
        for (int i = 0; i < sphericCoords.length; i++) {
            double[] src = sphericCoords[i];
            double[] dst = cartesianCoords[i];
            MathUtils.SphericalCoordinates cartesian = new MathUtils.SphericalCoordinates(src[0], src[1], src[2]);
            MathUtils.CartesianCoordinates spherical = cartesian.toCartesian();
            log.debug("xyz({}) = > rtd({})",cartesian, spherical);
            Delta delta = Delta.delta(0.00001);
            assertThat(spherical.x).isEqualTo(dst[0], delta);
            assertThat(spherical.y).isEqualTo(dst[1], delta);
            assertThat(spherical.z).isEqualTo(dst[2], delta);
        }
    }
}
