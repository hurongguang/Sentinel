package com.alibaba.csp.sentinel.dashboard.rule.nacos;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRuleProvider;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("degradeRuleNacosProvider")
public class DegradeRuleNacosProvider implements DynamicRuleProvider<List<DegradeRuleEntity>> {
    @Autowired
    private ConfigService configService;

    @Override
    public List<DegradeRuleEntity> getRules(String appName) throws Exception {
        String rules = configService.getConfig(appName + NacosConfigUtil.DEGRADE_DATA_ID_POSTFIX, NacosConfigUtil.GROUP_ID, 3000);
        if (StringUtil.isEmpty(rules)) {
            return new ArrayList<>();
        }
        return JSON.parseArray(rules, DegradeRuleEntity.class);
    }
}
