# edu_online
参照尚硅谷-谷粒学院完成的一个基于SpringBoot+Vue的在线教育项目的后端服务，用于学习接近于实际环境中的各种业务开发

## **在线教育平台-后端服务** 

   前台用户界面地址：https://github.com/ekesaiting/edu_online_front

   后台管理界面地址：https://github.com/ekesaiting/edu_online_admin

  参照谷粒学院完成的一个比较完整的在线教育平台项目，采用前后端分离开发，由三部分组成：前端用户界面，后台管理界面，后端服务。后台服务采用分布式架构，包含课程管理，订单管理，用户管理，权限管理，数据统计，云服务与支付等各项独立模块
  * 前端用户界面采用Nuxt解决前后端分离的SEO问题
  * 使用Nacos作为服务注册与配置中心，Gateway作为网关,便于统一管理与配置
  * 使用Mybatis-Plus简化开发，便于实现乐观锁，逻辑删除等功能
  * 接入阿里云OSS,视频点播，短信服务，使用微信支付实现支付功能
  * 采用SpringSecurity+JWT实现用户登入与授权，并整合微信登入
  * 使用Redis作为热点数据缓存，Canal作为数据库同步工具。
  * 使用Gitee+Docker+Jenkins实现持续集成与部署
