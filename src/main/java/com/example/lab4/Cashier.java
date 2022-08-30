package com.example.lab4;

import org.springframework.web.bind.annotation.*;

import static java.lang.Math.*;

@RestController
public class Cashier {

    @RequestMapping(value = "/getChange/{money}", method = RequestMethod.GET)
    public Change getChange(@PathVariable("money") int money){
        Change ch = new Change();
        ch.setB1000(Math.round(money/1000));
        money = money - (ch.getB1000()*1000);
        ch.setB500(Math.round(money/500));
        money = money - (ch.getB500()*500);
        ch.setB100(Math.round(money/100));
        money = money - (ch.getB100()*100);
        ch.setB20(Math.round(money/20));
        money = money - (ch.getB20()*20);
        ch.setB10(Math.round(money/10));
        money = money - (ch.getB10()*10);
        ch.setB5(Math.round(money/5));
        money = money - (ch.getB5()*5);
        ch.setB1(money);
        return ch;
    }
}
