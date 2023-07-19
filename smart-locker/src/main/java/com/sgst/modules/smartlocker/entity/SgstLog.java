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
 * @Description: sgst_log
 * @Author: jeecg-boot
 * @Date:   2023-07-18
 * @Version: V1.0
 */
@Data
@TableName("sgst_log")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="sgst_log对象", description="sgst_log")
public class SgstLog implements Serializable {
    private static final long serialVersionUID = 1L;

	/**创建时间*/
	@JsonFormat(timezone = "GMT+9",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private java.util.Date createTime;
	/**账号(企业id), 0表示SGST*/
	@Excel(name = "账号(企业id), 0表示SGST", width = 15)
    @ApiModelProperty(value = "账号(企业id), 0表示SGST")
    private java.lang.Integer accountId;
	/**所属设备(Android等)*/
	@Excel(name = "所属设备(Android等)", width = 15)
    @ApiModelProperty(value = "所属设备(Android等)")
    private java.lang.String imei;
	/**访问的API*/
	@Excel(name = "访问的API", width = 15)
    @ApiModelProperty(value = "访问的API")
    private java.lang.String url;
	/**param*/
	@Excel(name = "param", width = 15)
    @ApiModelProperty(value = "param")
    private java.lang.String param;
	/**resultJson*/
	@Excel(name = "resultJson", width = 15)
    @ApiModelProperty(value = "resultJson")
    private java.lang.String resultJson;
	/**resultStatus*/
	@Excel(name = "resultStatus", width = 15)
    @ApiModelProperty(value = "resultStatus")
    private java.lang.Integer resultStatus;
	/**err*/
	@Excel(name = "err", width = 15)
    @ApiModelProperty(value = "err")
    private java.lang.String err;
	/**处理用时*/
	@Excel(name = "处理用时", width = 15)
    @ApiModelProperty(value = "处理用时")
    private java.lang.Integer usedMilliseconds;
}
