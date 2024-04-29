/**
 * -*- coding: utf-8 -*-
 * @File    :   ShortPathFactory.java
 * @Time    :   2024/4/26 22:33
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */
package spp.Static.oneToOne;

import spp.Static.oneToOne.AStarSPA;
import spp.Static.oneToOne.DijkstraSPA;
import spp.Static.oneToOne.IShortPathAlgorithm;


public class ShortPathFactory {
    public static IShortPathAlgorithm createShortPathFunction(String algorithm) {
        IShortPathAlgorithm spa = null;
        switch (algorithm) {
            case "dijkstra":
                spa = new DijkstraSPA();
                break;
            case "aStar":
                spa = new AStarSPA();
                break;
        }
        return spa;
    }
}
