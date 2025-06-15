package com.zengrui.zblog.server.mapper;

import com.zengrui.zblog.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insert(User user);

    Boolean isUserNameExists(String username);

    User getUserByUserNameAndPassword(User user);

    Boolean isUserPasswordCorrect(User user);
}
