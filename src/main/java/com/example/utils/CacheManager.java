package com.example.utils;

import com.google.common.collect.Sets;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CacheManager {

    public static final Map<String, Set<String>> userset =  new HashMap<>();

    static {
        Set<String> roleset = Sets.newHashSet( "admin", "user");

        userset.put( "niuniu", roleset );

        Set<String> rolesetNormal = Sets.newHashSet( "user" );

        userset.put( "wangwang", rolesetNormal );
    }
}
