package com.wbh.emall.api.mapper;

import com.wbh.emall.user.common.mapper.EmallGoodsMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author WBH
 * @since 2022/2/24
 */
public class EmallGoodsMapperTest {
    private static EmallGoodsMapper mapper;
    
    @BeforeEach
    public static void setUpMybatisDatabase() {
        SqlSessionFactory builder = new SqlSessionFactoryBuilder().build(EmallGoodsMapperTest.class.getClassLoader().getResourceAsStream("mybatisTestConfiguration/EmallGoodsMapperTestConfiguration.xml"));
        //you can use builder.openSession(false) to not commit to database
        mapper = builder.getConfiguration().getMapper(EmallGoodsMapper.class, builder.openSession(true));
    }
    
    @Test
    public void testSelectByTypeId() {
        // System.out.println(mapper.selectByTypeId(1));
    }
}
