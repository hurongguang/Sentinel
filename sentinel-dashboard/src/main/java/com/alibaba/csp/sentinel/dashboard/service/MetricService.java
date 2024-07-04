package com.alibaba.csp.sentinel.dashboard.service;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.MetricEntity;
import com.alibaba.csp.sentinel.dashboard.domain.dao.Metric;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Collection;
import java.util.List;

public interface MetricService extends IService<Metric> {

    List<String> listResourcesOfApp(String app);

    List<MetricEntity> queryByAppAndResourceBetween(String app, String resource, Long startTime, Long endTime);

    void saveAll(Collection<MetricEntity> metrics);

}
