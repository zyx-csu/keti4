/**
 * -*- coding: utf-8 -*-
 * @File    :   ISpNode.java
 * @Time    :   2024/4/26 22:32
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */
package spp.staticNetEnv;

import graph.base.INetNode;
import spp.Static.SpLabel;


public interface ISpNode extends INetNode {
    SpLabel getLabel();

    void setLabel(SpLabel spLabel);

    void setSPID(int sPID);

    boolean isDifferentSPID(int SPID);
}
