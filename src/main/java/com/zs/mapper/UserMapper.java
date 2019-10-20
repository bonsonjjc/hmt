package com.zs.mapper;

import com.zs.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User find(String account,String password);

    int password(String userId, String oldPassword,String newPassword );
}
