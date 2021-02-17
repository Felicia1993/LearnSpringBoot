# spring boot和redis集成
技术栈：springboot mybatis swagger

### 步骤一：pom文件引入redis依赖包
```

```
### 步骤二：配置文件加入redis配置信息

### 演示redisTemplate的增删改

### 优化重写Redis的序列化，改为Json方式
为什么要重写Redis序列化方式，使用Json呢？
因为RedisTemplate默认使用的是JdkSerializationRedisSerializer，会出现两个问题
1.被序列化的对象必须实现Serializable接口
2.被序列化会出现乱码，导致value值可读性差