package com.github.tanqizhou.share.slogan.test.mvc.order.dao;

import com.github.tanqizhou.share.slogan.test.mvc.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/04/22/16:08
 * @Description:
 */
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
