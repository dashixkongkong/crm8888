package com.bjpowernode;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import java.util.List;

public class App3
{
    public static void main( String[] args )
    {
        JedisPool pool=null;
       try{
           pool=RedisUtils.open("192.168.229.129",6379);
           Jedis jedis=pool.getResource();
           jedis.flushAll();
           Transaction tran=jedis.multi();
           tran.set("str1","aaa");
           tran.set("str2","bbb");
           //事务处理
           List<Object> oList=tran.exec();
           System.out.println("事务结果打印");
           for(Object o:oList){
               System.out.println(o);
           }
       }catch (Exception e){
           e.printStackTrace();;
       }finally{
           RedisUtils.close();
       }
    }
}
