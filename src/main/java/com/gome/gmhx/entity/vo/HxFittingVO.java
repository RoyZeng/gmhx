package com.gome.gmhx.entity.vo;

import java.util.List;

import com.gome.gmhx.entity.HxFitting;
import com.gome.gmhx.entity.HxFittingFaultCode;
import com.gome.gmhx.entity.HxFittingModel;
import com.gome.gmhx.entity.HxFittingProvider;

public class HxFittingVO {
	private HxFitting fitting;
	private List<HxFittingModel> suits;
	private List<HxFittingProvider> providers;
	private List<HxFittingFaultCode> faultCodes;
	
	public List<HxFittingFaultCode> getFaultCodes() {
		return faultCodes;
	}
	public void setFaultCodes(List<HxFittingFaultCode> faultCodes) {
		this.faultCodes = faultCodes;
	}
	public HxFitting getFitting() {
		return fitting;
	}
	public void setFitting(HxFitting fitting) {
		this.fitting = fitting;
	}
	public List<HxFittingModel> getSuits() {
		return suits;
	}
	public void setSuits(List<HxFittingModel> suits) {
		this.suits = suits;
	}
	public List<HxFittingProvider> getProviders() {
		return providers;
	}
	public void setProviders(List<HxFittingProvider> providers) {
		this.providers = providers;
	}
}
