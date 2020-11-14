package cn.it.jedis.test;

import cn.it.jedis.Utils.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolTest {

    /**
     * JedisPool的使用
     */
    @Test
    public void test(){
        //创建一个配置对象 jedisPoolConfig
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        config.setMaxIdle(10);

        //创建jedisPool连接池对象
        JedisPool jedisPool = new JedisPool(config,"localhost",6379);
        //获取连接
        Jedis jedis = jedisPool.getResource();

        jedis.set("hehe","haha");
        String hehe = jedis.get("hehe");
        System.out.println(hehe);

        //关闭 归还到连接池中
        jedis.close();
    }


    /**
     * 测试JedisPoolUtils的使用
     */
    @Test
    public void test1(){
        //通过连接池工具类获取
        Jedis jedis = JedisPoolUtils.getJedis();
        //使用
        jedis.set("hello","world");
        //关闭 归还连接池
        jedis.close();
    }
}
