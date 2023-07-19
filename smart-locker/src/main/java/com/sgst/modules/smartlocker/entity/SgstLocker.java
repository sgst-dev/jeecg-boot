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
 * @Description: sgst_locker
 * @Author: jeecg-boot
 * @Date:   2023-07-18
 * @Version: V1.0
 */
@Data
@TableName("sgst_locker")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="sgst_locker对象", description="sgst_locker")
public class SgstLocker implements Serializable {
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
	/**0: 独占外， 10: 預入中,20: 受取中, 30: 管理作業中. 最新情報-> https://cb6047arz0.larksuite.com/docx/Vl2hd724LoLo4axJGhRusDOPsFX*/
	@Excel(name = "0: 独占外， 10: 預入中,20: 受取中, 30: 管理作業中. 最新情報-> https://cb6047arz0.larksuite.com/docx/Vl2hd724LoLo4axJGhRusDOPsFX", width = 15)
    @ApiModelProperty(value = "0: 独占外， 10: 預入中,20: 受取中, 30: 管理作業中. 最新情報-> https://cb6047arz0.larksuite.com/docx/Vl2hd724LoLo4axJGhRusDOPsFX")
    private java.lang.Integer exclusiveMode;
	/**柜子名称*/
	@Excel(name = "柜子名称", width = 15)
    @ApiModelProperty(value = "柜子名称")
    private java.lang.String lockerName;
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
