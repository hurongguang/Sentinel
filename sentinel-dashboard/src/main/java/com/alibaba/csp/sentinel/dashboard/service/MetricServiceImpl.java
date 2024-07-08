package com.alibaba.csp.sentinel.dashboard.service;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.MetricEntity;
import com.alibaba.csp.sentinel.dashboard.domain.dao.Metric;
import com.alibaba.csp.sentinel.dashboard.mapper.MetricMapper;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class MetricServiceImpl extends ServiceImpl<MetricMapper, Metric> implements MetricService {

    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    @Override
    public void saveAll(Collection<MetricEntity> metrics) {
        if (metrics == null) {
            return;
        }
        readWriteLock.writeLock().lock();
        try {
            this.saveBatch(metrics.stream().map(this::convertMetric).collect(Collectors.toList()));
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    @Override
    public List<String> listResourcesOfApp(String app) {
        List<String> results = new ArrayList<>();
        if (StringUtil.isBlank(app)) {
            return results;
        }

        readWriteLock.readLock().lock();
        try {
            return this.lambdaQuery()
                    .eq(Metric::getApp, app)
                    .orderByDesc(Metric::getTimestamp)
                    .last("limit 1000")
                    .select(Metric::getResource)
                    .list()
                    .stream()
                    .map(Metric::getResource)
                    .collect(Collectors.toList());
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    @Override
    public List<MetricEntity> queryByAppAndResourceBetween(String app, String resource, Long startTime, Long endTime) {
        if (StringUtil.isBlank(app)) {
            return Collections.emptyList();
        }
        readWriteLock.readLock().lock();
        try {
            return this.lambdaQuery()
                    .eq(Metric::getResource, resource)
                    .ge(Metric::getTimestamp, new Date(startTime))
                    .le(Metric::getTimestamp, new Date(endTime))
                    .orderByAsc(Metric::getTimestamp)
                    .list()
                    .stream()
                    .map(this::convertMetric)
                    .collect(Collectors.toList());
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    private MetricEntity convertMetric(Metric metric) {
        MetricEntity entity = new MetricEntity();
        entity.setId(metric.getId());
        entity.setGmtCreate(metric.getGmtCreate());
        entity.setGmtModified(metric.getGmtModified());
        entity.setApp(metric.getApp());
        entity.setTimestamp(metric.getTimestamp());
        entity.setResource(metric.getResource());
        entity.setPassQps(metric.getPassQps());
        entity.setBlockQps(metric.getBlockQps());
        entity.setSuccessQps(metric.getSuccessQps());
        entity.setExceptionQps(metric.getExceptionQps());
        entity.setRt(metric.getRt());
        entity.setCount(metric.getCount());
        return entity;
    }

    private Metric convertMetric(MetricEntity metric) {
        Metric entity = new Metric();
        entity.setId(metric.getId());
        entity.setGmtCreate(metric.getGmtCreate());
        entity.setGmtModified(metric.getGmtModified());
        entity.setApp(metric.getApp());
        entity.setTimestamp(metric.getTimestamp());
        entity.setResource(metric.getResource());
        entity.setPassQps(metric.getPassQps());
        entity.setBlockQps(metric.getBlockQps());
        entity.setSuccessQps(metric.getSuccessQps());
        entity.setExceptionQps(metric.getExceptionQps());
        entity.setRt(metric.getRt());
        entity.setCount(metric.getCount());
        return entity;
    }
}
