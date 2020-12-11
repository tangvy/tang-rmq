package com.tangv.rmq.model;

import lombok.Data;

/**
 * author:   tangwei
 * Date:     2020/12/11 17:54
 * Description:
 */

@Data
public class OrderStep {

    private long orderId;

    private String desc;

    @Override
    public String toString() {
        return "OrderStep{" +
                "orderId=" + orderId +
                ", desc='" + desc + '\'' +
                '}';
    }


}