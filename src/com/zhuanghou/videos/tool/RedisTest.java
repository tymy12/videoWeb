package com.zhuanghou.videos.tool;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by duhui on 2017/12/1.
 */
public class RedisTest {

        public static void main(String[] args) {
            //连接本地的 Redis 服务
//            Jedis jedis = new Jedis("localhost",7005);
//            System.out.println("连接成功");
//            //查看服务是否运行
//            System.out.println("服务正在运行: "+jedis.ping());
//            jedis.set("666","hello6");

            HostAndPort hostAndPort = new HostAndPort("localhost",7005);
            Set<HostAndPort> hostAndPortSet = new HashSet<>();
            hostAndPortSet.add(hostAndPort);
            JedisCluster jediss = new JedisCluster(hostAndPortSet);

            //jediss.set("111","666666");
            String s= jediss.get("111") ;
            System.out.println(s);





        }

}
