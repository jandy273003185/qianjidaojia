package com.qifenqian.bms.basemanager.branchbank.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.branchbank.bean.BranchBank;

@MapperScan
public interface BranchBankMapper {

	public List<BranchBank> branchBankList(BranchBank queryBean);

	public BranchBank selectBankCnaps(@Param("bankCnaps") String bankCnaps);

	public int insertBranchBank(BranchBank insertBean);

	public int updateBranchBank(BranchBank updateBean);

	public int deleteBranchBank(BranchBank deleteBean);

}
