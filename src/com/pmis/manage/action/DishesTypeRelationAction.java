package com.pmis.manage.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pmis.manage.service.DishesTypeRelationService;
@Controller
@RequestMapping("/dishesTypeRelationAction")
public class DishesTypeRelationAction {
	private DishesTypeRelationService dishesTypeRelationService;

	public DishesTypeRelationService getDishesTypeRelationService() {
		return dishesTypeRelationService;
	}
	@Resource
	public void setDishesTypeRelationService(
			DishesTypeRelationService dishesTypeRelationService) {
		this.dishesTypeRelationService = dishesTypeRelationService;
	}
	
	
}
