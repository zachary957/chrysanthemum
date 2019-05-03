package com.eutopia.register;

import com.eutopia.register.impl.MyMapperProvider;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@RegisterMapper
public interface MyMapper<T> extends Mapper<T> {

    @SelectProvider(type = MyMapperProvider.class, method = "dynamicSQL")
    List<T> selectAll();
}
