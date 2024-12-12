package com.fdt.project.service;


import com.fdt.project.service.impl.UserInterfaceInfoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserInterfaceInfoServiceTest {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;
    @Test
    void testAddUserInterfaceInfo() {


        boolean result = userInterfaceInfoService.invokeCount(1L, 1L);
        Assertions.assertTrue(result);
    }
}
