/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SpringBootMockMvcTest
 * Author:   HuangTaiHong
 * Date:     2018-04-11 下午 7:31
 * Description: SpringBoot Mock测试
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.roberto.growth.process.uc.test;

import org.springframework.beans.factory.annotation.Value;
import roberto.growth.process.uc.service.UCServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 〈一句话功能简述〉<br> 
 * 〈SpringBoot Mock测试〉
 *
 * @author HuangTaiHong
 * @create 2018-04-11 
 * @since 1.0.0
 */
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UCServiceApplication.class)
public class SpringBootMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @Value("${roberto}")
    private String username;
    @Test
    public void testBaiDu() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello")
                .accept(MediaType.APPLICATION_ATOM_XML))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("HELLO WORLD")));
    }

    @Test
    public void testSpringCloudConfig() {
        System.out.println(username);
    }
}