
package com.wangxin.consumer.api.common;

public interface VersionProvider {
    /**
     * @return timestamp formattato, es. "20250804123045123"
     */
    String generateVersion();
}
