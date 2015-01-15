package com.gome.gmhx.controller.basicdata;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gome.common.bean.ViewExcel;
import com.gome.common.page.Page;
import com.gome.common.util.BeanMapUtils;
import com.gome.common.util.DateUtils;
import com.gome.common.util.DecoderUtil;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.HxPosition;
import com.gome.gmhx.entity.JlSale;
import com.gome.gmhx.service.wsdl.service.JlSaleService;

@Controller
@RequestMapping(value="/SaleData")
public class JlSaleDataController {

	@Resource
	private JlSaleService jlSaleService;
	
	@RequestMapping(value = "/getJlSaleDataPageList", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getJlSaleDataPageList(HttpServletResponse response, Page page, JlSale sale,@DateTimeFormat(pattern="yyyy-MM-dd")Date startDate, @DateTimeFormat(pattern="yyyy-MM-dd")Date endDate,
			@DateTimeFormat(pattern="yyyy-MM-dd")Date starttsDate, @DateTimeFormat(pattern="yyyy-MM-dd")Date endtsDate) throws Exception {
		Map<String, Object> map = BeanMapUtils.convertBean(sale);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("starttsDate", starttsDate);
		map.put("endtsDate", endtsDate);
		page.setParam(map);
		List<Map<String, Object>> list = jlSaleService.getJlSaleDataPageList(page);
		return JsonUtil.writeListToDataGrid(page.getTotalResult(), list);
	}
	
	@RequestMapping(value="/showView", produces = "text/plain;charset=utf-8")
	public ModelAndView showView(JlSale sale){
		ModelAndView mav = new ModelAndView("basicData/hxBillNumImportQuery/hxBillNumShow");
		JlSale jlSale=jlSaleService.selectSaleByPK(sale);
		mav.addObject("sale", jlSale);
		return mav;
	}
	
	@RequestMapping(value = "/exportExcel")
	public ModelAndView exportExcel(JlSale sale, String tableField, String tableHeader, HttpServletRequest request, HttpServletResponse response) throws Exception {
		new DecoderUtil<JlSale>().decodeURI(sale);
		String header = URLDecoder.decode(tableHeader, "UTF-8");
		List<Map<String, Object>> list = jlSaleService.getJlSaleDataExport(sale);
		ViewExcel viewExcel = new ViewExcel("提单号导出" + DateUtils.formatDateTime(new Date(), DateUtils.FORMAT_THREE),tableField, header, list);
		return new ModelAndView(viewExcel);
	}
}
