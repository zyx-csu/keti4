/**
 * -*- coding: utf-8 -*-
 * @File    :   CLabel.java
 * @Time    :   2024/4/26 22:33
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */
package spp.clabel;

import graph.base.INetLink;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class CLabel implements ILabel{
    private int nodeID;
    private INetLink link;
    private CLabel previous;

    // region Construction

    public CLabel(int nodeID, INetLink link, CLabel previous) {
        if (link == null) {
            throw new IllegalArgumentException("CLabel encounter an error. pLink should not be null");
        }
        if (previous == null) {
            throw new IllegalArgumentException("CLabel encounter an error. previous clabel should not be null");
        }
        this.nodeID = nodeID;
        this.link = link;
        this.previous = previous;
    }

    public CLabel(int nodeID) {
        if (nodeID < 0) {
            throw new IllegalArgumentException("CLabel encounter an error.nodeID shoule be positive:" + nodeID);
        }
        this.nodeID = nodeID;
        this.link = null;
        this.previous = null;
    }

    // endregion

    // region ILabel
    @Override
    public INetLink getLink() {
        return this.link;
    }

    @Override
    public int getLinkID() {
        return this.link == null ? link.getLinkID():-1;
    }

    @Override
    public int getNodeID() {
        return nodeID;
    }

    @Override
    public ILabel getPrevious() {
        return previous;
    }

    @Override
    public void replacePrevious(ILabel pLabel) {
        this.previous = (CLabel) pLabel;
    }

    @Override
    public boolean isSamePath(ILabel pInPath) {
        if (pInPath == null) {
            return false;
        }
        ILabel oPath = pInPath;
        ILabel cPath = this;
        while (oPath != null && cPath != null) {
            if (oPath.getNodeID() != cPath.getNodeID()) {
                return false;
            }
            oPath = oPath.getPrevious();
            cPath = cPath.getPrevious();
        }
        if (oPath != null || cPath != null) {
            return false;
        }
        return true;
    }

    @Override
    public boolean isNodeInPath(int iNodeID) {
        if (iNodeID == nodeID) {
            return true;
        }
        ILabel cLabel = this;
        while (cLabel != null) {
            if (cLabel.getNodeID() == iNodeID) {
                return true;
            }
            cLabel = cLabel.getPrevious();
        }
        return false;
    }

    @Override
    public String toPathString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nodeID);
        ILabel cLabel = previous;

        while (cLabel != null) {
            sb.insert(0, (cLabel.getNodeID() + ","));
            cLabel = cLabel.getPrevious();
        }
        return sb.toString();
    }

    @Override
    public String toReversePathString() {
        StringBuilder sb = new StringBuilder();
        sb.append(nodeID);
        ILabel cLabel = previous;

        while (cLabel != null) {
            sb.append("," + cLabel.getNodeID());
            cLabel = cLabel.getPrevious();
        }
        return sb.toString();
    }

    @Override
    public List<Integer> getNodeIDs() {
        LinkedList<Integer> nodes = new LinkedList<>();
        ILabel cLabel = this;
        while (cLabel != null) {
            nodes.addFirst(cLabel.getNodeID());
            cLabel = cLabel.getPrevious();
        }
        return nodes;
    }

    @Override
    public List<Integer> getReverseNodeIDs() {
        List<Integer> nodes = new ArrayList<>();
        ILabel cLabel = this;
        while (cLabel != null) {
            nodes.add(cLabel.getNodeID());
            cLabel = cLabel.getPrevious();
        }
        return nodes;
    }

    @Override
    public List<Integer> getLinksIDs() {
        LinkedList<Integer> linkIDs = new LinkedList<>();
        ILabel cLabel = this;
        while (cLabel != null) {
            linkIDs.addFirst(cLabel.getLinkID());
            cLabel = cLabel.getPrevious();
        }
        linkIDs.remove(0); // 删除第一个元素
        return linkIDs;
    }

    @Override
    public List<Integer> getReverseLinkIDs() {
        List<Integer> linkIDs = new ArrayList<>();
        ILabel cLabel = this;
        while (cLabel != null) {
            linkIDs.add(cLabel.getLinkID());
            cLabel = cLabel.getPrevious();
        }
        linkIDs.remove(linkIDs.size() - 1); // 删除最后一个元素
        return linkIDs;
    }

    @Override
    public String toString() {
        return this.toPathString();
    }

    // endregion
}
