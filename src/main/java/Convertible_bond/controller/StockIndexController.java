package Convertible_bond.controller;

import Convertible_bond.pojo.Client;
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
        String name="梁鸿振";
        List<String> stock_list=new ArrayList<String>();

        String stock_id = "sh600000";
        String sprice=Client.get("http://hq.sinajs.cn/list="+stock_id).split(",")[3];
        String oprice="9.71";
        String stock_nm="浦发银行";
        String stock_li = "{'stock_id':'"+stock_id+"','stock_nm':'"+stock_nm+"','sprice':'"+sprice+"','oprice':'"+oprice+"'}";
        stock_list.add(stock_li);

        //查询结构
        //String stock_list = "[{'stock_id':'sh600000','stock_nm':'浦发银行','sprice':'9.72','oprice':'9.71'}]";
        String Result_json="[{'name':'"+name+"','stock_list':"+stock_list+"}]";
        JSONArray jsonArray = JSONArray.parseArray(Result_json);
        return jsonArray.toString();
    }




    @RequestMapping("/test")
    @ResponseBody
    @CrossOrigin
    public String test() throws IOException {
        return "测试";
    }



}
