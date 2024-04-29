/**
 * -*- coding: utf-8 -*-
 * @File    :   AStar.java
 * @Time    :   2024/4/26 22:29
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */

import graph.Network;
import spp.Static.oneToOne.IShortPathAlgorithm;
import spp.Static.oneToOne.ShortPathFactory;
import spp.Static.SpLabel;
import sun.nio.ch.Net;
import util.networkLoader.LoadNetFromCSV;
import util.Logging;
import java.util.logging.Logger;
import java.util.logging.Level;


public class AStar {
    public static void main(String[] args) throws Exception {
        Logger logger = Logging.getLogger("aStar.log");

        if (args.length != 3) {
            logger.log(Level.INFO, "incorrect number of parameters are passed to the procedure");
            return;
        }

        String netWorkFilePath = args[0];
        int originNodeId = Integer.parseInt(args[1]);
        int destinationNodeId = Integer.parseInt(args[2]);


        Network pNet = null;
        try {
            String filePath = java.net.URLDecoder.decode(netWorkFilePath, "utf-8");
            pNet = LoadNetFromCSV.loadNetWithCoordinate(filePath);
        } catch (Exception e) {
            logger.log(Level.INFO, " network file not found or failed to load");
            return;
        }

        IShortPathAlgorithm aStarAlg = ShortPathFactory.createShortPathFunction("aStar");
        SpLabel aimPath = null;
        try {
            aimPath = aStarAlg.findShortPath(pNet, originNodeId, destinationNodeId);
        } catch (Exception e) {
            logger.log(Level.INFO, e.getMessage());
        }
        String linksInPath = aimPath.getLinksInPath();
        String nodesInPath = aimPath.getNodesInPath();
        double pathCost = aimPath.getPathCost();

        System.out.println("originNodeId:" + originNodeId + ' ' + "destinationNodeId: " + destinationNodeId);
        System.out.println("pathCost: " + pathCost);
        System.out.println("linksInPath: " +linksInPath);
        System.out.println("nodesInPath: " + nodesInPath);
    }
}
