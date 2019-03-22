package com.koko.it.config;

import org.apache.shiro.SecurityUtils;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

//@Configuration
public class UserAuditorAware implements AuditorAware<String> {


    // TODO 配置createBy和lastModifyBy
    @Override
    public Optional<String> getCurrentAuditor() {
        String userName = SecurityUtils.getSubject().getPrincipal().toString();
        if (userName != null) {
            Optional<String> optional = Optional.of(userName);
            return optional;
        }
        return null;
    }
}
