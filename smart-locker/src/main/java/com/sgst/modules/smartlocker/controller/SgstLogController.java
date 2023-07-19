package com.sgst.modules.smartlocker.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import com.sgst.modules.smartlocker.entity.SgstLog;
import com.sgst.modules.smartlocker.service.ISgstLogService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: sgst_log
 * @Author: jeecg-boot
 * @Date:   2023-07-18
 * @Version: V1.0
 */
@Api(tags="sgst_log")
@RestController
@RequestMapping("/smartlocker/sgstLog")
@Slf4j
public class SgstLogController extends JeecgController<SgstLog, ISgstLogService> {
	@Autowired
	private ISgstLogService sgstLogService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sgstLog
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "sgst_log-分页列表查询")
	@ApiOperation(value="sgst_log-分页列表查询", notes="sgst_log-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SgstLog>> queryPageList(SgstLog sgstLog,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgstLog> queryWrapper = QueryGenerator.initQueryWrapper(sgstLog, req.getParameterMap());
		Page<SgstLog> page = new Page<SgstLog>(pageNo, pageSize);
		IPage<SgstLog> pageList = sgstLogService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param sgstLog
	 * @return
	 */
	@AutoLog(value = "sgst_log-添加")
	@ApiOperation(value="sgst_log-添加", notes="sgst_log-添加")
	@RequiresPermissions("smartlocker:sgst_log:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SgstLog sgstLog) {
		sgstLogService.save(sgstLog);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param sgstLog
	 * @return
	 */
	@AutoLog(value = "sgst_log-编辑")
	@ApiOperation(value="sgst_log-编辑", notes="sgst_log-编辑")
	@RequiresPermissions("smartlocker:sgst_log:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SgstLog sgstLog) {
		sgstLogService.updateById(sgstLog);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "sgst_log-通过id删除")
	@ApiOperation(value="sgst_log-通过id删除", notes="sgst_log-通过id删除")
	@RequiresPermissions("smartlocker:sgst_log:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		sgstLogService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "sgst_log-批量删除")
	@ApiOperation(value="sgst_log-批量删除", notes="sgst_log-批量删除")
	@RequiresPermissions("smartlocker:sgst_log:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgstLogService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "sgst_log-通过id查询")
	@ApiOperation(value="sgst_log-通过id查询", notes="sgst_log-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SgstLog> queryById(@RequestParam(name="id",required=true) String id) {
		SgstLog sgstLog = sgstLogService.getById(id);
		if(sgstLog==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(sgstLog);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param sgstLog
    */
    @RequiresPermissions("smartlocker:sgst_log:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SgstLog sgstLog) {
        return super.exportXls(request, sgstLog, SgstLog.class, "sgst_log");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("smartlocker:sgst_log:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SgstLog.class);
    }

}
