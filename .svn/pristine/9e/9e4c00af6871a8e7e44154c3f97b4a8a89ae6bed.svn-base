package com.gome.gmhx.controller.basicdata;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.common.page.Page;
import com.gome.common.util.JsonUtil;
import com.gome.gmhx.entity.SettleaccountsParam;
import com.gome.gmhx.service.basicdata.SettleaccountsParamService;

@Controller
@RequestMapping("/settleaccountsParam")
public class SettleaccountsParamContoller {
	
		@Resource
		private SettleaccountsParamService settleaccountsParamService;
	
	
		/**
		* @Title: gotoList
		* @Description:访问核算参数列表页面
		* @param @return 设定文件
		* @return String 返回类型
		* @throws
		*/
		@RequestMapping(value="gotolist")
		public  String gotoList()  throws Exception{
			return "basicData/hxInstallStandardFee/settleaccountsParam";
		}
		
		/**
		* @Title: listAllDataByPage
		* @Description: 数据查询
		* @param @param filter
		* @param @return 设定文件
		* @return String 返回类型
		* @throws
		*/
		@RequestMapping(value="listAllDataByPage",produces="text/plain;charset=utf-8")
		public  @ResponseBody String  listAllDataByPage(SettleaccountsParam filter,Page page)  throws Exception{
		        	page.setParam(filter);
					List datas = this.settleaccountsParamService.listAllDataByPage(page);
				    return JsonUtil.writeListToDataGrid(page.getTotalResult(), datas);
		}
		
		
		/**
		* @Title: queryByParamNo
		* @Description: 根据编码
		* @param @param paramNo
		* @return String 返回类型
		* @throws
		*/
		@RequestMapping(value="queryByParamNo",produces="text/plain;charset=utf-8")
		public  @ResponseBody String  queryByParamNo(String paramNo)  throws Exception{
				    return	JSONObject.fromObject(this.settleaccountsParamService.queryByParamNo(paramNo)).toString();
		}
		
		
		/**
		* @Title: savaOrUpdateRecode
		* @Description: 前台保存或者更新入口
		* @param @param param
		* @param @return
		* @param @throws Exception 设定文件
		* @return String 返回类型
		* @throws
		*/
		@RequestMapping(value="savaOrUpdateRecode",produces="text/plain;charset=utf-8")
		public  @ResponseBody String  savaOrUpdateRecode(SettleaccountsParam param)  throws Exception{
		 	this.settleaccountsParamService.saveOrUpdate(param);
		    return JsonUtil.toJson(new HashMap());
		}
		
		/**
		* @Title: delRecode
		* @Description: 删除数据入口
		* @param @param paramNo
		* @param @return
		* @param @throws Exception 设定文件
		* @return String 返回类型
		* @throws
		*/
		@RequestMapping(value="delRecode",produces="text/plain;charset=utf-8")
		public  @ResponseBody String  delRecode(String paramNo)  throws Exception{
			this.settleaccountsParamService.delSettleaccountsParam(paramNo);
			return JsonUtil.toJson(new HashMap());
		}

}
