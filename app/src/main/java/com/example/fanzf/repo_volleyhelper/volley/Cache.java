/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.fanzf.repo_volleyhelper.volley;

import java.util.Collections;
import java.util.Map;

/**
 * An interface for a cache keyed by a String with a byte array as data.
 */
public interface Cache {
    /**
     * Retrieves an entry from the cache.
     *
     * @param key Cache key
     * @return An {@link Entry} or null in the event of a cache miss
     */
    public Entry get(String key);

    /**
     * Adds or replaces an entry to the cache.
     *
     * @param key   Cache key
     * @param entry DataBean to store and metadata for cache coherency, TTL, etc.
     */
    public void put(String key, Entry entry);

    /**
     * Performs any potentially long-running actions needed to initialize the cache;
     * will be called from a worker thread.
     */
    public void initialize();

    /**
     * Invalidates an entry in the cache.
     *
     * @param key        Cache key
     * @param fullExpire True to fully expire the entry, false to soft expire
     */
    public void invalidate(String key, boolean fullExpire);

    /**
     * Removes an entry from the cache.
     *
     * @param key Cache key
     */
    public void remove(String key);

    /**
     * Empties the cache.
     */
    public void clear();

    /**
     * DataBean and metadata for an entry returned by the cache.
     */
    public static class Entry {
        /**
         * 加载本地时间
         */
        public static final String LOAD_LOC_TIME = "loadLocTime";
        /**
         * 缓存时间
         */
        public static final String CACHE_TIME = "cacheTime";

        /**
         * The data returned from cache.
         */
        public byte[] data;

        /**
         * ETag for cache coherency.
         */
        public String etag;

        /**
         * Date of this response as reported by the server.
         */
        public long serverDate;

        /**
         * The last modified date for the requested object.
         */
        public long lastModified;

        /**
         * TTL for this record.
         */
        public long ttl;

        /**
         * Soft TTL for this record.
         */
        public long softTtl;
//增加字段低版本升级会报错
//        /**
//         * 网络请求加载数据时间，单位毫秒，本地时间
//         */
//        public long loadLocalTime;
//
//        /**
//         * 缓存时间，单位毫秒
//         */
//        public long cacheTime;

        /**
         * Immutable response headers as received from server; must be non-null.
         */
        public Map<String, String> responseHeaders = Collections.emptyMap();

        /**
         * True if the entry is expired.
         */
        public boolean isExpired() {
            long lTime = 0;
            long cTime = 0;
            if (responseHeaders != null) {
                String strLTime = responseHeaders.get(LOAD_LOC_TIME);
                String strCTime = responseHeaders.get(CACHE_TIME);
                try {
                    if(strLTime != null){
                        lTime = Long.parseLong(strLTime);
                    }
                    if(strCTime != null){
                        cTime = Long.parseLong(strCTime);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            return (Math.abs(System.currentTimeMillis() - lTime)) > cTime;
//            return this.ttl < System.currentTimeMillis();
        }

//        public boolean isExpired() {
//            return true;
//        }

        /**
         * True if a refresh is needed from the original data source.
         */
        public boolean refreshNeeded() {
//            return this.softTtl < System.currentTimeMillis();
            return false;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    ", etag='" + etag + '\'' +
                    ", serverDate=" + serverDate +
                    ", lastModified=" + lastModified +
                    ", ttl=" + ttl +
                    ", softTtl=" + softTtl +
                    ", responseHeaders=" + responseHeaders +
                    '}';
        }
    }

}
