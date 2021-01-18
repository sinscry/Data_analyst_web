#### Data_analyst_web

* 初步逻辑:
	* 基本资料:
		* 服务器地址: 120.78.209.24
		* 域名:sinscry.space
		* 股票数据来源:
			* 可选债:https://www.jisilu.cn/data/cbnew/#cb
			* 新浪股票api:https://www.jianshu.com/p/108b8110a98c
		* 远程MySql数据库的数据表: stock_usr.usrs;
	0. 当前目标:
		* 构建选股网站（www.sinscry.space:80/stock）:
			* 界面用materilize实现
			1. 显示可转债按正转比排序：(完成)
			2. 显示本人选股和价格：(进行中)
			    1. MySQL数据库的stock_usr.usrs记载数据:
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
				   * 删除数据:`delete from stock_usr.usrs where usr=“测试”`
				   INSERT INTO stock_usr.usrs (usr,stock_id,stock_nm,oprice) VALUES   ("意愿","sh600000","浦发银行",1);
				2. 使用mybatis查询数据
					* name,stock_id,stock_nm,oprice在MySQL数据库取
					* sprice(现价)通过新浪股票api:`String sprice=Client.get("http://hq.sinajs.cn/list="+stock_id).split(",")[3];`
					* 构建目标json:
						```
						//查询结构
						//String name='梁鸿振'
						//String stock_list = "[{'stock_id':'sh600000','stock_nm':'浦发银行','sprice':'9.72','oprice':'9.71'}]";
						String Result_json="[{'name':'"+name+"','stock_list':"+stock_list+"}]";
						JSONArray jsonArray = JSONArray.parseArray(Result_json);
						return jsonArray.toString();
						```
				3. 添加持仓数据功能:(完成)
				4. 删除持仓数据功能:(完成)
			3. 添加访问日志
			4. 调整添加的位置（简单）
			5. 添加点评区
			6. 到目标股价发邮箱提醒
					
				
				
				
	1. springmvc构建后端，上传excel文件，并导入MySQL库里：
		1. 根据excel名构建MySQL表：
			1. 判断是否重名，是否要覆盖
	
	