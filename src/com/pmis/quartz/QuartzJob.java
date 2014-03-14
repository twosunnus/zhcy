//package com.pmis.quartz;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.annotation.Resource;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.pmis.dbhkf.model.GameInfoTmp;
//import com.pmis.dbhkf.service.GameInfoService;
//import com.pmis.dbhkf.util.GetGame07073;
//import com.pmis.dbhkf.util.GetGame265;
//import com.pmis.dbhkf.util.GetGame521;
//import com.pmis.dbhkf.util.GetGame5336;
//
//@Component
//public class QuartzJob {
//	
//	public GameInfoService gameInfoService;
//	
//	public static Map<String,List<String>> operatorMap = new HashMap();
//	public QuartzJob(){
//		List l = new ArrayList();
//		l.add("wan78");
//		operatorMap.put("wan78平台", l);
//		l = new ArrayList();
//		l.add("11yeyou");
//		operatorMap.put("11页游", l);
//		l = new ArrayList();
//		l.add("202wan游戏");
//		operatorMap.put("202wan", l);
//		
//		l = new ArrayList();
//		l.add("2866");
//		operatorMap.put("2866游戏", l);
//		
//		l = new ArrayList();
//		l.add("33456");
//		operatorMap.put("33456平台", l);
//		
//		l = new ArrayList();
//		l.add("360uu平台");
//		l.add("360uu游戏");
//		operatorMap.put("360uu", l);
//		
//		l = new ArrayList();
//		l.add("54op");
//		operatorMap.put("54op游戏", l);
//		
//		l = new ArrayList();
//		l.add("556wan网页游戏平台");
//		operatorMap.put("556wan网页游戏", l);
//
//		l = new ArrayList();
//		l.add("56uu");
//		operatorMap.put("56uu平台", l);
//
//		l = new ArrayList();
//		l.add("57k");
//		operatorMap.put("57K游戏", l);
//
//		l = new ArrayList();
//		l.add("602游戏平台");
//		operatorMap.put("602游戏", l);
//
//		l = new ArrayList();
//		l.add("6637");
//		operatorMap.put("6637页游", l);
//
//		l = new ArrayList();
//		l.add("844a");
//		operatorMap.put("844a游戏中心", l);
//
//		l = new ArrayList();
//		l.add("8641");
//		operatorMap.put("8641网页游戏", l);
//
//		l = new ArrayList();
//		l.add("88网页游戏");
//		operatorMap.put("88游戏", l);
//
//		l = new ArrayList();
//		l.add("92ha游戏平台");
//		operatorMap.put("92ha", l);
//
//		l = new ArrayList();
//		l.add("930wan游戏平台");
//		l.add("930wan");
//		operatorMap.put("930wan游戏", l);
//
//		l = new ArrayList();
//		l.add("dcwan平台");
//		operatorMap.put("dcwan", l);
//
//		l = new ArrayList();
//		l.add("hao2u");
//		operatorMap.put("hao2u平台", l);
//
//		l = new ArrayList();
//		l.add("ppwan游戏");
//		operatorMap.put("ppwan", l);
//
//		l = new ArrayList();
//		l.add("嘟嘟牛游戏");
//		operatorMap.put("嘟嘟牛", l);
//
//		l = new ArrayList();
//		l.add("我游网");
//		operatorMap.put("我游", l);
//
//		l = new ArrayList();
//		l.add("爱游网");
//		operatorMap.put("爱游", l);
//
//		l = new ArrayList();
//		l.add("狐玩网");
//		operatorMap.put("狐玩游戏", l);
//
//		l = new ArrayList();
//		l.add("狗扑");
//		operatorMap.put("狗扑页游", l);
//
//		l = new ArrayList();
//		l.add("玩玩网");
//		l.add("玩玩平台");
//		operatorMap.put("玩玩", l);
//
//		l = new ArrayList();
//		l.add("蓝港在线平台");
//		operatorMap.put("蓝港在线", l);
//
//		l = new ArrayList();
//		l.add("要玩");
//		l.add("要玩平台");
//		operatorMap.put("要玩游戏", l);
//
//		l = new ArrayList();
//		l.add("迅雷牛x");
//		operatorMap.put("迅雷牛X页游", l);
//
//	}
//	
//	@Scheduled(cron="00 53 10 * * ?")
//	public void work() throws Exception{
//		System.out.println("任务开始");
//		GetGame265 g265 = new GetGame265();
//		GetGame07073 g07073 = new GetGame07073();
//		GetGame521 g521 = new GetGame521();
//		GetGame5336 g5336 = new GetGame5336();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
//		Date d = new Date();
//		d.setDate(new Date().getDate());
//		String now = sdf.format(d);
//		List<GameInfoTmp> gameList265 = new ArrayList<GameInfoTmp>();
//		List<GameInfoTmp> gameList07073 = new ArrayList<GameInfoTmp>();
//		List<GameInfoTmp> gameList521 = new ArrayList<GameInfoTmp>();
//		List<GameInfoTmp> gameList5336 = new ArrayList<GameInfoTmp>();
//		g265.get(gameList265, now);
//		System.out.println("265数据采集完毕,共"+gameList265.size()+"条记录");
//		g07073.get(gameList07073, now);
//		System.out.println("07073数据采集完毕,共"+gameList07073.size()+"条记录");
//		g521.get(gameList521, now);
//		System.out.println("521数据采集完毕,共"+gameList521.size()+"条记录");
//		g5336.get(gameList5336, now);
//		System.out.println("5336数据采集完毕,共"+gameList5336.size()+"条记录");
//		
//		System.out.println("向临时表插入数据");
//		gameInfoService.saveForTmpList(gameList265);
//		gameInfoService.saveForTmpList(gameList07073);
//		gameInfoService.saveForTmpList(gameList521);
//		gameInfoService.saveForTmpList(gameList5336);
//		
//		//更新游戏厂商名称规范
//		System.out.println("更新游戏厂商名称");
//		gameInfoService.updateOperator(operatorMap);
//		
//		//更新游戏对应类型
//		System.out.println("开始更新游戏类型");
//		gameInfoService.updateGameType();
//		
//		//向实际表中插入数据
//		System.out.println("向实际表中插入数据");
//		gameInfoService.saveForGameList();
//		
//		System.out.println("删除临时表数据");
//		gameInfoService.deleteGameInfoTmp(gameList265);
//		gameInfoService.deleteGameInfoTmp(gameList07073);
//		gameInfoService.deleteGameInfoTmp(gameList521);
//		gameInfoService.deleteGameInfoTmp(gameList5336);
//	}
//
//
//	public GameInfoService getGameInfoService() {
//		return gameInfoService;
//	}
//
//	@Resource
//	public void setGameInfoService(GameInfoService gameInfoService) {
//		this.gameInfoService = gameInfoService;
//	}
//	
//	
//	
//	
//}
