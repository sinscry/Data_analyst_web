package Convertible_bond.controller;

import Convertible_bond.pojo.Client;
import Convertible_bond.pojo.MyUser;
import Convertible_bond.pojo.Mybatis_mysql;
import com.alibaba.fastjson.JSONArray;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class StockIndexController {
    @Autowired
    private Client Client;

    @Autowired
    private Mybatis_mysql Mybatis_mysql;

    @RequestMapping("/stock")
    public String stock(){
        return "可转债";
    }

    @RequestMapping("/stock_json")
    @ResponseBody
    @CrossOrigin
    public String stock_json() throws IOException {
        //后台Http请求转发解决跨域问题
        return Client.get("https://www.jisilu.cn/data/cbnew/cb_list/?___jsl=LST___t=1608430043802");
    }

    @RequestMapping("/usr_json")
    @ResponseBody
    @CrossOrigin
    public String usr_json() throws IOException {
        //获取用户名数组
        List<String> name_list = Mybatis_mysql.selectAllName();

        List<String> Result_list=new ArrayList<String>();
        //根据用户名构建Result_list:[{'name':'"+name+"','stock_list':"+stock_list+"}]
        for (String name:name_list){
            //根据用户名获取信息
            List<MyUser> usr_list=Mybatis_mysql.selectByName(name);
            List<String> stock_list=new ArrayList<String>();
            //根据每股代码构建stock_list
            for (MyUser usr:usr_list){
                String sprice=Client.get("http://hq.sinajs.cn/list="+usr.stock_id).split(",")[3];
                String stock_li = "{'stock_id':'"+usr.stock_id+"','stock_nm':'"+usr.stock_nm+"','sprice':'"+sprice+"','oprice':'"+usr.oprice+"'}";
                stock_list.add(stock_li);
            }
            String Result_li = "{'name':'"+name+"','stock_list':"+stock_list+"}";
            Result_list.add(Result_li);
        }
        JSONArray jsonArray = JSONArray.parseArray(Result_list.toString());
        return jsonArray.toString();
    }




    @RequestMapping("/test")
    @ResponseBody
    @CrossOrigin
    public String test() throws IOException {
        return "t";
    }



}
