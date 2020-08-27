package kr.co.fastcampus.eatgo.utils;

import org.junit.Test;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class JwtUtilTests {

    @Test
    public void createToken() {
        JwtUtil jwtUtil = new JwtUtil();

        String token = jwtUtil.createToken(1004L, "John");

//        jwt의 특징은 . 가 있어야 한다. (Header.Payload.Signature)
        assertThat(token, containsString("."));
    }
}