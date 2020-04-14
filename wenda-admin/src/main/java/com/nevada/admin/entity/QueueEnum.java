package com.nevada.admin.entity;

/**
 * 消息队列枚举配置
 * Created by macro on 2018/9/14.
 */
public enum QueueEnum {


    QUEUE_ORDER_CANCEL("wenda.like.direct","wenda.like.save","wenda.like.save");

    /**
     * 交换名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private String name;
    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }

    public String getExchange() {
        return exchange;
    }

    public String getName() {
        return name;
    }

    public String getRouteKey() {
        return routeKey;
    }
}
