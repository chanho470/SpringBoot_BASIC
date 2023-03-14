package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component //컴포넌트 스캔
public class TimeTraceAop {

    // memberService 프록시(가짜) 를 통해 AOP 실행
    @Around("execution(* hello.hellospring..*(..))") //원하는 적용 대상 선택 가능하다.
    public Object execute(ProceedingJoinPoint joinPoint) throws  Throwable{ //메서드 호출할 때 마다 인터셉트
        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString()+ " "+timeMs+"ms");
        }

    }
}
