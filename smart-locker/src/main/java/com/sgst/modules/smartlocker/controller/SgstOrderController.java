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
import com.sgst.modules.smartlocker.entity.SgstOrder;
import com.sgst.modules.smartlocker.service.ISgstOrderService;

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
 * @Description: sgst_order
 * @Author: jeecg-boot
 * @Date:   2023-07-18
 * @Version: V1.0
 */
@Api(tags="sgst_order")
@RestController
@RequestMapping("/smartlocker/sgstOrder")
@Slf4j
public class SgstOrderController extends JeecgController<SgstOrder, ISgstOrderService> {
	@Autowired
	private ISgstOrderService sgstOrderService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sgstOrder
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "sgst_order-分页列表查询")
	@ApiOperation(value="sgst_order-分页列表查询", notes="sgst_order-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SgstOrder>> queryPageList(SgstOrder sgstOrder,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgstOrder> queryWrapper = QueryGenerator.initQueryWrapper(sgstOrder, req.getParameterMap());
		Page<SgstOrder> page = new Page<SgstOrder>(pageNo, pageSize);
		IPage<SgstOrder> pageList = sgstOrderService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param sgstOrder
	 * @return
	 */
	@AutoLog(value = "sgst_order-添加")
	@ApiOperation(value="sgst_order-添加", notes="sgst_order-添加")
	@RequiresPermissions("smartlocker:sgst_order:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SgstOrder sgstOrder) {
		sgstOrderService.save(sgstOrder);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param sgstOrder
	 * @return
	 */
	@AutoLog(value = "sgst_order-编辑")
	@ApiOperation(value="sgst_order-编辑", notes="sgst_order-编辑")
	@RequiresPermissions("smartlocker:sgst_order:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SgstOrder sgstOrder) {
		sgstOrderService.updateById(sgstOrder);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "sgst_order-通过id删除")
	@ApiOperation(value="sgst_order-通过id删除", notes="sgst_order-通过id删除")
	@RequiresPermissions("smartlocker:sgst_order:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		sgstOrderService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "sgst_order-批量删除")
	@ApiOperation(value="sgst_order-批量删除", notes="sgst_order-批量删除")
	@RequiresPermissions("smartlocker:sgst_order:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgstOrderService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "sgst_order-通过id查询")
	@ApiOperation(value="sgst_order-通过id查询", notes="sgst_order-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SgstOrder> queryById(@RequestParam(name="id",required=true) String id) {
		SgstOrder sgstOrder = sgstOrderService.getById(id);
		if(sgstOrder==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(sgstOrder);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param sgstOrder
    */
    @RequiresPermissions("smartlocker:sgst_order:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SgstOrder sgstOrder) {
        return super.exportXls(request, sgstOrder, SgstOrder.class, "sgst_order");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("smartlocker:sgst_order:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SgstOrder.class);
    }

}
