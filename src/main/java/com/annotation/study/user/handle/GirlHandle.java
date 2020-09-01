package com.annotation.study.user.handle;

import com.annotation.study.user.model.User;
import com.annotation.study.user.service.IGirlService;
import com.annotation.study.user.service.impl.IGirlServiceImpl;
import com.annotation.study.user.vo.AsyncContext;
import com.annotation.study.user.vo.AsyncQueryVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;


@Component
public class GirlHandle implements AbstractHandle{
    private static final Logger LOGGER = LoggerFactory.getLogger(GirlHandle.class);

    @Autowired
    IGirlService iGirlService;

    @Override
    public void handle(AsyncQueryVo asyncQueryVo) {
        // 获取数量
        LOGGER.info("handle获取数量");
        User user = (User)AsyncContext.getInstance().get().get("param");
        Integer num = iGirlService.getCount(user);
        Map<String, Integer> resultMap = (Map<String, Integer>)asyncQueryVo.getT();
        resultMap.put("girlCount", num == null ? 0 : num);
    }
}
