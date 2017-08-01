package com.yn.keygen;

/**
 * Created by yangnan on 17/7/31.
 *
 * 全局唯一ID生成器
 *
 */
public interface KeyGenerator {
    /**
     * 生成全局唯一ID
     *
     * @return
     */
    Number generateKey();
}
