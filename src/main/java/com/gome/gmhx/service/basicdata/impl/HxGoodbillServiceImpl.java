package com.gome.gmhx.service.basicdata.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gome.common.page.Page;
import com.gome.gmhx.dao.basicdata.HxGoodbillDao;
import com.gome.gmhx.entity.HxGoodbill;
import com.gome.gmhx.entity.HxGoodbillKey;
import com.gome.gmhx.service.basicdata.HxGoodbillService;

@Service("hxGoodbillService")
public class HxGoodbillServiceImpl implements HxGoodbillService {

	@Resource
	private HxGoodbillDao hxGoodbillDao;
	@Override
	public void insertGoodbill(HxGoodbill entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteGoodbillByKey(HxGoodbillKey key) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateSale(HxGoodbill entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, String> insertGoodbills(List<HxGoodbill> bills) {

		Map<String, String> resultMap = new HashMap<String, String>();
		String exists = "{";
		String success = "{";
		String failure = "{";
		
		for(int i=0 ; i< bills.size() ; i++){
			HxGoodbill bill = bills.get(i);
			HxGoodbillKey key = new HxGoodbillKey();
			key.setGsxx01(bill.getGsxx01());
			key.setThdh(bill.getThdh());
			HxGoodbill temp = hxGoodbillDao.selectByPrimaryKey(key);
			if(temp!=null){
				exists +=(i+2)+",";
			}else{
				String gsxx =bill.getGsxx01();
				Long thdh =bill.getThdh();
				if("".equals(gsxx)|| thdh==null || gsxx==null){
					failure +=(i+2)+",";
				}else{
					try{
						hxGoodbillDao.insert(bill);
						success +=(i+2)+",";
					}catch(Exception e){
						failure +=(i+2)+",";
					}
				}
			}
		}
		resultMap.put("exists", exists.length()==1 ? exists+"}":exists.subSequence(0, exists.length()-1)+"}");
		resultMap.put("success", success.length()==1 ? success+"}":success.subSequence(0, success.length()-1)+"}");
		resultMap.put("failure", failure.length()==1 ? failure+"}":failure.subSequence(0, failure.length()-1)+"}");
		return resultMap;
	
	}

	@Override
	public HxGoodbill getGoodbillByPrimaryKey(List<String> asList) {
		HxGoodbillKey key = new HxGoodbillKey();
		key.setGsxx01(asList.get(0));
		key.setThdh(new Long(asList.get(1)));
		return hxGoodbillDao.selectByPrimaryKey(key);
	}

	@Override
	public List<Map<String, Object>> getHxGoodbillPageList(Page page) {
		return hxGoodbillDao.getHxGoodbillPageList(page);
	}

	@Override
	public List<Map<String, Object>> getBillNumExport(HxGoodbill bill) {
		return hxGoodbillDao.getBillNumExport(bill);
	}

}
