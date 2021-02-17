### 为什么要使用springcache，它解决了什么问题
springcache是spring3.1版本发布出来的，他是对缓存进行封装和抽象，通过在方法上使用annotation注解就可以拿到缓存结果。正式因为用了annotation，所以它解决了业务代码和缓存代码的耦合度问题。即在不侵入业务代码的基础上让现有代码即可支持缓存，它让开发人员无感知的使用了缓存
对于redis的缓存，springcache只支持String，其他的Hash、List、set、Zset都不支持
### 二、在启动类中将缓存功能打开，添加@EnableCaching，注解
#### 步骤1：pom文件加入依赖包

#### 步骤2：配置文件，加入redis配置信息
#### 步骤3：开启缓存配置，序列化
