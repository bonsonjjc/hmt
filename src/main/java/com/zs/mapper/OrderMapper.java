package com.zs.mapper;

import com.zs.model.DayStatistics;
import com.zs.model.Order;
import com.zs.vo.OrderQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {

    List<Order> list(OrderQuery query);

    Order findByIdOrFno(@Param("fid") String fid, @Param("fno") String fno);

    float totalMoney(OrderQuery query);

    List<DayStatistics> statistics(OrderQuery query);

    void add(Order order);

    int update(Order order);
}
