package com.sgst.modules.smartlocker.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: sgst_order
 * @Author: jeecg-boot
 * @Date:   2023-07-18
 * @Version: V1.0
 */
@Data
@TableName("sgst_order")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="sgst_order对象", description="sgst_order")
public class SgstOrder implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**所属设备(Android等)*/
	@Excel(name = "所属设备(Android等)", width = 15)
    @ApiModelProperty(value = "所属设备(Android等)")
    private java.lang.String imei;
	/**开门密码*/
	@Excel(name = "开门密码", width = 15)
    @ApiModelProperty(value = "开门密码")
    private java.lang.String pwd;
	/**为每次存件分配的唯一编号*/
	@Excel(name = "为每次存件分配的唯一编号", width = 15)
    @ApiModelProperty(value = "为每次存件分配的唯一编号")
    private java.lang.String orderId;
	/**删除的时刻. 为满足业务的唯一性需求，只把9999-01-01 00:00:00作为未删除*/
	@Excel(name = "删除的时刻. 为满足业务的唯一性需求，只把9999-01-01 00:00:00作为未删除", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+9",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "删除的时刻. 为满足业务的唯一性需求，只把9999-01-01 00:00:00作为未删除")
    private java.util.Date deleteAt;
	/**自由列1*/
	@Excel(name = "自由列1", width = 15)
    @ApiModelProperty(value = "自由列1")
    private java.lang.String memo1;
	/**自由列2*/
	@Excel(name = "自由列2", width = 15)
    @ApiModelProperty(value = "自由列2")
    private java.lang.String memo2;
	/**最后一次更新时间*/
	@JsonFormat(timezone = "GMT+9",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最后一次更新时间")
    private java.util.Date updateTime;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+9",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
	/**租户ID*/
	@Excel(name = "租户ID", width = 15)
    @ApiModelProperty(value = "租户ID")
    private java.lang.Integer tenantId;
}
