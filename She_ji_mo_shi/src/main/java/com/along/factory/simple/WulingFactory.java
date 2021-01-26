package com.along.factory.simple;

/**
 * @author yufuxin
 * @version 1.0
 * @date 2020/12/31 17:10
 */
public class WulingFactory implements CarFactory{
    @Override
    public Car getCar() {
        return new Wuling();
    }
}
