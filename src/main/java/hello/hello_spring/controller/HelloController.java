package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //정적 컨텐츠
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    //MVC와 템플릿 엔진
    @GetMapping("hello-mvc")
//  required = false 가 없으면 파라미터가 꼭 들어가야되서 파라미터 없이 주소 hello-mvc 입력하면 에러가 뜬다
//  public String helloMvc(@RequestParam(value = "name", required = false) String name, Model model){

//  hello-mvc?name=유섭 로 넣어주면 됨
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    //API 방식1 (소스없이 그대로)
    @GetMapping("hello-string")
    @ResponseBody
    // http에서 body 부에 직접 넣어주겠다는 의미
    // hello-string?name=유섭 넣어주면 됨
    public String helloString(@RequestParam("name") String name) {
        return "hello!!!! " + name;
    }

    //API 방식2 (데이터만 내려줄때)
    @GetMapping("hello-api")
    @ResponseBody
    //hello-api?name=유섭아
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    //getter setter
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
