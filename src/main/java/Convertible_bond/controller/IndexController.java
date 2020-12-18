package Convertible_bond.controller;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;


@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(Model model){
        return "Index";
    }
    @RequestMapping("/stock")
    public String stock(){
        return "可转债";
    }
    @RequestMapping("/stock_json")
    @ResponseBody
    @CrossOrigin
    public String stock_json() throws IOException {
        //后台Http请求转发解决跨域问题
        HttpClient client = HttpClients.createDefault();            //client对象
        HttpGet get = new HttpGet("http://dcfm.eastmoney.com/em_mutisvcexpandinterface/api/js/get?type=KZZ_LB2.0&token=70f12f2f4f091e459a279469fe49eca5&cmd=&st=YJL&sr=-1&p=1&ps=50&rt=53608710");    //创建get请求
        CloseableHttpResponse response = (CloseableHttpResponse) client.execute(get);   //执行get请求
        String mes = EntityUtils.toString(response.getEntity());    //将返回体的信息转换为字符串
        System.out.println(mes);
        return mes;
    }
}
