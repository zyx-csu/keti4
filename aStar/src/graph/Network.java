/**
 * -*- coding: utf-8 -*-
 * @File    :   Network.java
 * @Time    :   2024/4/26 22:34
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */
package graph;

import graph.base.INetLink;
import graph.base.INetNode;
import graph.base.INetwork;
import graph.base.NetworkType;


import java.security.KeyException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

public class Network implements INetwork {
    private Map<Integer, INetNode> nodeSet;
    private NetworkType networkType;
    private boolean hasTurns;
    private boolean hasCoordinate;
    private boolean hasTollDistance;
    private boolean hasMaxSpeed;
    private boolean hasMaxLinkLength;
    private boolean hasShortestPathResults;
    private double maxSpeed;
    private double maxLinkLength;
    private int gSPID;

    // region Construction

    public Network(NetworkType networkType, boolean hasTurns, boolean hasCoordinate, boolean hasTollDistance) {
        nodeSet = new HashMap<>(20);

        this.networkType = networkType;
        this.hasTurns = hasTurns;
        this.hasCoordinate = hasCoordinate;
        this.hasTollDistance = hasTollDistance;
        maxSpeed = Math.round(100 / 2.6);
        hasMaxSpeed = false;
        maxLinkLength = 0;
        hasMaxLinkLength = false;
        hasShortestPathResults = false;

        this.gSPID = 1;
    }

    // endregion

    // region INetwork

    @Override
    public int getNodeCount() {
        return nodeSet.size();
    }

    @Override
    public int getLinkCount() {
        int linkCnt = 0;
        for( INetNode node : nodeSet.values()) {
            linkCnt += node.getAdjacentLinkCount();
        }
        return linkCnt;
    }

    @Override
    public NetworkType getNetType() {
        return this.networkType;
    }

    @Override
    public boolean hasTurn() {
        return this.hasTurns;
    }

    @Override
    public boolean hasCoordinate() {
        return this.hasCoordinate;
    }

    @Override
    public boolean hasTollDistance() {
        return this.hasTollDistance;
    }

    @Override
    public boolean hasShortestPaths() {
        return this.hasShortestPathResults;
    }

    @Override
    public boolean hasMaxSpeed() {
        return this.hasMaxSpeed;
    }

    @Override
    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    @Override
    public boolean hasMaxLinkLength() {
        return this.hasMaxLinkLength;
    }

    @Override
    public double getMaxLinkLength() {
        return this.maxLinkLength;
    }

    @Override
    public int getSPID() {
        return this.gSPID;
    }

    @Override
    public boolean addNewSPID() {
        gSPID += 1;
        if (gSPID == Integer.MAX_VALUE) {
            gSPID = 1;
            return true;
        }
        return false;
    }

    @Override
    public boolean isNodeExists(int iNodeID) {
        return nodeSet.containsKey(iNodeID);
    }

    @Override
    public INetNode getNetNode(int iNodeID) {
        if (nodeSet.containsKey(iNodeID)) {
            return nodeSet.get(iNodeID);
        } else {
            throw new NoSuchElementException("Network.GetNetNode() encounter an error. nodeSet don't contains iNodeID: " + iNodeID);
        }
    }

    @Override
    public boolean isLinkExists(int iTailNodeID, int iHeadNodeID) {
        if (!this.isNodeExists(iTailNodeID)) {
            return false;
        }
        INetNode tailNode = this.getNetNode(iTailNodeID);
        if (!tailNode.hasAdjacentLink(iHeadNodeID)) {
            return false;
        }
        return true;
    }

    @Override
    public INetLink getNetLink(int iTailNodeID, int iHeadNodeID) {
        try {
            INetNode tailNode = this.getNetNode(iTailNodeID);
            return tailNode.getAdjacentLink(iHeadNodeID);
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Network.GetNetLink() encounter an error." + ex.toString());
        }
    }

    @Override
    public void addNode(int iNodeID, INetNode pNetNode) {
        if (iNodeID < 0) {
            throw new IllegalArgumentException("Network encounter an error. nodeID should not be negative: " + iNodeID);
        }
        if (this.isNodeExists(iNodeID)) {
            throw new IllegalArgumentException("Network encounter an error. input node is alread in the network: " + iNodeID);
        }
        if (pNetNode == null) {
            throw new NullPointerException("Network encounter an error. input pNetNode should not be null");
        }
        nodeSet.put(iNodeID, pNetNode);
    }

    @Override
    public void removeNode(int iNodeID) {
        if (nodeSet.containsKey(iNodeID)) {
            nodeSet.remove(iNodeID);
        } else {
            throw new IllegalArgumentException("Network.RemoveNode() encounter an error.Node " + iNodeID + " is not in the network.");
        }
    }

    @Override
    public void release() {
        for (INetNode node : nodeSet.values()) {
            for (INetLink link : node) {
                link.release();
            }
        }
        for (INetNode node : nodeSet.values()) {
            node.release();
        }
    }
    // endregion

    // region Iterable
    @Override
    public Iterator<INetNode> iterator() {
        return nodeSet.values().iterator();
    }
    // endregion
}
