package com.lcw.one.sys.dao;

import com.lcw.one.sys.entity.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CityMapper {

    @Select("SELECT * FROM sys_area WHERE type = #{type}")
    Area findByState(@Param("type") String type);

}