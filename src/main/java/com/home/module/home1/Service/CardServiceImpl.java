package com.home.module.home1.Service;

import com.home.Serach.Result;
import com.home.module.home1.HiberMapper.CardMapper;
import com.home.module.home1.entity.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.X
 **/
@Service
public class CardServiceImpl {
    @Autowired
    private CardMapper cardMapper;

    /**
     * 新增
     * @param card
     * @return
     */
    public Result<Card> InsertCard(Card card){
        cardMapper.saveAndFlush(card);
        return new Result<Card>(Result.ResultState.SUCCESS_RESPONSE,"Insert Success",card);
    }
}
