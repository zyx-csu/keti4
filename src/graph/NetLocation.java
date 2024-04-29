/**
 * -*- coding: utf-8 -*-
 * @File    :   NetLocation.java
 * @Time    :   2024/4/26 22:33
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */
package graph;

import graph.base.INetLink;
import graph.base.INetNode;

import java.util.Objects;

public class NetLocation {
    private INetLink netLink;
    private double iLRS;
    private Coordinate pNetXY;

    // region Construction
    public NetLocation(INetLink pNetLink, double iLRS) {
        if (pNetLink == null) {
            throw new NullPointerException("NetLocation encounter an error: pNetLink should not be null");
        }
        if (iLRS < 0 || iLRS > 1) {
            throw new NullPointerException("NetLocation encounter an error: iLRS should between (0,1) : " + iLRS);
        }
        this.netLink = pNetLink;
        this.iLRS = iLRS;
    }
    // endregion

    // region Properties
    public INetLink getINetLink() {
        return netLink;
    }

    public double getiLRS() {
        return iLRS;
    }

    public void setiLRS(double iLRS) {
        if (iLRS < 0 || iLRS > 1) {
            throw new IllegalArgumentException("NetLocation encounter an error: iLRS should between (0,1) : " + iLRS);
        }
        this.iLRS = iLRS;
    }

    public Coordinate getpNetXY() {
        return pNetXY;
    }

    public void setpNetXY(Coordinate pNetXY) {
        this.pNetXY = pNetXY;
    }

    // endregion

    // region Methods
    public boolean isValid() {
        return true;
    }

    public boolean isInstersection() {
        if (iLRS == 0 || iLRS == 1) {
            return true;
        }
        return false;
    }

    /**
     * 判断一个location是否是边界点
     * @param pInteresection
     * @return
     */
    public boolean isIntersection(INetNode pInteresection) {
        if (iLRS == 0 || iLRS == 1) {
            if (iLRS == 0) {
                pInteresection = this.netLink.getTailNode();
            } else {
                pInteresection = this.netLink.getHeadNode();
            }
            return true;
        } else {
            pInteresection = null;
        }
        return false;
    }

    public boolean hasCoordinate() {
        return pNetXY == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetLocation that = (NetLocation) o;
        return Double.compare(that.iLRS, iLRS) == 0 && Objects.equals(netLink, that.netLink) && Objects.equals(pNetXY, that.pNetXY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(netLink, iLRS, pNetXY);
    }

    @Override
    public String toString() {
        return this.iLRS +"@" + this.netLink.toString();
    }

// endregion
}
