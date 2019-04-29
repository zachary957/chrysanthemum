package register;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import register.impl.MyMapperProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@RegisterMapper
public interface MyMapper<T> extends Mapper<T> {

    @SelectProvider(type = MyMapperProvider.class, method = "dynamicSQL")
    List<T> selectAll();

}
