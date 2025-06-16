package com.zengrui.zblog.server.mapper;

import com.zengrui.zblog.pojo.dto.UserUpdateDTO;
import com.zengrui.zblog.pojo.entity.User;
import com.zengrui.zblog.pojo.vo.UserViewVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    void insert(User user);

    Boolean isUserNameExists(String username);

    User getUserByUserNameAndPassword(User user);

    Boolean isUserPasswordCorrect(User user);

    @Select("select id,username,avatar from user where id = #{userId}")
    UserViewVO getUserViewByUserId(Long userId);

    void updateUserInfo(UserUpdateDTO userUpdateDTO);
}
