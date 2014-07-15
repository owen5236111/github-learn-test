package com.jpe.smt.imageCache.util;

/**
 * 缓存配置
 * @author oywf 
 * 缓存文件配置 也在这里实现 
 */
public interface CacheConfig {
	/** 缓冲字节流一次读入大小 ( google 推荐 8192 ). **/
	int IO_BUFFER_SIZE = 8 * 1024;

	/** 缓存总目录. **/ 
	String DISK_CACHE_NAME = "/jpe_smt/CacheDir";

	/**
	 * 图片缓存的配置.
	 */
	interface Image {
		/** 图片缓存的目录. **/
		String DISK_CACHE_NAME = "/images";

		/** 图片缓存的最大大小. (20MB) **/
		int DISK_CACHE_MAX_SIZE = 1024 * 1024 * 20;

		/** 图片缓存的内存大小. (1/8内存大小) **/
		int MEMORY_SHRINK_FACTOR = 8;
	}
}
