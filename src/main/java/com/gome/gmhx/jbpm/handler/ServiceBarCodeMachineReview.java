package com.gome.gmhx.jbpm.handler;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

import com.gome.common.util.SpringUtil;
import com.gome.gmhx.dao.servicemanage.HxServiceProductDao;
import com.gome.gmhx.dao.wsdl.EccDao;
import com.gome.gmhx.entity.HxServiceProduct;
import com.gome.gmhx.entity.HxServiceTicket;
import com.gome.gmhx.entity.ValidateResult;
import com.gome.gmhx.service.common.MachineReviewService;
import com.gome.gmhx.service.servicemanage.HxServiceTicketQueryService;

public class ServiceBarCodeMachineReview implements DecisionHandler {

	@Override
	public String decide(OpenExecution execution) {
		String dataSource = (String)execution.getVariable("dataSource");
		String key = execution.getProcessInstance().getKey();
		// sp02安装 sp06维修
		String serviceType = execution.getProcessDefinitionId();
		HxServiceTicketQueryService hxServiceTicketQueryService = (HxServiceTicketQueryService) SpringUtil.getBean("hxServiceTicketQueryService");
		HxServiceTicket serviceTicket = hxServiceTicketQueryService.getServiceTicketByServiceId(key);
		String result = null;
		if(serviceType.startsWith("SP02")){
			result = this.validateInstall(serviceTicket,dataSource,key);
		}else if(serviceType.startsWith("SP06")){
			result = this.validateRepaire(key);
		}
		if(!StringUtils.isEmpty(result)){
			serviceTicket.setMachineCheckFaultReason(result);
			serviceTicket.setCheckResult("机审无效");
			hxServiceTicketQueryService.updateServiceTickeetSelective(serviceTicket);
			return "T3";
		}else{
			serviceTicket.setCheckResult("机审有效");
			hxServiceTicketQueryService.updateServiceTickeetSelective(serviceTicket);
			return "T2";
		}
	}
	
	private String validateRepaire(String key){
		MachineReviewService machineReviewService = (MachineReviewService) SpringUtil.getBean("machineReviewService");
		return machineReviewService.hxRepairBillMachineReview(key);
	}
	
	private String validateInstall(HxServiceTicket serviceTicket,String dataSource,String key){
		HxServiceProductDao hxServiceProductDao =(HxServiceProductDao) SpringUtil.getBean("hxServiceProductDao");
		EccDao eccDao = (EccDao) SpringUtil.getBean("eccDao");
		MachineReviewService machineReviewService = (MachineReviewService) SpringUtil.getBean("machineReviewService");
		HxServiceProduct serviceProduct = hxServiceProductDao.getServiceProductByPrimaryKey(serviceTicket.getProductId());
		
		String PPDM = serviceProduct.getBrand();
		String JX = serviceProduct.getMachineType();
		String PLDM = serviceProduct.getCategory();
		String JQTM = serviceProduct.getMachineCode();
		String NJTM = serviceProduct.getInternalMachineCode1();
		String WJTM = serviceProduct.getExternalMachineCode();
		String jlNum = serviceTicket.getJlServiceId();
		
		StringBuffer checkResult = new StringBuffer();
		if("0".equals(dataSource)){
			if(!PLDM.startsWith("R03")||!PLDM.startsWith("r03")) NJTM = JQTM;
			
			if(StringUtils.isEmpty(JX)){
				return checkResult.append("机型为空！").toString();
			}else if(StringUtils.isEmpty(JQTM)&&StringUtils.isEmpty(NJTM)){
				return checkResult.append("条码为空！").toString();
			}
			Map data =  eccDao.getEccGoodsByGoodsCode(serviceProduct.getProductCode());
			if(data==null){
				return checkResult.append("系统中不存在此款商品！").toString();
			}
			String machineType =(String) data.get("GROES");
			//空调R03  伊莱克斯00011  三洋00018
				if(PLDM.startsWith("R03")||PLDM.startsWith("r03")){
					if("00011".equals(PPDM)){
						if(StringUtils.isNotEmpty(NJTM)){
							String msg = machineReviewService.JlOrCrmServiceBillTwiceMachineReview(machineType+"-G",NJTM,null,jlNum,key);
							if(StringUtils.isNotEmpty(msg)){
								checkResult.append(msg);
							}
						}
						if(StringUtils.isNotEmpty(WJTM)){
							String msg = machineReviewService.JlOrCrmServiceBillTwiceMachineReview(machineType+"-W", WJTM,serviceProduct,jlNum,key);
							if(StringUtils.isNotEmpty(msg)){
								checkResult.append(msg);
							}
						}
						return checkResult.toString();
					}else if("00018".equals(PPDM)){
						if(StringUtils.isNotEmpty(NJTM)){
							String msg = machineReviewService.JlOrCrmServiceBillTwiceMachineReview(machineType.replace("/GW", "/G"), NJTM,null,jlNum,key);
							if(StringUtils.isNotEmpty(msg)){
								checkResult.append(msg);
							}
						}
						if(StringUtils.isNotEmpty(WJTM)){
							String msg = machineReviewService.JlOrCrmServiceBillTwiceMachineReview(machineType.replace("/GW", "/W"), WJTM,serviceProduct,jlNum,key);
							if(StringUtils.isNotEmpty(msg)){
								checkResult.append(msg);
							}
						}
						return checkResult.toString();
					}
				}else{
					if(StringUtils.isNotEmpty(NJTM)){
						String msg = machineReviewService.JlOrCrmServiceBillTwiceMachineReview(machineType, NJTM,serviceProduct,jlNum,key);
						if(StringUtils.isNotEmpty(msg)){
							checkResult.append(msg);
						}
						return checkResult.toString();
					}
				}
				return checkResult.append("条码为空！").toString();
		}else{
			Map<String, String>  map = machineReviewService.splitMachineCode(PLDM,JX,PPDM);
			String insideType = map.get("insideCode");
			String outsideType = map.get("outsideCode");
			
			//空调R03  伊莱克斯00011  三洋00018
			if(PLDM.startsWith("R03")||PLDM.startsWith("r03")){
				if("00011".equals(PPDM)){
					if(StringUtils.isNotEmpty(NJTM)){
						String msg = machineReviewService.hxServiceBillMachineReview(insideType,NJTM,key,null);
						if(StringUtils.isNotEmpty(msg)){
							checkResult.append(msg);
						}
					}
					if(StringUtils.isNotEmpty(WJTM)){
						String msg = machineReviewService.hxServiceBillMachineReview(outsideType,WJTM,key,serviceProduct);
						if(StringUtils.isNotEmpty(msg)){
							checkResult.append(msg);
						}
					}
					return checkResult.toString();
				}else if("00018".equals(PPDM)){
					if(StringUtils.isNotEmpty(NJTM)){
						String msg = machineReviewService.hxServiceBillMachineReview(insideType,NJTM,key,null);
						if(StringUtils.isNotEmpty(msg)){
							checkResult.append(msg);
						}
					}
					if(StringUtils.isNotEmpty(WJTM)){
						String msg = machineReviewService.hxServiceBillMachineReview(outsideType,WJTM,key,serviceProduct);
						if(StringUtils.isNotEmpty(msg)){
							checkResult.append(msg);
						}
					}
					return checkResult.toString();
				}
			}else{
				if(StringUtils.isNotEmpty(NJTM)){
					String msg = machineReviewService.hxServiceBillMachineReview(JX,JQTM,key,serviceProduct);
					if(StringUtils.isNotEmpty(msg)){
						checkResult.append(msg);
					}
					return checkResult.toString();
				}
				return checkResult.append("条码为空！").toString();
			}
			return null;
		}
	}
}
