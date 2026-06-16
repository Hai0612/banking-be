package com.core.autoconfigure;


import com.core.filter.MdcContextFilter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class ObservabilityAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MdcContextFilter mdcContextFilter() {
        return new MdcContextFilter();
    }
}
