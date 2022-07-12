package com.atguigu.unionfindsets;

import java.util.HashMap;

public class MergeUser {
    public static class User {
        public String a;
        public String b;
        public String c;

        public User(String a, String b, String c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

    public int mergeUser(User[] users) {
        UnionFindSets.UnionFindSetsStructure<User> unionFindSetsStructure = new UnionFindSets.UnionFindSetsStructure<>(users);
        HashMap<String, User> mapA = new HashMap<>();
        HashMap<String, User> mapB = new HashMap<>();
        HashMap<String, User> mapC = new HashMap<>();
        for (int i = 0; i < users.length; i++) {

            if (mapA.containsKey(users[i].a)) {
                unionFindSetsStructure.union(users[i], mapA.get(users[i].a));
            } else {
                mapA.put(users[i].a, users[i]);
            }

            if (mapB.containsKey(users[i].b)) {
                unionFindSetsStructure.union(users[i], mapB.get(users[i].b));
            } else {
                mapB.put(users[i].b, users[i]);
            }

            if (mapC.containsKey(users[i].c)) {
                unionFindSetsStructure.union(users[i], mapC.get(users[i].c));
            } else {
                mapC.put(users[i].c, users[i]);
            }
        }
        return unionFindSetsStructure.getNum();
    }
}
