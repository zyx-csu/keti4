/**
 * -*- coding: utf-8 -*-
 * @File    :   Coordinate.java
 * @Time    :   2024/4/26 22:33
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */
package graph;

public class Coordinate {
    public double x;
    public double y;

    public Coordinate(double dblX, double dblY) {
        this.x = dblX;
        this.y = dblY;
    }

    /**
     * 计算欧氏距离
     * @param pCoordinate
     * @return
     */
    public double cptEuclideanDistance(Coordinate pCoordinate) {
        return Math.sqrt(Math.pow(pCoordinate.x - this.x, 2) + Math.pow(pCoordinate.y - this.y, 2));
    }
}
