package com.xmk.bsf.jdbc;

import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class XmkBsfJdbcApplicationTests {

    public static final Object UNSET = new Object();
    static Object[] indexedVariables = {UNSET, UNSET, UNSET, UNSET, UNSET, UNSET};

    @Test
    void contextLoads() {

        User user = new User();
        user.setAge(1);
        user.setName("mingke");

        User user1 = new User();
        user1.setAge(2);
        user1.setName("mingke");


        User user2 = new User();
        user2.setAge(1);
        user2.setName("xie");

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user2);
        userList.add(user1);
        userList.add(user);

        HashMap<String, Integer> map = new HashMap();



        System.out.println(map);

        expandIndexedVariableTableAndSet(101, "aaa");
    }


    public void expandIndexedVariableTableAndSet(int index, Object value) {
        Object[] oldArray = indexedVariables;
        final int oldCapacity = oldArray.length;
        int newCapacity = index;
        newCapacity |= newCapacity >>> 1;
        newCapacity |= newCapacity >>> 2;
        newCapacity |= newCapacity >>> 4;
        newCapacity |= newCapacity >>> 8;
        newCapacity |= newCapacity >>> 16;
        newCapacity++;

        Object[] newArray = Arrays.copyOf(oldArray, newCapacity);
        Arrays.fill(newArray, oldCapacity, newArray.length, UNSET);
        newArray[index] = value;
        indexedVariables = newArray;

        System.out.println(newCapacity);

    }

    @Data
    class User {
        private Integer age;

        private String name;
    }

}
