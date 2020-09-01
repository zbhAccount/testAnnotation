package com.annotation.study.user.handle;

import com.annotation.study.user.service.IGirlService;
import com.annotation.study.user.service.impl.IGirlServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
public class GirlHandle implements AbstractHandle{
    private static final Logger LOGGER = LoggerFactory.getLogger(GirlHandle.class);

    @Autowired
    IGirlService iGirlService;

    @Override
    public Integer handle() {
        // 获取数量
        LOGGER.info("handle获取数量");
        return iGirlService.getCount();
    }
}
