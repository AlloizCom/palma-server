package com.alloiz.palma.server.service.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.util.Collection;

public class Validation {

    public static void checkId(Long id) {
        if (id == null || id < 0)
            throw new RuntimeException("invalid id");
    }

    public static Boolean checkIdWithBolleanReturnStatement(Long id) {
        if (id == null || id < 0){
            return false;
        }
        return true;
    }

    public static void checkString(String string) {
        if (string == null)
            throw new RuntimeException(string + " must be not null");
    }

    //use when object is parameter
    public static void checkSave(Object object) {
        if (object == null)
            throw new RuntimeException("null");
    }

    //use when json is parameter instead of object in save method
    public static void checkJson(String json) {
        try {
            new ObjectMapper().readTree(String.valueOf(json));
        } catch (NullPointerException | IOException e) {
            throw new RuntimeException("json is null or empty");
        }
    }

    public static <T> T checkObjectExistsById(Long id, JpaRepository<T, Long> jpaRepository) {
        checkId(id);
        T t;
        if ((t = jpaRepository.findOne(id)) == null) {
            throw new RuntimeException("there is no such object");
        }
        return t;
    }

    /**
     * Validator for Collections
     * @param c
     * @return
     */
    public static boolean isNullOrEmpty( final Collection< ? > c ) {
        return c == null || c.isEmpty();
    }

}
