#### Data_analyst_web

* 初步逻辑:
	0. 当前目标:
		* 构建选股网站:
			1. 显示可转债按正转比排序 : 
				* 用json完成
			2. 显示本人选股和价格:
			    1. 数据库stock_usr记载数据:
			        * `create database stock_usr`
			        * 数据结构:(usr,stock_id,stock_nm,oprice)//对应(用户，代码，股名，购入价)
		            * 建表	        
                    ```
                    CREATE TABLE IF NOT EXISTS stock_usr.usrs(
                        usr varchar(30),
                        stock_id varchar(20),
                        stock_nm varchar(20),
                        oprice double,
                        update_time timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
                        PRIMARY KEY (`id`),
                        KEY `key_usr` (`usr`)
                    )
                    ```   
                   * 插入数据:`INSERT INTO stock_usr.usrs (usr,stock_id,stock_nm,oprice) VALUES   ("梁鸿振","sh600000","浦发银行",9.717);`
				2. 使用mybatis查询数据
					* 远程服务器:`mysql -h sinscry.space -u sinscry -p`
				
				
				
	1. springmvc构建后端，上传excel文件，并导入MySQL库里：
		1. 根据excel名构建MySQL表：
			1. 判断是否重名，是否要覆盖
	
	