package com.bjpowernode;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App2
{
    public static void main( String[] args )
    {
        JedisPool pool=null;
       try{
           pool=RedisUtils.open("192.168.229.129",6379);
           Jedis jedis=pool.getResource();
           jedis.flushAll();
           jedis.hset("website","baidu","www.baidu.com");
           String str1=jedis.hget("website","baidu");
           System.out.println(str1);
           System.out.println("------------------------------------");

           Map<String,String> map=new HashMap <>();
           map.put("id","a001");
           map.put("name","zs");
           map.put("age","22");
           jedis.hmset("student",map);
           List<String> sList=jedis.hmget("student","id","name","age");
           for(String str:sList){
               System.out.println(str);
           }
       }catch (Exception e){
           e.printStackTrace();;
       }finally{
           RedisUtils.close();
       }
    }
}
