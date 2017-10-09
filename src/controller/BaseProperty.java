package controller;

import org.springframework.beans.factory.annotation.Value;

public class BaseProperty {
	
	/**
	 * 订单模板编号
	 */
	protected String Template_NewOrder;
	
	
	@Value("#{propertyConfigurerBean['template.new']}")
	public String setTemplate_NewOrder(String templateNew) {
		Template_NewOrder = templateNew;
		return Template_NewOrder;
	}
}
