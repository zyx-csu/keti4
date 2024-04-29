/**
 * -*- coding: utf-8 -*-
 * @File    :   INetLink.java
 * @Time    :   2024/4/26 22:33
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */
package graph.base;


public interface INetLink {
    // region Properties
    INetNode getTailNode();

    int getTailNodeID();

    INetNode getHeadNode();

    int getHeadNodeID();

    int getLinkID();

    // endregion

    void release();
}
