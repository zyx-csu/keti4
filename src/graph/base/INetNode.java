/**
 * -*- coding: utf-8 -*-
 * @File    :   INetNode.java
 * @Time    :   2024/4/26 22:33
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */
package graph.base;

import graph.Coordinate;

import java.util.Map;


public interface INetNode extends Iterable<INetLink>{
    // region Properties
    int getNodeID();

    int getAdjacentLinkCount();

    boolean hasCoordinate();

    Coordinate getCoordinate();

    Map<Integer, INetLink> getPredecessorLinks();

    // Map<Integer, INetLink> getAdjacentLinks();
    // endregion

    // region Method

    /**
     * 判断是否存在后继边
     * @param headNodeID
     * @return
     */
    boolean hasAdjacentLink(int headNodeID);

    /**
     * 获取后继边
     * @param headNodeID
     * @return
     */
    INetLink getAdjacentLink(int headNodeID);

    void release();

    void addAdjacentLink(INetLink pAdjacentLink);

    void removeAdjacentLink(int headNodeID);

    void addPredecessorLink(INetLink pPredecessorLink);

    void removePredecessorLink(int tailNodeID);

    // endregion
}
