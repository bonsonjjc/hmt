package com.zs.mapper;

import com.zs.model.Line;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayMapper {
    List<Line> findByFeid(String feid);
}
