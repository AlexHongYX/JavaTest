package com.xiaoaxiao.test.thread_test.production_consumer_model.PcByQueue;

/**
 * Created by xiaoaxiao on 2019/7/20
 * Description: 商品类
 */
public class Goods {

    private final String id;
    private final String goodName;

    public Goods(String id, String goodName) {
        this.id = id;
        this.goodName = goodName;
    }

    public String getId() {
        return id;
    }

    public String getGoodName() {
        return goodName;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id='" + id + '\'' +
                ", goodName='" + goodName + '\'' +
                '}';
    }
}
