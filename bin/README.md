# JEEFakeTmall
不使用任何框架 使用纯Java EE写的一个在线商城 上手项目，提前体会一下复制粘贴数据库模板代码的恐怖。  
是 http://how2j.cn/k/tmall-j2ee/tmall-j2ee-894/894.html 上的一个练手项目。  

至于为什么bean包里会有Hibernate的配置文件，那是因为手打Bean实在是受不了就用了Hibernate的反向工程来生成。
同时HibernateSessionFactory.java也只是一开始想体验一下Hibernate的魔力，该项目并未使用Hibernate ORM来操作数据库。
