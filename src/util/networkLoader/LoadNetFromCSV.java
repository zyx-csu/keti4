/**
 * -*- coding: utf-8 -*-
 * @File    :   LoadNetFromCSV.java
 * @Time    :   2024/4/26 22:31
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */

package util.networkLoader;

import graph.Network;
import graph.base.NetworkType;
import spp.Static.StaticLink;
import spp.Static.StaticNode;

import java.io.BufferedReader;
import java.io.FileReader;



public class LoadNetFromCSV {
    public static Network loadNetWithCoordinate(String path) throws Exception {
        Network pNetwork = new Network(NetworkType.NT_Static, false, true, false);

        BufferedReader reader;

        String inString ="";
        try {
            reader = new BufferedReader(new FileReader(path));
            reader.readLine();

            StaticNode pTailNode, pHeadNode;
            StaticLink pNetLink;

            while((inString=reader.readLine()) != null) {
                String item[] = inString.split(",");
                int fromNodeID = new Integer(item[0]);
                double fromNodeX = new Double(item[1]);
                double fromNodeY = new Double(item[2]);
                int toNodeID = new Integer(item[3]);
                double toNodeX = new Double(item[4]);
                double toNodeY = new Double(item[5]);
                int linkID = new Integer(item[6]);
                double linkCost = new Double(item[7]);

                if(!pNetwork.isNodeExists(fromNodeID)) {
                    pTailNode = new StaticNode(fromNodeID, fromNodeX, fromNodeY);
                    pNetwork.addNode(fromNodeID, pTailNode);
                } else {
                    pTailNode = (StaticNode) pNetwork.getNetNode(fromNodeID);
                }

                if(!pNetwork.isNodeExists(toNodeID)) {
                    pHeadNode = new StaticNode(toNodeID, toNodeX, toNodeY);
                    pNetwork.addNode(toNodeID, pHeadNode);
                } else {
                    pHeadNode = (StaticNode) pNetwork.getNetNode(toNodeID);
                }

                if(!pTailNode.hasAdjacentLink(toNodeID)) {
                    pNetLink = new StaticLink(pTailNode, pHeadNode, linkID, linkCost);
                    pTailNode.addAdjacentLink(pNetLink);
                } else {
                    throw new Exception("pTailNode exists the adjacentLink, the link exists");
                }

                if(!pHeadNode.getPredecessorLinks().containsKey(fromNodeID)) {
                    pHeadNode.addPredecessorLink(pNetLink);
                } else {
                    throw new Exception("pHeadNode exists the PredecessorLink, the link exists");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return pNetwork;
    }



    public static Network loadNetWithoutCoordinate(String path) throws Exception {
        Network pNetwork = new Network(NetworkType.NT_Static, false, false, false);
        BufferedReader reader;

        String inString ="";
        try {

            reader = new BufferedReader(new FileReader(path));
            reader.readLine();

            StaticNode pTailNode, pHeadNode;
            StaticLink pNetLink;

            while((inString=reader.readLine())!=null) {
                String item[] = inString.split(",");
                int fromNodeID = new Integer(item[0]);
                int toNodeID = new Integer(item[3]);
                int objectID = new Integer(item[6]);
                double linkCost = new Double(item[7]);

                if(!pNetwork.isNodeExists(fromNodeID)) {
                    pTailNode = new StaticNode(fromNodeID);
                    pNetwork.addNode(fromNodeID, pTailNode);
                }
                else {
                    pTailNode = (StaticNode) pNetwork.getNetNode(fromNodeID);
                }

                if(!pNetwork.isNodeExists(toNodeID)) {
                    pHeadNode = new StaticNode(toNodeID);
                    pNetwork.addNode(toNodeID, pHeadNode);
                }
                else {
                    pHeadNode = (StaticNode) pNetwork.getNetNode(toNodeID);
                }

                if(!pTailNode.hasAdjacentLink(toNodeID)) {
                    pNetLink = new StaticLink(pTailNode, pHeadNode, objectID, linkCost);
                    pTailNode.addAdjacentLink(pNetLink);
                }
                else {
                    throw new Exception("pTailNode exists the adjacentLink, the link exists");
                }

                if(!pHeadNode.getPredecessorLinks().containsKey(fromNodeID)) {
                    pHeadNode.addPredecessorLink(pNetLink);
                }
                else {
                    throw new Exception("pHeadNode exists the PredecessorLink, the link exists");
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return pNetwork;
    }
}
