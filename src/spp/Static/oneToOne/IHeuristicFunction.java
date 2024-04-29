/**
 * -*- coding: utf-8 -*-
 * @File    :   IHeuristicFunction.java
 * @Time    :   2024/4/26 22:33
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */
package spp.Static.oneToOne;

import graph.base.INetNode;


public interface IHeuristicFunction {
    int getDestionationNodeID();
    double getHeuristicDistance(INetNode pNode);
}
