package ETS.jm.service;

import ETS.jm.mapper.JmMapper;
import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**************************************************
* @FileName   : BoardService.java
* @Description: 공지사항 관리 비지니스 로직
* @Author     : Seung-Jun. Kim
* @Version    : 2020. 1. 31.
* @Copyright  : ⓒADUP. All Right Reserved
**************************************************/
@Service
public class JmService {

	@Autowired
	private JmMapper JmMapper;
	
	/**************************************************
	* @MethodName : getNoticeList
	* @Description: 공지사항 리스트
	* @param paramMap
	* @return List<CamelMap>
	* @throws Exception
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 1. 31.
	**************************************************/
	public List<CamelMap> getNoticeList(DataMap paramMap) throws Exception {
		return JmMapper.getJmList(paramMap);		
	}
	
	/**************************************************
	* @MethodName : getNoticeListCount
	* @Description: 공지사항 리스트 카운트
	* @param paramMap
	* @return int
	* @throws Exception
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 1. 31.
	**************************************************/
	public int getNoticeListCount(DataMap paramMap) throws Exception {
		return JmMapper.getJmListCount(paramMap);
	}
	
	/**************************************************
	* @MethodName : getNoticeView
	* @Description: 공지사항 상세보기
	* @param paramMap
	* @return CamelMap
	* @throws SQLException
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 1. 31.
	**************************************************/
	public CamelMap getNoticeView(DataMap paramMap) throws SQLException{
		return JmMapper.getJmView(paramMap);
	}
	
	/*
	 * 
	 * 
	 * 
	 * 등록, 수정, 삭제
	 * 
	 * 
	 * 
	 * 
	 */
	
	/**************************************************
	* @MethodName : deleteNotice
	* @Description: 공지사항 삭제
	* @param paramMap
	* @return boolean
	* @throws Exception
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 1. 31.
	**************************************************/
	public boolean deleteNotice(DataMap paramMap) throws Exception{
		int rst = 0;
		rst = this.JmMapper.deleteJm(paramMap);
		
		return rst > 0 ? true : false;
	}
	
	/**************************************************
	* @MethodName : insertNotice
	* @Description: 공지사항 등록
	* @param paramMap
	* @return int
	* @throws Exception
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 2. 4.
	**************************************************/
	public int insertJm(DataMap paramMap) throws Exception {
		return JmMapper.insertJm(paramMap);
	}
	
	/**************************************************
	* @MethodName : updateNotice
	* @Description: 공지사항 수정
	* @param paramMap
	* @return int
	* @throws Exception
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 2. 4.
	**************************************************/
	public int updateJm(DataMap paramMap) throws Exception {
		return JmMapper.updateJm(paramMap);
	}	
}
