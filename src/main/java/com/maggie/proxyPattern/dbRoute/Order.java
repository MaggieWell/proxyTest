package com.maggie.proxyPattern.dbRoute;

/**
 * Description:
 * author:MaggieHao
 * Date:2019-05-17
 * Time:22:43
 */
public class Order {
    private Object orderInfo;
    private Long createTime;//订单的创建时间进行按年分库
    private String id;

    public Object getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(Object orderInfo) {
        this.orderInfo = orderInfo;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
