/**
 * -*- coding: utf-8 -*-
 * @File    :   NetworkType.java
 * @Time    :   2024/4/26 22:33
 * @Author  :   Huanghuihuang && Zhongyuxiang
 * @Email   :   9027575@qq.com
 */
package graph.base;

public enum NetworkType {
    NT_Static(1),
    NT_Dynamic(2),
    NT_Stochastic(3),
    NT_Normal(4),
    NT_StochasticDyanmic(5),
    NL_StoDynamicSpeed(6);

    private int value;
    NetworkType(int i) {
        this.value = i;
    }
}
