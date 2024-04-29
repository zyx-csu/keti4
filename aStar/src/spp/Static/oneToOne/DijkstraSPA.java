/**
 * -*- coding: utf-8 -*-
 * @File    :   DijkstraSPA.java
 * @Time    :   2024/4/26 22:32
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */
package spp.Static.oneToOne;

import graph.Network;
import graph.base.INetLink;
import spp.Static.SpLabel;
import spp.Static.StaticNode;
import spp.staticNetEnv.ISpLink;
import spp.staticNetEnv.ISpNode;
import util.FibonacciHeap;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class DijkstraSPA implements IShortPathAlgorithm{
    int originID;
    int destionationID;

    @Override
    public int getOriginNode() {
        return this.originID;
    }

    @Override
    public void setOriginNode(int originNodeID) {
        this.originID = originNodeID;
    }

    @Override
    public int getDestinationNode() {
        return this.destionationID;
    }

    @Override
    public void setDestinationNode(int destinationNodeID) {
        this.destionationID = destinationNodeID;
    }

    @Override
    public SpLabel findShortPath(Network pNetwork, int iOrigin, int iDestination) {
        if (pNetwork == null) {
            throw new IllegalArgumentException("StaticSPA dijkstraFHeap encounters an error. input network should not be null");
        }
        if (!pNetwork.isNodeExists(iOrigin)) {
            throw new IllegalArgumentException("StaticSPA dijkstraFHeap encounters an error. input iOrigin should not be null");
        }
        if (!pNetwork.isNodeExists(iDestination)) {
            throw new IllegalArgumentException("StaticSPA dijkstraFHeap encounters an error. input iDestination should not be null");
        }

        FibonacciHeap<SpLabel> fh = new FibonacciHeap<>();

        // 初始化
        final Map<SpLabel, FibonacciHeap.Entry<SpLabel>> heapEntries = new HashMap<>();
        StaticNode oriNode = (StaticNode) pNetwork.getNetNode(iOrigin);
        SpLabel oriLabel = new SpLabel(oriNode, 0);
        oriNode.setLabel(oriLabel);
        heapEntries.put(oriLabel, fh.enqueue(oriLabel, 0));

        SpLabel pChosenLabel, pAdjacentLabel;
        ISpNode pChosenNode, pAdjacentNode;
        ISpLink pAdjacentLink;

        while (!fh.isEmpty()) {
            pChosenLabel = fh.dequeueMin().getValue();
            // 找到了目的地，返回一个label
            if (iDestination == pChosenLabel.getAssociatedNode().getNodeID()) {
                return pChosenLabel;
            }
            // 扩展路径
            pChosenNode = pChosenLabel.getAssociatedNode();
            pChosenNode.getAdjacentLinkCount();
            Iterator<INetLink> adjacentLinkIter = pChosenNode.iterator();
            while (adjacentLinkIter.hasNext()) {
                pAdjacentLink = (ISpLink) adjacentLinkIter.next();
                pAdjacentNode = (ISpNode) pAdjacentLink.getHeadNode();

                pAdjacentLabel = pChosenLabel.expandAcylic(true, pAdjacentLink, 0);

                if (pAdjacentLabel != null) {
                    if (pAdjacentNode.getLabel() == null) {
                        pAdjacentNode.setLabel(pAdjacentLabel);
                        heapEntries.put(pAdjacentLabel, fh.enqueue(pAdjacentLabel, pAdjacentLabel.getPathCost()));
                    } else {
                        if (pAdjacentLabel.getPathCost() < pAdjacentNode.getLabel().getPathCost()) {
                            fh.delete(heapEntries.get(pAdjacentNode.getLabel()));
                            pAdjacentNode.setLabel(pAdjacentLabel);
                            heapEntries.put(pAdjacentLabel, fh.enqueue(pAdjacentLabel, pAdjacentLabel.getPathCost()));
                        }
                    }
                }
            }
        }
        return null;
    }
}
