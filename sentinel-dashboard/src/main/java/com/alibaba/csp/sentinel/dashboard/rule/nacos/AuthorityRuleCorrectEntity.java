package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.RuleEntity;
import com.alibaba.csp.sentinel.slots.block.Rule;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityRule;
import lombok.Data;

import java.util.Date;

@Data
public class AuthorityRuleCorrectEntity implements RuleEntity {

    private Long id;
    private String app;
    private String ip;
    private Integer port;
    private String limitApp;
    private String resource;
    private Date gmtCreate;
    private Date gmtModified;
    private int strategy;

    @Override
    public Rule toRule() {
        return new AuthorityRule();
    }
}