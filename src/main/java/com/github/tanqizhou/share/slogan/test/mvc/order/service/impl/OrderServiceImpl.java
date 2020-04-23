package com.github.tanqizhou.share.slogan.test.mvc.order.service.impl;

import com.github.tanqizhou.share.slogan.test.mvc.order.dao.OrderRepository;
import com.github.tanqizhou.share.slogan.test.mvc.order.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Auther: TanqiZhou
 * @Date: 2020/04/22/16:10
 * @Description:
 */
@Slf4j
@Service
public class OrderServiceImpl {

    @Autowired
    OrderRepository orderRepository;

    @CacheEvict("order")
    public boolean addOrder(Order order){
        Order save = orderRepository.save(order);
        return save.getId() != 0;
    }

    @Cacheable(value = "order",cacheManager = "cacheManager")
    public Page<Order> listOrder(){
        Pageable pageable =  PageRequest.of(0,10, new Sort(Sort.Direction.ASC, "id"));
        Page<Order> all = orderRepository.findAll(pageable);
        return all;
    }
}
