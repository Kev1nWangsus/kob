package com.botduel.botrunningsystem.service.impl;

import com.botduel.botrunningsystem.service.BotRunningService;
import com.botduel.botrunningsystem.service.impl.utils.BotPool;
import org.springframework.stereotype.Service;

@Service
public class BotRunningServiceImpl implements BotRunningService {
    public final static BotPool BOT_POOL = new BotPool();

    @Override
    public String addBot(Integer userId, String botCode, String input) {
        BOT_POOL.addBot(userId, botCode, input);
        return "add bot success";
    }
}
