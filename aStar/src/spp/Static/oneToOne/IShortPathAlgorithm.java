/**
 * -*- coding: utf-8 -*-
 * @File    :   IShortPathAlgorithm.java
 * @Time    :   2024/4/26 22:33
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */
package spp.Static.oneToOne;

import graph.Network;
import spp.Static.SpLabel;


public interface IShortPathAlgorithm {
    int getOriginNode();
    void setOriginNode(int originNodeID);
    int getDestinationNode();
    void setDestinationNode(int destinationNodeID);

    SpLabel findShortPath(Network pNetwork, int iOrigin, int iDestination);
}
