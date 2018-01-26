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
            return new SphericalCoordinates(r,t,p);
        }
    }

    @ToString
    @AllArgsConstructor
    public static class SphericalCoordinates{

        public double r, theta, phy;

        public SphericalCoordinates setRadius(double r){
            this.r = r;
            return this;
        }

        public CartesianCoordinates toCartesian(){
            double x = r * Math.sin(theta) *Math.cos(phy);
            double y = r * Math.sin(theta) *Math.sin(phy);
            double z = r * Math.cos(theta);
            return new CartesianCoordinates(x, y, z);
        }
    }

    public static double angularDist(CartesianCoordinates a, CartesianCoordinates b){
        double scalar = a.x * b.x + a.y * b.y + a.z * b.z;
        double squareRadius = a.x * a.x + a.y * a.y + a.z * a.z;
        return Math.acos(scalar/squareRadius);
    }
}
