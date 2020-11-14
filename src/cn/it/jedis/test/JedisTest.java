package cn.it.jedis.test;

import cn.it.jedis.Utils.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {

    /**
     * 快速入门Jedis  和  jdbc很想
     */

    //1.字符串类型 String
    //方法：set get del

    //2.哈希类型
    //方法：hset hget hdel

    //3.列表类型list
    //方法：lpush rpush lpop rpop

    //4.集合类新set
    //方法：sadd srem smembers

    //5.有序集合类型sortedset
    //方法：zdd
    @Test
    public void test() {
        //1.获取连接
        Jedis jedis = new Jedis("localhost", 6379);
        //2.操作
        Set<String> keys = jedis.keys("*");
        System.out.println(keys);
        //3.关闭连接
        jedis.close();
    }


    /**
     * String数据结构操作
     */
    @Test
    public void test1() {
        //1.获取连接
        Jedis jedis = new Jedis(); //空参 默认值为 “localhost”，6379
        //2.操作
        //存储
        jedis.set("username", "wangwu");
        //获取
        String username = jedis.get("username");
        System.out.println(username);

        //可以使用setex()方法存储可指定过期时间的key value
        //将Key(activecode)：value(hehe)键值对存入redis，并且20秒后自动删除该键值对
        jedis.setex("activecode",20,"hehe");


        //3.关闭连接
        jedis.close();
    }


    /**
     * hash数据结构操作
     */
    @Test
    public void test2() {
        //1.获取连接
        Jedis jedis = new Jedis(); //空参 默认值为 “localhost”，6379
        //2.操作
        // 存储hash
        jedis.hset("user","name","lisi");
        jedis.hset("user","age","23");
        jedis.hset("user","gender","male");

        //获取
        Map<String, String> user = jedis.hgetAll("user");

        Set<String> keySet = user.keySet();
        for (String key:keySet
             ) {
            String value = user.get(key);
            System.out.println(key+":"+value);
        }

        //3.关闭连接
        jedis.close();
    }


    /**
     * list数据结构操作 有顺序 可重复
     */
    @Test
    public void test3() {
        //1.获取连接
        Jedis jedis = new Jedis(); //空参 默认值为 “localhost”，6379
        //2.操作
        jedis.del("mylist"); //删除
        //存储
        jedis.lpush("mylist","a","b","c");
        jedis.rpush("mylist","a","b","c");


        List<String> mylist = jedis.lrange("mylist", 0, -1);

        System.out.println(mylist);

        String elements1 = jedis.lpop("mylist");
        System.out.println("左边弹出元素："+elements1);

        String elements2 = jedis.rpop("mylist");
        System.out.println("右边弹出元素："+elements2);

        List<String> mylist1 = jedis.lrange("mylist", 0, -1);
        System.out.println(mylist1);

        //3.关闭连接
        jedis.close();
    }

    /**
     * set数据结构操作 无序，不可重复
     */
    @Test
    public void test4() {
        //1.获取连接
        Jedis jedis = new Jedis(); //空参 默认值为 “localhost”，6379
        //2.操作
        //set存储
        jedis.sadd("myset","java","php","c++");

        //set获取
        Set<String> myset = jedis.smembers("myset");
        System.out.println(myset);

        //3.关闭连接
        jedis.close();
    }


    /**
     * sortedset数据结构操作 无序，不可重复
     */
    @Test
    public void test5() {
        //1.获取连接
        Jedis jedis = new Jedis(); //空参 默认值为 “localhost”，6379
        //2.操作
        //sortedset存储
        jedis.zadd("mysortedset",80,"lisi");
        jedis.zadd("mysortedset",70,"wangwu");
        jedis.zadd("mysortedset",90,"zhangsan");

        //sortedset获取
        Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
        System.out.println(mysortedset);

        //3.关闭连接
        jedis.close();
    }

    /**
     * jedis连接池工具类使用
     */
    @Test
    public void test8() {

        //通过连接池工具类获取
        Jedis jedis = JedisPoolUtils.getJedis();


        //3. 使用
        jedis.set("hello", "world");


        //4. 关闭 归还到连接池中
        jedis.close();

    }

}
