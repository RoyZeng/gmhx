package gmhx.webservice.templete.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.gome.gmhx.entity.EccBrand;
import com.gome.gmhx.webservice.wsdl.EccWService;


public class TestService {
	public static void main(String[] args) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(EccWService.class);
		factory.setAddress("http://10.2.31.220:8080/gmhx/service/eccWService");
		EccWService service = (EccWService) factory.create();
		
		EccBrand eccBrand = new EccBrand();
		eccBrand.setProdh("1");
		eccBrand.setStufe("1");
		eccBrand.setUpdateFlag("1");
		eccBrand.setVtext("1");
		
		List<EccBrand> list = new ArrayList<EccBrand>();
		list.add(eccBrand);
        service.handleEccBrand(list);
	}
}
