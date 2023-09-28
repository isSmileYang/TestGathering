package com.multiTest.demo.seckillservice;

import com.multiTest.demo.model.AtomicStock;
import com.multiTest.demo.model.Entity.MaskOrder;
import com.multiTest.demo.model.Entity.Masks;
import com.multiTest.demo.model.Entity.Purchaser;
import com.multiTest.demo.model.SecKillEnum;
import com.multiTest.demo.mapper.SecKillMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class SecKillService {

    @Autowired
    private RedisCacheHandle redisCacheHandle;

    @Autowired
    private SecKillMapper secKillMapper;

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Autowired
    private AtomicStock atomicStock;

    /**
     * 利用MySQL的update行锁实现悲观锁
     * @param paramMap
     * @return
     */
    @Transactional
    public SecKillEnum handleByPessLockInMySQL(Map<String, Object> paramMap) {
        RedisProperties.Jedis jedis = redisCacheHandle.getJedis();
        MaskOrder record;

        Integer userId = (Integer) paramMap.get("userId");
        Integer productId = (Integer)paramMap.get("productId");
        Purchaser user = secKillMapper.getUserById(userId);
        Masks product = secKillMapper.getProductById(productId);

        String hasBoughtSetKey = SecKillUtils.getRedisHasBoughtSetKey(product.getProductName());
        //判断是否重复购买
        boolean isBuy = jedis.sismember(hasBoughtSetKey, user.getId().toString());
        if (isBuy){
            log.error("用户:"+user.getUsername()+"重复购买商品"+product.getProductName());
        }
        boolean secKillSuccess = secKillMapper.updatePessLockInMySQL(product);
        if (!secKillSuccess){
            log.error("商品:"+product.getProductName()+"库存不足!");
        }

        long result = jedis.sadd(hasBoughtSetKey,user.getId().toString());
        if (result > 0){
            record = new MaskOrder(null,user,product,SecKillEnum.SUCCESS.getCode(),SecKillEnum.SUCCESS.getMessage(),new Date());
            log.info(record.toString());
            boolean insertFlag =  secKillMapper.insertRecord(record);
            if (insertFlag){
                log.info("用户:"+user.getUsername()+"秒杀商品："+product.getProductName()+"成功!");
                return SecKillEnum.SUCCESS;
            }else {
                log.error("系统错误!");

            }
        }else {
            log.error("用户:"+user.getUsername()+"重复秒杀商品"+product.getProductName());
        }
    }

}

