/**
 * -*- coding: utf-8 -*-
 * @File    :   SpLabel.java
 * @Time    :   2024/4/26 22:30
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */

package spp.Static;

import graph.base.INetLink;
import graph.base.INetNode;
import graph.base.INetwork;
import spp.clabel.CLabel;
import spp.staticNetEnv.ISpLink;
import spp.staticNetEnv.ISpNode;

import java.util.ArrayList;
import java.util.List;


public class SpLabel extends CLabel {
    private ISpNode associatedNode;
    private double pathCost;
    private double dblHCost; // A*使用
    // region Constructors
    public SpLabel(ISpNode pAssociateNode, double iPathCost, INetLink link, CLabel previous, double dblHCost) {
        super(pAssociateNode.getNodeID(), link, previous);
        if (iPathCost < 0) {
            throw new IllegalArgumentException("SpLabel Construct encounter an error: ipathCost < 0");
        }
        this.associatedNode = pAssociateNode;
        this.pathCost = iPathCost;
        this.dblHCost = dblHCost;
    }

    public SpLabel(ISpNode pAssociateNode, double dblHCost) {
        super(pAssociateNode.getNodeID());
        this.associatedNode = pAssociateNode;
        this.pathCost = 0;
        this.dblHCost = dblHCost;
    }
    // endregion

    public double getDblHCost() {
        return dblHCost;
    }

    public void setDblHCost(double dblHCost) {
        this.dblHCost = dblHCost;
    }

    public ISpNode getAssociatedNode() {
        return associatedNode;
    }

    public double getPathCost() {
        return pathCost;
    }

    public void setPathCost(double pathCost) {
        if (pathCost < 0) {
            throw new IllegalArgumentException("SpLabel setPathCost encounter an error: ipathCost < 0");
        }
        this.pathCost = pathCost;
    }

    public static SpLabel generatePathByIDs(List<Integer> nodeIDs, INetwork pNetwork) {
        if (nodeIDs == null || pNetwork == null) {
            throw new IllegalArgumentException("SpLabel.GeneratePathByIDs() encounter an error: the inputs should not be null");
        }
        if (nodeIDs.size() == 0) {
            throw new IllegalArgumentException("SpLabel.GeneratePathByIDs() encounter an error: the pNodeIDs should not be empty");
        }
        try {
            ISpNode pTailNode;
            ISpNode pHeadNode = (ISpNode) pNetwork.getNetNode(nodeIDs.get(0));
            SpLabel pPreLabel;
            SpLabel pCurLabel = new SpLabel(pHeadNode, 0);
            ISpLink pNetLink;
            for (int i = 1; i < nodeIDs.size(); i++) {
                pTailNode = pHeadNode;
                pHeadNode = (ISpNode) pNetwork.getNetNode(nodeIDs.get(i));
                pNetLink = (ISpLink) pTailNode.getAdjacentLink(pHeadNode.getNodeID());
                pPreLabel = pCurLabel;
                pCurLabel = pPreLabel.expand(true, pNetLink, 0);
            }
            return pCurLabel;
        } catch (Exception ex) {
            throw new IllegalArgumentException("STDLabel.GeneratePathByIDs() encounter an error: Input node sequences is invalid." + ex.toString());
        }
    }

    public SpLabel expandAcylic(boolean bForward, INetLink pLink, double dblHCost) {
        if (pLink == null) {
            throw new IllegalArgumentException("STDLabel.ExpandAcylic() function encounters an error : pLink is null");
        }
        INetNode pSpNode;
        if (bForward) {
            pSpNode = pLink.getHeadNode();
        } else {
            pSpNode = pLink.getTailNode();
        }
        return this.isNodeInPath(pSpNode.getNodeID()) ? null : expand(bForward, pLink, dblHCost);
    }

    public SpLabel expand(boolean bForward, INetLink pLink, double dblHCost) {
        if (pLink == null) {
            throw new IllegalArgumentException("STDLabel.Expand() function encounters an error : pLink is null");
        }
        ISpLink pSpLink = (ISpLink) pLink;
        double iPathCost = this.pathCost + pSpLink.getLinkCost();
        ISpNode pSpNode;
        if (bForward) {
            pSpNode = (ISpNode) pSpLink.getHeadNode();
        } else {
            pSpNode = (ISpNode) pSpLink.getTailNode();
        }
        return new SpLabel(pSpNode, iPathCost, pSpLink, this, dblHCost);
    }

    public String toString() {
        String format = "{0,0:#.000}";
        String strResult = "Shortest Path: " + this.toPathString() + "Path cost: " + String.format(format, this.pathCost);
        return strResult;
    }

    public String getNodesInPath() {
        SpLabel curLabel = this;
        //StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        while (curLabel != null) {
            list.add(curLabel.getNodeID());
            // sb.append(curLabel.getAssociatedNode().getNodeID() + ",");
            curLabel = (SpLabel) curLabel.getPrevious();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = list.size() - 1; i > 0; i--) {
            sb.append(list.get(i) + ",");
        }
        sb.append(list.get(0));
        return sb.toString();
    }

    public String getLinksInPath() {
        SpLabel curLabel = this;
        SpLabel preLabel = null;
        ISpNode preNode = null;
        List<Integer> list = new ArrayList<>();
        //
        while (curLabel.getPrevious() != null) {
            preLabel = (SpLabel) curLabel.getPrevious();
            preNode = preLabel.getAssociatedNode();
            INetLink adjacentLink = preNode.getAdjacentLink(curLabel.getNodeID());
            list.add(adjacentLink.getLinkID());
            curLabel = preLabel;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = list.size() - 1; i > 0; i--) {
            sb.append(list.get(i) + ",");
        }
        sb.append(list.get(0));
        return sb.toString();
    }
}
