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
 * @Description: sgst_box
 * @Author: jeecg-boot
 * @Date:   2023-07-18
 * @Version: V1.0
 */
@Data
@TableName("sgst_box")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="sgst_box对象", description="sgst_box")
public class SgstBox implements Serializable {
    private static final long serialVersionUID = 1L;

	/**id*/
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private java.lang.Integer id;
	/**账号(企业id), 0表示未分配*/
	@Excel(name = "账号(企业id), 0表示未分配", width = 15)
    @ApiModelProperty(value = "账号(企业id), 0表示未分配")
    private java.lang.Integer accountId;
	/**所属设备(Android等)*/
	@Excel(name = "所属设备(Android等)", width = 15)
    @ApiModelProperty(value = "所属设备(Android等)")
    private java.lang.String imei;
	/**柜子种类   100:小, 200:中, 300:大. 其他的再说*/
	@Excel(name = "柜子种类   100:小, 200:中, 300:大. 其他的再说", width = 15)
    @ApiModelProperty(value = "柜子种类   100:小, 200:中, 300:大. 其他的再说")
    private java.lang.Integer boxType;
	/**柜子状态 参考: https://cb6047arz0.larksuite.com/docx/IGSHdhACNowNAMx5HWeuDoE6s9f */
	@Excel(name = "柜子状态 参考: https://cb6047arz0.larksuite.com/docx/IGSHdhACNowNAMx5HWeuDoE6s9f ", width = 15)
    @ApiModelProperty(value = "柜子状态 参考: https://cb6047arz0.larksuite.com/docx/IGSHdhACNowNAMx5HWeuDoE6s9f ")
    private java.lang.Integer status;
	/**柜门名称*/
	@Excel(name = "柜门名称", width = 15)
    @ApiModelProperty(value = "柜门名称")
    private java.lang.String boxName;
	/**为每次存件分配的唯一编号(当1次存件使用到了多个box时，这多个box的order_id都为同一个值)*/
	@Excel(name = "为每次存件分配的唯一编号(当1次存件使用到了多个box时，这多个box的order_id都为同一个值)", width = 15)
    @ApiModelProperty(value = "为每次存件分配的唯一编号(当1次存件使用到了多个box时，这多个box的order_id都为同一个值)")
    private java.lang.String orderId;
	/**锁的控制板号*/
	@Excel(name = "锁的控制板号", width = 15)
    @ApiModelProperty(value = "锁的控制板号")
    private java.lang.String lockPanelCode;
	/**锁的端口号*/
	@Excel(name = "锁的端口号", width = 15)
    @ApiModelProperty(value = "锁的端口号")
    private java.lang.Integer lockPort;
	/**sensor1所属控制板*/
	@Excel(name = "sensor1所属控制板", width = 15)
    @ApiModelProperty(value = "sensor1所属控制板")
    private java.lang.String sensorPanelCode1;
	/**sensor1模组名*/
	@Excel(name = "sensor1模组名", width = 15)
    @ApiModelProperty(value = "sensor1模组名")
    private java.lang.String sensorModuleName1;
	/**sensor2所属控制板*/
	@Excel(name = "sensor2所属控制板", width = 15)
    @ApiModelProperty(value = "sensor2所属控制板")
    private java.lang.String sensorPanelCode2;
	/**sensor2模组名*/
	@Excel(name = "sensor2模组名", width = 15)
    @ApiModelProperty(value = "sensor2模组名")
    private java.lang.String sensorModuleName2;
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
