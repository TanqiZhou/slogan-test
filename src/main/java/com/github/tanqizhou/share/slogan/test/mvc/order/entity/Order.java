package com.github.tanqizhou.share.slogan.test.mvc.order.entity;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/04/22/16:04
 * @Description:
 */
@Entity
@Slf4j
@ToString
@Data
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String goodsName;

    @Column
    private BigDecimal price;
}
