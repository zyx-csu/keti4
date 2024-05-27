/**
 * -*- coding: utf-8 -*-
 * @File    :   AStar.java
 * @Time    :   2024/4/26 22:29
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */

import graph.Network;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import spp.Static.oneToOne.IShortPathAlgorithm;
import spp.Static.oneToOne.ShortPathFactory;
import spp.Static.SpLabel;
import util.networkLoader.LoadNetFromCSV;
import util.Logging;
import java.util.logging.Logger;
import java.util.logging.Level;


public class AStar {
    public static Namespace getArgs(String[] args, Logger logger) {
        ArgumentParser argumentParser = ArgumentParsers.newFor("aStar").build()
                .description("Calculate shortest path between tow points in road network employing aStar algorithm.");

        argumentParser.addArgument("-f", "--networkFile")
                .dest("networkFile")
                .type(String.class)
                .setDefault(" ")
                .help("Input network file path");

        argumentParser.addArgument("-originNodeId", "--originNodeId")
                .dest("originNodeId")
                .type(Integer.class)
                .setDefault(0)
                .help("Origin node ID");

        argumentParser.addArgument("-destinationNodeId", "--destinationNodeId")
                .dest("destinationNodeId")
                .type(Integer.class)
                .setDefault(0)
                .help("Destination node ID");

        Namespace namespace = null;
        try {
            namespace = argumentParser.parseArgs(args);
        } catch (ArgumentParserException e) {
            logger.log(Level.INFO, "Incorrect parameters are passed to the procedure");
        }
        return namespace;
    }

    public static void main(String[] args) throws Exception {
        Logger logger = Logging.getLogger("aStar.log");
        Namespace namespace = getArgs(args, logger);

        Network pNet = null;
        try {
            String netWorkFilePath = namespace.getString("networkFile");
            String filePath = java.net.URLDecoder.decode(netWorkFilePath, "utf-8");
            pNet = LoadNetFromCSV.loadNetWithCoordinate(filePath);
        } catch (Exception e) {
            logger.log(Level.INFO, " network file not found or failed to load");
            System.exit(-1);
        }

        IShortPathAlgorithm aStarAlg = ShortPathFactory.createShortPathFunction("aStar");
        SpLabel aimPath = null;
        int originNodeId = 0, destinationNodeId = 0;
        try {
            originNodeId = namespace.getInt("originNodeId");
            destinationNodeId = namespace.getInt("destinationNodeId");
            aimPath = aStarAlg.findShortPath(pNet, originNodeId, destinationNodeId);
        } catch (Exception e) {
            logger.log(Level.INFO, e.getMessage());
            System.exit(-1);
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
