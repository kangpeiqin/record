package com.kk.basic.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @author KPQ
 * @date 2022/2/11
 */
public class HashKeyTest {

    static class Key {
        private Integer id;

        public Key(Integer id) {
            this.id = id;
        }

        public Integer getId() {
            return id;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Key)) {
                return false;
            } else {
                return this.getId().equals(((Key) o).getId());
            }

        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    public static void main(String[] args) {
        //equals 方法默认是判断两个对象的内存地址是否相等，即是否是同一个对象，
        //而我们通常需要判断的是对象的值是否相等，所以当两个对象需要进行比较时，就需要重写equals方法
        //在使用HashMap或者HashSet时，如果需要存入自定义对象的Key，我们通常要重写自定义的Key的hashCode 和 equals 方法
        // 因为在哈希表中，会根据 Key 元素 hashCode 计算在键值对数组当中存储的索引位置，如果 key 哈希值相同，则会被映射到数组的同一位置。
        //即发生哈希冲突，会用链地址法，将哈希值相等的元素链成一个链表
        //没有重写 hashCode 方法，默认会使用根类 Object 的 hashCode 方法，返回的是对象的内存地址
        Key key1 = new Key(1);
        Key key2 = new Key(1);
        Map<Key, String> map = new HashMap<>(16);
        map.put(key1, "test");
        System.out.println(map.get(key1));
        System.out.println(map.get(key2));
    }

}
