#### Data_analyst_web

* 初步逻辑:
	0. 当前目标:
		* 构建选股网站（www.sinscry.space:8080/stock）:
			* 界面用materilize实现
			1. 显示可转债按正转比排序 : 
				* 用json访问https://www.jisilu.cn/data/cbnew/#cb获取转债数据
			2. 显示本人选股和价格:
				* 新浪股票api接口:https://www.jianshu.com/p/108b8110a98c
				* 逻辑:用户嵌套
			    1. 数据库stock_usr记载数据:
					* 远程MySql:`mysql -h sinscry.space -u sinscry -p`
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
					* name,stock_id,stock_nm,oprice在数据库可取
					* sprice(现价):`String sprice=Client.get("http://hq.sinajs.cn/list="+stock_id).split(",")[3];`
					* 构建目标json:
						```
						//查询结构
						//String name='梁鸿振'
						//String stock_list = "[{'stock_id':'sh600000','stock_nm':'浦发银行','sprice':'9.72','oprice':'9.71'}]";
						String Result_json="[{'name':'"+name+"','stock_list':"+stock_list+"}]";
						JSONArray jsonArray = JSONArray.parseArray(Result_json);
						return jsonArray.toString();
						```
					
					
				
				
				
	1. springmvc构建后端，上传excel文件，并导入MySQL库里：
		1. 根据excel名构建MySQL表：
			1. 判断是否重名，是否要覆盖
	
	