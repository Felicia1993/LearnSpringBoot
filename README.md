## 什么是SpringBoot 
springBoot是伴随着Spring4.0诞生的，于2014年4月，发布了Spring Boot 1.0.0 SpringBoot就是一个内嵌Web容器(tomcat/jetty)的可执行程序(jar)的框架。 

你开发的web应用不需要作为war包部署到web容器中，而是作为一个可执行程序(jar)，启动时把web服务器配置好，加载起来。

## SpringBoot推出之前，编码存在什么问题？
1.大量的XML文件在项目（SpringMVC）中，配置相当繁琐 

2.整合第三方框架的配置问题 

3.低效的开发效率和部署效率等问题

## docker搭建集群命令

docker create --name redis-node-1 --net host --privileged=true -v /Users/Java/files/redis-node-1:/data redis:5.0.7 --cluster-enabled yes --appendonly yes --port 6381

docker create --name redis-node-2 --net host --privileged=true -v /Users/Java/files/redis-node-2:/data redis:5.0.7 --cluster-enabled yes --appendonly yes --port 6382

docker create --name redis-node-3 --net host --privileged=true -v /Users/Java/files/redis-node-3:/data redis:5.0.7 --cluster-enabled yes --appendonly yes --port 6383

docker create --name redis-node-4 --net host --privileged=true -v /Users/Java/files/redis-node-4:/data redis:5.0.7 --cluster-enabled yes --appendonly yes --port 6384

docker create --name redis-node-5 --net host --privileged=true -v /Users/Java/files/redis-node-5:/data redis:5.0.7 --cluster-enabled yes --appendonly yes --port 6385

docker create --name redis-node-6 --net host --privileged=true -v /Users/Java/files/redis-node-6:/data redis:5.0.7 --cluster-enabled yes --appendonly yes --port 6386

docker create//创建容器的命令

--name redis-node-1 //容器的名字 例如：redis-node-1

--privileged=true //docker容器 获取数组机root权限

-v /Users/Java/files/redis-node-1:/data //容器的data目录 映射到宿主机/Users/Java/files/redis-node-1:/data

redis:5.0.7 //redis镜像名称和版本

--cluster-enabled yes //redis.conf的配置：开启redis集群

--appendonly yes //redis.conf的配置：开启数据持久化

--port 6381 //redis.conf的配置：redis端口号

## 启动容器
docker start redis-node-1 redis-node-2 redis-node-3 redis-node-4 redis-node-5 redis-node-6

## 查看容器
docker ps

## 进入节点redis-node-1容器中
docker exec -it redis-node-1 /bin/bash

参数create表示创建一个新的集群， --replicas 1 表示为每个master创建一个slave
redis-cli --cluster create 127.0.0.1:6381  127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 127.0.0.1:6385 127.0.0.1:6386 --cluster-replicas 1

使用docker start启动容器，进入容器内部报错could not connect

解决方案：使用docker run 绑定IP和端口

docker inspect 44de1b0b5312(容器ID)查看容器自己的内部网络和ip地址

docker run --name redis-node-4 -d -p 127.0.0.1:6384:6384 --net host --privileged=true -v /Users/Java/files/redis-node-4:/data redis:5.0.7 --cluster-enabled yes --appendonly yes --port 6384
docker run --name redis-node-5 -d -p 127.0.0.1:6385:6385 --net host --privileged=true -v /Users/Java/files/redis-node-5:/data redis:5.0.7 --cluster-enabled yes --appendonly yes --port 6385
docker run --name redis-node-6 -d -p 127.0.0.1:6386:6386 --net host --privileged=true -v /Users/Java/files/redis-node-6:/data redis:5.0.7 --cluster-enabled yes --appendonly yes --port 6386
```
>>> Performing hash slots allocation on 6 nodes...
Master[0] -> Slots 0 - 5460
Master[1] -> Slots 5461 - 10922
Master[2] -> Slots 10923 - 16383
Adding replica 127.0.0.1:6385 to 127.0.0.1:6381
Adding replica 127.0.0.1:6386 to 127.0.0.1:6382
Adding replica 127.0.0.1:6384 to 127.0.0.1:6383
>>> Trying to optimize slaves allocation for anti-affinity
[WARNING] Some slaves are in the same host as their master
M: ff85d991c2a6af2283f8ffe0dfb5a036975fed63 127.0.0.1:6381
   slots:[0-5460] (5461 slots) master
M: fe25154b7deb296fafba1afefa76d438e7deb4c0 127.0.0.1:6382
   slots:[5461-10922] (5462 slots) master
M: 96a9b20acd29c43c1d3c20adf0dd36a9bc82c4c1 127.0.0.1:6383
   slots:[10923-16383] (5461 slots) master
S: 90187399b90b6eb719e9adfbd46b1a94b4ec2429 127.0.0.1:6384
   replicates 96a9b20acd29c43c1d3c20adf0dd36a9bc82c4c1
S: d60e952ba67b14922cc747c6bab0375fba5c5a8e 127.0.0.1:6385
   replicates ff85d991c2a6af2283f8ffe0dfb5a036975fed63
S: ea598a0ffdd5fe4af99f41395ced879e66212bf2 127.0.0.1:6386
   replicates fe25154b7deb296fafba1afefa76d438e7deb4c0
Can I set the above configuration? (type 'yes' to accept): yes
>>> Nodes configuration updated
>>> Assign a different config epoch to each node
>>> Sending CLUSTER MEET messages to join the cluster
Waiting for the cluster to join
.....
>>> Performing Cluster Check (using node 127.0.0.1:6381)
M: ff85d991c2a6af2283f8ffe0dfb5a036975fed63 127.0.0.1:6381
   slots:[0-5460] (5461 slots) master
   1 additional replica(s)
S: 90187399b90b6eb719e9adfbd46b1a94b4ec2429 127.0.0.1:6384
   slots: (0 slots) slave
   replicates 96a9b20acd29c43c1d3c20adf0dd36a9bc82c4c1
M: fe25154b7deb296fafba1afefa76d438e7deb4c0 127.0.0.1:6382
   slots:[5461-10922] (5462 slots) master
   1 additional replica(s)
S: ea598a0ffdd5fe4af99f41395ced879e66212bf2 127.0.0.1:6386
   slots: (0 slots) slave
   replicates fe25154b7deb296fafba1afefa76d438e7deb4c0
M: 96a9b20acd29c43c1d3c20adf0dd36a9bc82c4c1 127.0.0.1:6383
   slots:[10923-16383] (5461 slots) master
   1 additional replica(s)
S: d60e952ba67b14922cc747c6bab0375fba5c5a8e 127.0.0.1:6385
   slots: (0 slots) slave
   replicates ff85d991c2a6af2283f8ffe0dfb5a036975fed63
[OK] All nodes agree about slots configuration.
>>> Check for open slots...
>>> Check slots coverage...
[OK] All 16384 slots covered.

```

### 使用cluster info命令查看集群状态
redis-cli -h 127.0.0.1 -p 6381 -c

cluster info

```
cluster_state:ok                    #集群状态
cluster_slots_assigned:16384        #被分配槽的位数
cluster_slots_ok:16384              #正确分配的槽位
cluster_slots_pfail:0
cluster_slots_fail:0
cluster_known_nodes:6               #当前节点数
cluster_size:3
cluster_current_epoch:6
cluster_my_epoch:1
cluster_stats_messages_ping_sent:108
cluster_stats_messages_pong_sent:95
cluster_stats_messages_sent:203
cluster_stats_messages_ping_received:90
cluster_stats_messages_pong_received:108
cluster_stats_messages_meet_received:5
cluster_stats_messages_received:203

```
### 使用cluster nodes命令查看节点状态
cluster nodes
```
90187399b90b6eb719e9adfbd46b1a94b4ec2429 127.0.0.1:6384@16384 slave 96a9b20acd29c43c1d3c20adf0dd36a9bc82c4c1 0 1612431689000 4 connected
ff85d991c2a6af2283f8ffe0dfb5a036975fed63 127.0.0.1:6381@16381 myself,master - 0 1612431688000 1 connected 0-5460
fe25154b7deb296fafba1afefa76d438e7deb4c0 127.0.0.1:6382@16382 master - 0 1612431690282 2 connected 5461-10922
ea598a0ffdd5fe4af99f41395ced879e66212bf2 127.0.0.1:6386@16386 slave fe25154b7deb296fafba1afefa76d438e7deb4c0 0 1612431689000 6 connected
96a9b20acd29c43c1d3c20adf0dd36a9bc82c4c1 127.0.0.1:6383@16383 master - 0 1612431691302 3 connected 10923-16383
d60e952ba67b14922cc747c6bab0375fba5c5a8e 127.0.0.1:6385@16385 slave ff85d991c2a6af2283f8ffe0dfb5a036975fed63 0 1612431689268 5 connected
```