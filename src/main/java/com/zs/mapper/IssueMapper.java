package com.zs.mapper;

import com.zs.model.Issue;
import com.zs.model.Notice;
import com.zs.vo.ListQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IssueMapper {

    List<Issue> list(ListQuery query);

    Issue findById(String fid);
}
