package ETS.textNotice.service;

import ETS.textNotice.mapper.textNoticeMapper;
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
public class textNoticeService {

	@Autowired
	private textNoticeMapper textNoticeMapper;
	
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
		return textNoticeMapper.getNoticeList(paramMap);		
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
		return textNoticeMapper.getNoticeListCount(paramMap);
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
		return textNoticeMapper.getNoticeView(paramMap);
	}
	
	public CamelMap getNoticePreview(DataMap paramMap) throws SQLException{
		return textNoticeMapper.getNoticePreview(paramMap);
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
		rst = this.textNoticeMapper.deleteNotice(paramMap);
		
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
	public int insertNotice(DataMap paramMap) throws Exception {
		return textNoticeMapper.insertNotice(paramMap);
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
	public int updateNotice(DataMap paramMap) throws Exception {
		return textNoticeMapper.updateNotice(paramMap);
	}	
	
	//조회수 업데이트
	public boolean updateViewCount(DataMap paramMap) throws Exception {
		return textNoticeMapper.updateViewCount(paramMap);
	}
}
