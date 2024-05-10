package com.sky.mapper;

import com.sky.entity.OrderDetail;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDetailMapper {

    void insertBatch(List<OrderDetail> orderDetails);

    /**
     * 根据订单id查询订单明细
     * @param id
     * @return
     */
    @Select("select * from order_detail where order_id=#{id}")
    List<OrderDetail> getByOrderId(Long id);
}