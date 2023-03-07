package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    } //hello-template.html에서 name 값을 파라메터 name 값을 받아와 view 해준다.

    @GetMapping("hello-string")
    @ResponseBody //http 응답 바디 부분에 직접 넣는다.
    public String helloSpring(@RequestParam("name") String name){
        return "hello " + name;
    } // html이 없어도 http://localhost:8081/hello-string?name=Spring 에서 hello spring 을 확인할 수 있다.

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name); //파라메터로 가져온 값을 객체에 지정
        return hello; // 객체형식이기 때문에 json 방식으로 값을 만들어 던져준다.
    }

    // @ResponseBody를 사용하면 http body에 문자 내용을 직접 반환 해주는데 HttpMessageConverter로 기본 문자 및 객체 등 변환 처리를 해준다.

    // alt + insert : getter setter 단축키
    static class Hello{ //객체 생성
        private String name;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }


}
