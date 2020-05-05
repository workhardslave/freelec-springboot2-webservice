package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// MyBatis에서 DAO라고 불리는 DB Layer 접근자, JPA에선 Repository라고 부르며 인터페이스로 생성
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}

/*
    규모가 있는 프로젝트에서의 데이터 조회는 FK의 조인, 복잡한 조건 등으로 이런 Entity 클래스만으로 처리가 어려워
    조회용 프레임워크를 추가로 사용 ex) QueryDSL, jooq, MyBatis
    조회는 위 3가지 프레임워크 중 하나를 통해 조회하고, 등록/수정/삭제 등은 SpringDataJPA를 통해 진행
 */