package meeting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.city.bean.City;
import com.qifenqian.bms.basemanager.city.service.CityService;

@Controller
@RequestMapping("/test")
public class AutoTestCotroller {
	
	@Autowired
	private CityService cityService;

	
	@RequestMapping("/autocomplete")
	public ModelAndView toMain(){
		ModelAndView mv =new ModelAndView("/test/autocomplete");
		List<City> areaList = cityService.getAreaByCityId("229");
		
		
		mv.addObject("areaList", JSONObject.toJSONString(areaList));
		return mv;
	}
	
	@RequestMapping("/select")
	@ResponseBody
	public String getData(){
		
		List<City> areaList = cityService.getAreaByCityId("229");
		
		return JSON.toJSONString(areaList);
	}
}
