package gmhx.webservice.templete.test;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gome.common.Constrants;
import com.gome.gmhx.entity.HxServiceRetreatReplacement;
import com.gome.gmhx.service.servicemanage.HxRetreatReplacementCreateService;

@Controller
@RequestMapping(value="/HxJasperReport")
public class HxJasperReportController {

	@Resource
	private HxRetreatReplacementCreateService hxRetreatReplacementCreateService;
	
	@RequestMapping(value="/print")
	@ResponseBody
	public void print(HttpServletRequest request,HttpServletResponse response,String id,String type)
			throws ServletException, IOException{
		File jasperFile=null;
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("unit_mc", "国美电器有限公司");
		JasperPrint jasperPrint = null;
		try {
			jasperFile = new File(request.getSession().getServletContext()
					.getRealPath("/WEB-INF/jasper/RetreatReplacementCreate.jasper"));
			JasperReport jasperReport = (JasperReport) JRLoader
					.loadObject(jasperFile.getPath());
			jasperPrint = JasperFillManager.fillReport(jasperReport,
					parameters, getRetreatReplacementDataSource(id));
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
	
}

