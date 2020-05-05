/* @SpringBootApplicaiton
   스프링 부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정
   이 어노테이션이 있는 곳부터 설정을 읽어가기 때문에 프로젝트의 최상단에 위치
*/

package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args); //내장 WAS(톰캣) 실행
    }
}
