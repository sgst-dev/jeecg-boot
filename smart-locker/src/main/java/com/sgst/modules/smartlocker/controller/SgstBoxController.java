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
import com.sgst.modules.smartlocker.entity.SgstBox;
import com.sgst.modules.smartlocker.service.ISgstBoxService;

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
 * @Description: sgst_box
 * @Author: jeecg-boot
 * @Date:   2023-07-18
 * @Version: V1.0
 */
@Api(tags="sgst_box")
@RestController
@RequestMapping("/smartlocker/sgstBox")
@Slf4j
public class SgstBoxController extends JeecgController<SgstBox, ISgstBoxService> {
	@Autowired
	private ISgstBoxService sgstBoxService;
	
	/**
	 * 分页列表查询
	 *
	 * @param sgstBox
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "sgst_box-分页列表查询")
	@ApiOperation(value="sgst_box-分页列表查询", notes="sgst_box-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<SgstBox>> queryPageList(SgstBox sgstBox,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<SgstBox> queryWrapper = QueryGenerator.initQueryWrapper(sgstBox, req.getParameterMap());
		Page<SgstBox> page = new Page<SgstBox>(pageNo, pageSize);
		IPage<SgstBox> pageList = sgstBoxService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param sgstBox
	 * @return
	 */
	@AutoLog(value = "sgst_box-添加")
	@ApiOperation(value="sgst_box-添加", notes="sgst_box-添加")
	@RequiresPermissions("smartlocker:sgst_box:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody SgstBox sgstBox) {
		sgstBoxService.save(sgstBox);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param sgstBox
	 * @return
	 */
	@AutoLog(value = "sgst_box-编辑")
	@ApiOperation(value="sgst_box-编辑", notes="sgst_box-编辑")
	@RequiresPermissions("smartlocker:sgst_box:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody SgstBox sgstBox) {
		sgstBoxService.updateById(sgstBox);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "sgst_box-通过id删除")
	@ApiOperation(value="sgst_box-通过id删除", notes="sgst_box-通过id删除")
	@RequiresPermissions("smartlocker:sgst_box:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		sgstBoxService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "sgst_box-批量删除")
	@ApiOperation(value="sgst_box-批量删除", notes="sgst_box-批量删除")
	@RequiresPermissions("smartlocker:sgst_box:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.sgstBoxService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "sgst_box-通过id查询")
	@ApiOperation(value="sgst_box-通过id查询", notes="sgst_box-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<SgstBox> queryById(@RequestParam(name="id",required=true) String id) {
		SgstBox sgstBox = sgstBoxService.getById(id);
		if(sgstBox==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(sgstBox);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param sgstBox
    */
    @RequiresPermissions("smartlocker:sgst_box:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, SgstBox sgstBox) {
        return super.exportXls(request, sgstBox, SgstBox.class, "sgst_box");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("smartlocker:sgst_box:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, SgstBox.class);
    }

}
