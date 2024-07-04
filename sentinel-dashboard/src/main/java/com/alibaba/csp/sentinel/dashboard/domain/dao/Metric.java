package com.alibaba.csp.sentinel.dashboard.domain.dao;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("sentinel_metric")
public class Metric {

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 创建时间
     */
    @TableField("gmt_create")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @TableField("gmt_modified")
    private Date gmtModified;

    /**
     * 应用名称
     */
    private String app;

    /**
     * 监控信息的时间戳
     */
    private Date timestamp;

    /**
     * 资源名
     */
    private String resource;

    /**
     * 通过qps
     */
    @TableField("pass_qps")
    private Long passQps;

    /**
     * 成功qps
     */
    @TableField("success_qps")
    private Long successQps;

    /**
     * 限流qps
     */
    @TableField("block_qps")
    private Long blockQps;

    /**
     * 异常qps
     */
    @TableField("exception_qps")
    private Long exceptionQps;

    /**
     * 所有successQps的rt的和
     */
    private Double rt;

    /**
     * 本次聚合的总条数
     */
    private Integer count;

    /**
     * 资源的hashCode
     */
    @TableField("resource_code")
    private Integer resourceCode;
}