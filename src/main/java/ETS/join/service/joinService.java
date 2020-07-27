package ETS.join.service;

import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;
import ETS.join.mapper.joinMapper;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class joinService {
	@Autowired
	private joinMapper joinMapper;
	
	public List<CamelMap> getUserList(DataMap paramMap) throws Exception {
		return joinMapper.getUserList(paramMap);		
	}
	
	public List<CamelMap> getDUserList(DataMap paramMap) throws Exception {
		return joinMapper.getDUserList(paramMap);		
	}
	
	public int getUserListCount(DataMap paramMap) throws Exception {
		return joinMapper.getUserListCount(paramMap);
	}
	
	public int getDUserListCount(DataMap paramMap) throws Exception {
		return joinMapper.getDUserListCount(paramMap);
	}
	
	public boolean userDelay(DataMap paramMap) throws Exception{
		int rst = 0;
		rst = this.joinMapper.userDelay(paramMap);
		
		return rst > 0 ? true : false;
	}
	
	public boolean userApprove(DataMap paramMap) throws Exception{
		int rst = 0;
		rst = this.joinMapper.userApprove(paramMap);
		
		return rst > 0 ? true : false;
	}
	
	public boolean DuserApprove(DataMap paramMap) throws Exception{
		int rst = 0;
		rst = this.joinMapper.DuserApprove(paramMap);
		
		return rst > 0 ? true : false;
	}
}
