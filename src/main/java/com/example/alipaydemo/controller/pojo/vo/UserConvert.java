package com.example.alipaydemo.controller.pojo.vo;


import com.example.alipaydemo.controller.pojo.tUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);


    @Mappings({
            @Mapping(source = "password", target = "passWord")
    })
    UserVo userToUserVO(tUser user);
}
