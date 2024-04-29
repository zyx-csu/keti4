/**
 * -*- coding: utf-8 -*-
 * @File    :   StaticLink.java
 * @Time    :   2024/4/26 22:32
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */
package spp.Static;

import graph.base.INetNode;
import spp.staticNetEnv.ISpLink;


public class StaticLink implements ISpLink {
    private INetNode tailNode;
    private INetNode headNode;
    private Integer linkID;
    private double linkCost;

    public StaticLink(INetNode pTailNode, INetNode pHeadNode, int iLinkID, double dblLinkCost) {
        if (dblLinkCost < 0) {
            throw new IllegalArgumentException("StaticLink encounter an error : dblLinkCost < 0");
        }
        if (pHeadNode == null || pTailNode == null) {
            throw new IllegalArgumentException("StaticLink encounter an error : pHeadNode = null or pTailNode = 0");
        }
        this.tailNode = pTailNode;
        this.headNode = pHeadNode;
        this.linkID = iLinkID;
        this.linkCost = dblLinkCost;
    }

    @Override
    public INetNode getTailNode() {
        return this.tailNode;
    }

    @Override
    public int getTailNodeID() {
        return this.tailNode.getNodeID();
    }

    @Override
    public INetNode getHeadNode() {
        return this.headNode;
    }

    @Override
    public int getHeadNodeID() {
        return this.headNode.getNodeID();
    }

    @Override
    public int getLinkID() {
        return this.linkID;
    }

    @Override
    public void release() {
        return;
    }

    @Override
    public double getLinkCost() {
        return this.linkCost;
    }

    @Override
    public void updateLinkCost(double dblLinkCost) {
        if (dblLinkCost > 0) {
            this.linkCost = dblLinkCost;
        } else {
            throw new IllegalArgumentException("staticLink updateLinkCost encounter an error : dblLinkCost < 0");
        }
    }

    public String toString() {
        return this.getTailNodeID() + "-" + this.getHeadNodeID();
    }
}
