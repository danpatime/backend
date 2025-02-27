package com.example.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Sql("/data.sql")
@DataJpaTest
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface JpaTestWithInitData {
}
