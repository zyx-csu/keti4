/**
 * -*- coding: utf-8 -*-
 * @File    :   ISpLink.java
 * @Time    :   2024/4/26 22:30
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */

package spp.staticNetEnv;

import graph.base.INetLink;


public interface ISpLink extends INetLink {
    public double getLinkCost();

    public void updateLinkCost(double dblLinkCost);
}
