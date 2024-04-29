/**
 * -*- coding: utf-8 -*-
 * @File    :   ILabel.java
 * @Time    :   2024/4/26 22:33
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */
package spp.clabel;

import graph.base.INetLink;
import graph.base.INetNode;

import java.util.List;

public interface ILabel {
    // region Property
    INetLink getLink();

    int getLinkID();

    int getNodeID();

    ILabel getPrevious();

    // endregion

    // region Methods
    void replacePrevious(ILabel pLabel);

    /**
     * 判别输入的路段是不是含有相同的节点序列
     * @param pInPath
     * @return
     */
    boolean isSamePath(ILabel pInPath);

    boolean isNodeInPath(int iNodeID);

    /**
     * 将路径按照起始节点到终止节点的顺序转为String
     * @return
     */
    String toPathString();

    /**
     * 将路径按照终止节点到起始节点的顺序转为String
     * @return
     */
    String toReversePathString();

    List<Integer> getNodeIDs();

    List<Integer> getReverseNodeIDs();

    List<Integer> getLinksIDs();

    List<Integer> getReverseLinkIDs();

    // endregion
}
