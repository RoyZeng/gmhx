package com.gome.gmhx.controller.jasperreport;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.FileBufferedOutputStream;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.common.Constrants;
import com.gome.gmhx.entity.HxFitting;
import com.gome.gmhx.entity.HxMaterialManage;
import com.gome.gmhx.entity.HxParcelDelivery;
import com.gome.gmhx.entity.HxServiceRetreatReplacement;
import com.gome.gmhx.entity.vo.HxMaterialDetailVO;
import com.gome.gmhx.entity.vo.HxMaterialManageVO;
import com.gome.gmhx.service.basicdata.HxCodeService;
import com.gome.gmhx.service.basicdata.HxFittingService;
import com.gome.gmhx.service.materialmanage.HxMaterialService;
import com.gome.gmhx.service.materialmanage.HxParcelDeliveryService;
import com.gome.gmhx.service.servicemanage.HxRetreatReplacementCreateService;

@Controller
@RequestMapping(value="/HxJasperReport")
public class HxJasperReportController {

	@Resource
	private HxRetreatReplacementCreateService hxRetreatReplacementCreateService;
	
	@Resource
	private HxParcelDeliveryService parcelDeliveryService;
	
	@Resource
	private HxMaterialService hxMaterialService;
	
	@Resource
	private HxCodeService hxCodeService;
	
	@Resource
	private HxFittingService hxFittingService;
	
	@RequestMapping(value="/print")
	@ResponseBody
	public void print(HttpServletRequest request,HttpServletResponse response,String id,String type)
			throws ServletException, IOException{
		File jasperFile=null;
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("unit_mc", "国美电器有限公司");
		JasperPrint jasperPrint = null;
		String inOut=null;  // 出库/入库
		try {
		if(Constrants.PRINT_TYPE_RETURNMACHINE.equals(type)){
			jasperFile = new File(request.getSession().getServletContext()
					.getRealPath("/WEB-INF/jasper/RetreatReplacementCreate.jasper"));
		}else if(Constrants.PRINT_TYPE_PARCELTRANSCEIVER.equals(type) || Constrants.PRINT_TYPE_MATERIALMANAGE.equals(type) || Constrants.PRINT_TYPE_INOUTSTOCK.equals(type)){
			String reportSubFileName =  request.getSession().getServletContext().getRealPath("/WEB-INF/jasper");//子报表路径,子报表只需到文件夹，不需要到文件。
			parameters.put("SUBREPORT_DIR", reportSubFileName + "\\"); 
			List<HxMaterialDetailVO> materialDetailList=null;
			if(Constrants.PRINT_TYPE_PARCELTRANSCEIVER.equals(type)){
				jasperFile = new File(request.getSession().getServletContext()
						.getRealPath("/WEB-INF/jasper/ParcelDelivery_MaterialDetail.jasper"));
			    materialDetailList = parcelDeliveryService.getMaterialDetail(id);
			}else if(Constrants.PRINT_TYPE_MATERIALMANAGE.equals(type)){
				jasperFile = new File(request.getSession().getServletContext()
						.getRealPath("/WEB-INF/jasper/MaterialManage.jasper"));
				materialDetailList =hxMaterialService.getMaterialDetailByNumber(id);
				double totalPrice = 0;
				for(HxMaterialDetailVO materialDetail:materialDetailList){
					totalPrice += (Float)materialDetail.getPrice() * (materialDetail.getAuditCount()==null ? materialDetail.getApplyCount() : materialDetail.getAuditCount()) ;
				}
				parameters.put("totalPrice", totalPrice);
			}else if(Constrants.PRINT_TYPE_INOUTSTOCK.equals(type)){
				jasperFile = new File(request.getSession().getServletContext()
						.getRealPath("/WEB-INF/jasper/InOutStock.jasper"));
				inOut=request.getParameter("inout");
				String fittingPositionType = request.getParameter("fittingPositionType");  // 当前库存位置
				HxMaterialManage m = hxMaterialService.getHxMaterialShow(id);
				String fittingAuthType = m.getType();
				materialDetailList =hxMaterialService.getMaterialDetailByNumber(id);
				materialDetailList=getInOutStock(fittingAuthType, inOut, fittingPositionType, materialDetailList);
			}
			parameters.put("parameter1", materialDetailList); 
		}
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(jasperFile.getPath());
			jasperPrint = JasperFillManager.fillReport(jasperReport,
					parameters, getDataSource(id,type,inOut));
		} catch (JRException e) {
			e.printStackTrace();
		}
		if (jasperPrint!=null) {
			FileBufferedOutputStream fbos = new FileBufferedOutputStream();
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, fbos);
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			try {
				exporter.exportReport();
				fbos.close();
				if (fbos.size() > 0) {
					response.setContentType("application/pdf");
					response.setContentLength(fbos.size());
					ServletOutputStream ouputStream = response.getOutputStream();
					try {
						fbos.writeData(ouputStream);
						fbos.dispose();
						ouputStream.flush();
					} finally {
						if (ouputStream!=null) {
							ouputStream.close();
						}
					}
				}
			} catch (JRException e1) {
				e1.printStackTrace();
			}finally{
				if(fbos!=null){
					fbos.close();
					fbos.dispose();
				}
			}
		}
	}
	
	public List<HxMaterialDetailVO> getInOutStock(String type,String inout,String fittingPositionType,List<HxMaterialDetailVO> list){
		for(HxMaterialDetailVO materialDetail:list){
			HxFitting fitting = hxFittingService.getHxFittingByFittingCode(materialDetail.getFittingCodeDetail());
			Integer count=materialDetail.getAuditCount()!=null?materialDetail.getAuditCount():materialDetail.getApplyCount();
			materialDetail.setCount(count);
			if("1".equals(fittingPositionType)){
				if("1".equals(inout)){ //出库
					materialDetail.setInBatchPrice(fitting.getBranchPrice());
					materialDetail.setOutBatchPrice(fitting.getBranchPrice());
				}else{ // 入库
					materialDetail.setInBatchPrice(fitting.getCost());
				}
			}else if("2".equals(fittingPositionType)){
				if("1".equals(inout)){ //出库
					materialDetail.setInBatchPrice(fitting.getBranchPrice());
					if(type.startsWith("fb-kc-zy") || type.startsWith("fb-o-bf")){
						materialDetail.setOutBatchPrice(fitting.getBranchPrice());
					}else{
						//分部出库对象只有网点，所以对应出库价格为网点价格
						materialDetail.setOutBatchPrice(fitting.getNetworkPrice());
					}
				}else{//入库
					materialDetail.setInBatchPrice(fitting.getBranchPrice());
				}
			}else if("3".equals(fittingPositionType)){
				if("1".equals(inout)){ //出库
					materialDetail.setInBatchPrice(fitting.getNetworkPrice());
					materialDetail.setOutBatchPrice(fitting.getNetworkPrice());
				}else{ // 入库
					materialDetail.setInBatchPrice(fitting.getNetworkPrice());
				}
			}
		}
		return list;
	}
	
	public JRDataSource getRetreatReplacementDataSource(String applyId)
	{
		List<HxServiceRetreatReplacement> listData = new ArrayList<HxServiceRetreatReplacement>();
		HxServiceRetreatReplacement serviceRetreat = hxRetreatReplacementCreateService.getRetreatReplacementById(applyId);
		Map<String, String> map_sf = Constrants.CODEMAP.get("sf");
		Map<String, String> map_thj = Constrants.CODEMAP.get("thj");		
		Map<String, String> map_thms = Constrants.CODEMAP.get("thms");	
		Map<String, String> map_gmdm = Constrants.CODEMAP.get("gmdm");
		Map<String, String> map_pp = Constrants.CODEMAP.get("pp");
		serviceRetreat.setIsPresale(map_sf.get(serviceRetreat.getIsPresale()));
		serviceRetreat.setIsOnwall(map_sf.get(serviceRetreat.getIsOnwall()));
		serviceRetreat.setRetreatReplacement(map_thj.get(serviceRetreat.getRetreatReplacement()));
		serviceRetreat.setRrMode(map_thms.get(serviceRetreat.getRrMode()));
		serviceRetreat.setGomeCode(map_gmdm.get(serviceRetreat.getGomeCode()));
		serviceRetreat.setOldGomeCode(map_gmdm.get(serviceRetreat.getOldGomeCode()));
		serviceRetreat.setBrand(map_pp.get(serviceRetreat.getBrand()));
		serviceRetreat.setOldBrand(map_pp.get(serviceRetreat.getOldBrand()));
		listData.add(serviceRetreat);
		JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(listData);
		return data;
	}
	
	public JRDataSource getParcelDeliveryDataSource(String parcelCode)
	{
		HxParcelDelivery parcel = parcelDeliveryService.getHxParcelDeliveryById(parcelCode);
		List<HxParcelDelivery> list=new ArrayList<HxParcelDelivery>();
		list.add(parcel);
		JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(list);
		return data;
	}
	
	public JRDataSource getMaterialManageDataSource(String listNumber)
	{
		HxMaterialManageVO materialMan= hxMaterialService.getMaterialManageByNumber(listNumber);
		materialMan.setType(Constrants.FITTING_TITLE_MAP.get(materialMan.getType()));
		if(hxCodeService.getOrganizationUnloadById(materialMan.getSendCompany())!=null)
		       materialMan.setSendCompany(hxCodeService.getOrganizationUnloadById(materialMan.getSendCompany()).getName());
		if(hxCodeService.getOrganizationUnloadById(materialMan.getReceiveCompany())!=null)
		       materialMan.setReceiveCompany(hxCodeService.getOrganizationUnloadById(materialMan.getReceiveCompany()).getName());
		Map<String, String> map = Constrants.CODEMAP.get("djzt");
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> iter = set.iterator();
		while(iter.hasNext()){
			Entry<String, String> entry = iter.next();
			if(entry.getKey().equals(materialMan.getStatus())){
				materialMan.setStatus(entry.getValue());
				break;
			}
		}
		List<HxMaterialManageVO> list=new ArrayList<HxMaterialManageVO>();
		list.add(materialMan);
		JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(list);
		return data;
	}
	
	public JRDataSource getMaterialDataSource(String listNumber,String inOut){
		HxMaterialManageVO materialMan=hxMaterialService.getMaterialByNumber(listNumber);
		materialMan.setType(Constrants.FITTING_TITLE_MAP.get(materialMan.getType()));
		// 根据出入库设置批次号
		if("1".equals(inOut))
			materialMan.setBatch(materialMan.getOutBatch());
		else
			materialMan.setBatch(materialMan.getInBatch());
		List<HxMaterialManageVO> list=new ArrayList<HxMaterialManageVO>();
		list.add(materialMan);
		JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(list);
		return data;
	}
	
	public JRDataSource getDataSource(String id,String type,String inOut){
		JRDataSource dataSource=null;
		if(Constrants.PRINT_TYPE_RETURNMACHINE.equals(type)){
			dataSource= getRetreatReplacementDataSource(id);
		}else if(Constrants.PRINT_TYPE_PARCELTRANSCEIVER.equals(type)){
			dataSource= getParcelDeliveryDataSource(id);
		}else if(Constrants.PRINT_TYPE_MATERIALMANAGE.equals(type)){
			dataSource=getMaterialManageDataSource(id);
		}else if(Constrants.PRINT_TYPE_INOUTSTOCK.equals(type)){
			dataSource=getMaterialDataSource(id,inOut);
		}
		return dataSource;
	}
}
