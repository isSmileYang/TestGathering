# 
 ### 1. 口罩抢购模型的实现与测试
1.实现个人云服务器中高并发实际场景的搭建并进行压力测试
2.利用锁，CAS与Redis缓存等方案优化抢购模型处理性能


### 2.多线程并发的各种demo
逐步深入并发编程的问题，结合书本知识完善Concurrency实际场景的优化思路
1.Synchronized
2.DeadLock
3.ReentrantLock
4.CountDownLatch and Semaphore
5.serials of ReentrantLock
6.fairSync or unfairSync
# 压测报告
## 使用相同user，相同product
>### pessLockInMySQL
>#### 利用MySQL的update行锁实现悲观锁。
![](file:///C:/Users/yz/Documents/My%20Knowledge/temp/82984fae-351a-41ed-b8c8-52502b9d2365/128/index_files/d03f60e6-45d6-4af1-a9fb-1c9ed78522c3.png)
![](index_files/70aee250-60d3-40d7-9f3c-ceeb78362dae.png)

>### baseOnRedisWatch
>#### 使用Redis作为原子计数器

![](index_files/5cd12823-85f6-42af-ba5b-2c69e42f1239.png)

>### baseOnAtomicInteger
>#### 基于AtomicInteger的CAS机制

![](index_files/dafa1c98-dd93-4bb4-8cc2-2642a1521f37.png)

>### posiLockInMySQL
>#### MySQL加字段version实现乐观锁。

![](index_files/d8029424-9c62-4ca8-9a0f-d85e2c297422.png)


## 使用不同user，不同product
>### pessLockInMySQL
>#### 利用MySQL的update行锁实现悲观锁。


>### posiLockInMySQL
>#### MySQL加字段version实现乐观锁。
![](index_files/7071ada0-a3f3-4f7a-b1b5-eea247442d20.png)
![](index_files/3fed67ac-2bd6-4a8a-80d9-5eba137ddbd0.png)

>### baseOnRedisWatch
>#### 使用Redis作为原子计数器
![](index_files/0a9f281a-c928-4cd2-a635-28e39ad7f33d.png)
![](index_files/fae07497-3aea-4613-a6f1-a4989053cb4b.png)

>### baseOnAtomicInteger
>#### 基于AtomicInteger的CAS机制
![](index_files/919ada73-4d54-41a1-92ba-8da22fded0f4.png)
![](index_files/87911d2d-1ae4-40bd-a7ef-b6da45ac2cca.png)
