/**
 * -*- coding: utf-8 -*-
 * @File    :   StaticNode.java
 * @Time    :   2024/4/26 22:32
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */
package spp.Static;

import graph.NetNode;
import graph.base.INetLink;
import spp.staticNetEnv.ISpNode;

import java.util.Map;


public class StaticNode extends NetNode implements ISpNode{
    private SpLabel spLabel;
    private int gSPID;

    // region Construction
    public StaticNode(int iNodeID, Map<Integer, INetLink> pAdjacentLinks, Map<Integer, INetLink> pPredecessorLinks) {
        super(iNodeID, pAdjacentLinks, pPredecessorLinks);
        spLabel = null;
        gSPID = 1;
    }

    public StaticNode(int pNodeID, double x, double y, Map<Integer, INetLink> pAdjacentLinks, Map<Integer, INetLink> pPredecessorLinks) {
        super(pNodeID, x, y, pAdjacentLinks, pPredecessorLinks);
        spLabel = null;
        gSPID = 1;
    }

    public StaticNode(int pNodeID) {
        super(pNodeID);
        spLabel = null;
        gSPID = 1;
    }

    public StaticNode(int pNodeID, double x, double y) {
        super(pNodeID, x, y);
        spLabel = null;
        gSPID = 1;
    }
    // endregion

    @Override
    public SpLabel getLabel() {
        return spLabel;
    }

    @Override
    public void setLabel(SpLabel spLabel) {
        this.spLabel = spLabel;
    }

    @Override
    public void setSPID(int sPID) {
        this.gSPID = sPID;
    }

    @Override
    public boolean isDifferentSPID(int SPID) {
        return !(this.gSPID == SPID);
    }
}
