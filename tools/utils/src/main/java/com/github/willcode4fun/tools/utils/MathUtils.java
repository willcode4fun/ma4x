package com.github.willcode4fun.tools.utils;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * Created by DECOSTT on 05/09/2017.
 */

public class MathUtils {

    @ToString
    @AllArgsConstructor
    public static class CartesianCoordinates{

        public double x, y, z;

        public SphericalCoordinates toSpherical(){
            double r = Math.sqrt(x*x + y*y + z*z);
            double p = (r == 0.0)?0.0:Math.atan2(y,x);
            double t = (r == 0.0)?0.0:Math.acos(z/r);
            return SphericalCoordinates.of(r,t,p);
        }
    }

    @ToString
    @AllArgsConstructor
    public static class SphericalCoordinates{

        public double r, theta, phy;

        public CartesianCoordinates toCartesian(){
            double x = r * Math.sin(theta) *Math.cos(phy);
            double y = r * Math.sin(theta) *Math.sin(phy);
            double z = r * Math.cos(theta);
            return new CartesianCoordinates(x, y, z);
        }

        public static SphericalCoordinates of( double r,double theta,double phy){
            return new SphericalCoordinates(r,theta,phy);
        }
    }
}
