/**
 * -*- coding: utf-8 -*-
 * @File    :   NetNode.java
 * @Time    :   2024/4/26 22:34
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */
package graph;

import graph.base.INetLink;
import graph.base.INetNode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NetNode implements INetNode {
    private int nodeID;
    private Coordinate coordinate;
    private Map<Integer, INetLink> adjacentLinks;
    private Map<Integer, INetLink> predecessorLinks;

    // region Construction
    public NetNode(int pNodeID, Map<Integer, INetLink> pAdjacentLinks, Map<Integer, INetLink> pPredecessorLinks) {
        if (pNodeID < 0) {
            throw new IllegalArgumentException("NetNode encounter an error : " + "nodeID should large than 0: " + pNodeID);
        }
        if (pAdjacentLinks == null || pPredecessorLinks == null) {
            throw new IllegalArgumentException("NetNode encounter an error. pAdjacentLinkset and pPredecessorLinks should not be null");
        }
        this.nodeID = pNodeID;
        this.adjacentLinks = pAdjacentLinks;
        this.predecessorLinks = pPredecessorLinks;
        this.coordinate = null;
    }

    public NetNode(int pNodeID, double x, double y, Map<Integer, INetLink> pAdjacentLinks, Map<Integer, INetLink> pPredecessorLinks) {
        if (pNodeID < 0) {
            throw new IllegalArgumentException("NetNode encounter an error : " + "nodeID should large than 0: " + pNodeID);
        }
        if (pAdjacentLinks == null || pPredecessorLinks == null) {
            throw new IllegalArgumentException("NetNode encounter an error. pAdjacentLinkset and pPredecessorLinks should not be null");
        }
        this.nodeID = pNodeID;
        this.adjacentLinks = pAdjacentLinks;
        this.predecessorLinks = pPredecessorLinks;
        this.coordinate = new Coordinate(x, y);
    }

    public NetNode(int pNodeID, Coordinate pLoc) {
        if (pNodeID < 0) {
            throw new IllegalArgumentException("NetNode encounter an error : " + "nodeID should large than 0: " + pNodeID);
        }
        this.nodeID = pNodeID;
        adjacentLinks = new HashMap<>(2);
        predecessorLinks = new HashMap<>(2);
        coordinate = pLoc;
    }

    public NetNode(int pNodeID) {
        this(pNodeID, new HashMap<Integer, INetLink>(2), new HashMap<Integer, INetLink>(2));
    }

    public NetNode(int pNodeID, double x, double y) {
        this(pNodeID, x, y, new HashMap<Integer, INetLink>(2), new HashMap<Integer, INetLink>(2));
    }
    // endregion

    // region INetNode
    @Override
    public int getNodeID() {
        return this.nodeID;
    }

    @Override
    public int getAdjacentLinkCount() {
        return adjacentLinks.size();
    }

    @Override
    public boolean hasCoordinate() {
        return coordinate == null;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public Map<Integer, INetLink> getPredecessorLinks() {
        return predecessorLinks;
    }

    @Override
    public boolean hasAdjacentLink(int headNodeID) {
        return adjacentLinks.containsKey(headNodeID);
    }

    @Override
    public INetLink getAdjacentLink(int headNodeID) {
        return adjacentLinks.get(headNodeID);
    }

    @Override
    public void release() {
        adjacentLinks = null;
        predecessorLinks = null;
    }

    @Override
    public void addAdjacentLink(INetLink pAdjacentLink) {
        if (pAdjacentLink == null) {
            throw new NullPointerException("NetNode encounter an error. pAdjacentLink should not be null");
        }
        adjacentLinks.put(pAdjacentLink.getHeadNodeID(), pAdjacentLink);
    }

    @Override
    public void removeAdjacentLink(int headNodeID) {
        if (adjacentLinks.containsKey(headNodeID)) {
            predecessorLinks.remove(headNodeID);
        } else {
            throw new NullPointerException("NetNode(removeAdjacentLink function) encounter an error. input head node does not exist:" + headNodeID);
        }
    }

    @Override
    public void addPredecessorLink(INetLink pPredecessorLink) {
        if (pPredecessorLink == null) {
            throw new NullPointerException("NetNode encounter an error. pPredecessorLink should not be null");
        }
        predecessorLinks.put(pPredecessorLink.getTailNodeID(), pPredecessorLink);
    }

    @Override
    public void removePredecessorLink(int tailNodeID) {
        if (predecessorLinks.containsKey(tailNodeID)) {
            predecessorLinks.remove(tailNodeID);
        } else {
            throw new NullPointerException("NetNode(removePredecessorLink function) encounter an error. input tail node does not exist:" + tailNodeID);
        }
    }
    // endregion

    // region Iterator
    @Override
    public Iterator<INetLink> iterator() {
        return this.adjacentLinks.values().iterator();
    }
    // endregion

    // region Object
    @Override
    public String toString() {
        return "" + this.nodeID;
    }
    // endregion
}
