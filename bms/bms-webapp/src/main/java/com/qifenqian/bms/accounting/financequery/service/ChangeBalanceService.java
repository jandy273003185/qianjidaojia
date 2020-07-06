package com.qifenqian.bms.accounting.financequery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.financequery.bean.ChangeBalance;
import com.qifenqian.bms.accounting.financequery.mapper.ChangeBalanceMapper;

@Service
public class ChangeBalanceService {
	@Autowired
	private ChangeBalanceMapper mapper;

	public List<ChangeBalance> changeBalanceList(ChangeBalance changeBalance) {
		List<ChangeBalance> list = mapper.changeBalanceList(changeBalance);
		List<ChangeBalance> sumList = this.changeBalanceSum(changeBalance);
		for (int i = 0; i < sumList.size(); i++) {
			if (null != sumList.get(i)) {
				sumList.get(i).setSubjectName("合计");
			}

			list.add(sumList.get(i));
		}

		return list;
	}

	public List<ChangeBalance> changeBalanceSum(ChangeBalance changeBalance) {
		List<ChangeBalance> sum = mapper.changeBalanceSum(changeBalance);
		return sum;
	}

}
