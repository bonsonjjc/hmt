package com.zs.mapper;

import com.zs.model.Activity;
import com.zs.vo.ListQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper {
    List<Activity> list(ListQuery query);

    Activity findById(String fid);
}
