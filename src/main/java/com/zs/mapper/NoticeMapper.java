package com.zs.mapper;

import com.zs.model.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper {

    List<Notice> list();

    Notice findById(String  fid);
}
