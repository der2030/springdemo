package der.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @FileName:IndexWeb
* @Description:
* @Author: Derrick Ye
*/
@Controller
public class IndexWeb {

    @RequestMapping("/index")
    public String goIndex(){
        return "index";
    }
}
