分布式系统 约拍平台  
作者：胡毓淞 
注：jsp文件只是用来测试功能  
注：阿里云服务器已过期 接口文档已失效  

系统架构  
![image](https://github.com/EssentialsHys/photo/blob/master/images/约拍平台系统架构.png)  

功能介绍  
![image](https://github.com/EssentialsHys/photo/blob/master/images/function.png)  

dubbo-admin 查看服务注册情况和消费情况，注册中心使用zookeeper  
服务提供方  
![image](https://github.com/EssentialsHys/photo/blob/master/images/provider.png)  
服务消费方  
![image](https://github.com/EssentialsHys/photo/blob/master/images/consumer.png)  

因为是约拍平台，自然需要大量的图片，所以我们需要一个专门的文件服务器——FastDFS处理，但是FastDFS不支持http，我们无法访问图片的的url，所以需要Nginx来代理，提供http协议  
![image](https://github.com/EssentialsHys/photo/blob/master/images/ngx_fdfs.png)  
存储的图片  
![image](https://github.com/EssentialsHys/photo/blob/master/images/storage.png)  
url访问图片效果  
![image](https://github.com/EssentialsHys/photo/blob/master/images/http.png)  

Redis  
图片中的文件夹是key的冒号前缀，可以看到实现了缓存和分布式系统中Session的功能  
缓存数据（以查询摄影师为例）  
![image](https://github.com/EssentialsHys/photo/blob/master/images/redis.png)  
Session  
![image](https://github.com/EssentialsHys/photo/blob/master/images/redis.png)  
Cookie  
![image](https://github.com/EssentialsHys/photo/blob/master/images/redis.png)  
Cookie中保存Session的id，类似于JSESSIONID，作为Cookie和Session匹配的唯一标识，我们在Session中保存用户名  

跨设备进程通信——调用队友深度学习模型实现“智能换衣服”和“智能换背景”  
在队友电脑上使用花生壳内网穿透  
![image](https://github.com/EssentialsHys/photo/blob/master/images/NAT.png)  

数据库E-R图  
![image](https://github.com/EssentialsHys/photo/blob/master/images/MYSQL.png)  

