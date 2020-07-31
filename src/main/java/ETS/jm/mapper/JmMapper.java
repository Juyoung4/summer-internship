package ETS.jm.mapper;

import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;

import java.sql.SQLException;
import java.util.List;

/**************************************************
* @FileName   : BoardMapper.java
* @Description: 공지사항 관리 인터페이스
* @Author     : Seung-Jun. Kim
* @Version    : 2020. 1. 31.
* @Copyright  : ⓒADUP. All Right Reserved
**************************************************/
public interface JmMapper {
	
	/**************************************************
	* @MethodName : getNoticeList
	* @Description: 공지사항 리스트
	* @param paramMap
	* @return List<CamelMap>
	getBoardNotice* @throws SQLException
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 1. 31.
	**************************************************/
	List<CamelMap> getJmList(DataMap paramMap) throws SQLException;
	
	/**************************************************
	* @MethodName : getNoticeListCount
	* @Description: 공지사항 리스트 카운트
	* @param paramMap
	* @return int
	* @throws SQLException
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 1. 31.
	**************************************************/
	int getJmListCount(DataMap paramMap) throws SQLException;
	
	/**************************************************
	* @MethodName : getNoticeView
	* @Description: 공지사항 상세보기
	* @param paramMap
	* @return CamelMap
	* @throws SQLException
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 1. 31.
	**************************************************/
	CamelMap getJmView(DataMap paramMap) throws SQLException;
	
	/*
	 * 
	 * 
	 * 등록,수정,삭제
	 * 
	 * 
	 * 
	 * 
	 */
	
	/**************************************************
	* @MethodName : deleteNotice
	* @Description: 공지사항 삭제
	* @param paramMap
	* @return int
	* @throws SQLException
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 1. 31.
	**************************************************/
	int deleteJm(DataMap paramMap) throws SQLException;
	
	/**************************************************
	* @MethodName : insertNotice
	* @Description: 공지사항 등록
	* @param paramMap
	* @return int
	* @throws SQLException
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 2. 4.
	**************************************************/
	int insertJm(DataMap paramMap) throws SQLException;
	
	/**************************************************
	* @MethodName : updateNotice
	* @Description: 공지사항 수정
	* @param paramMap
	* @return int
	* @throws SQLException
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 2. 4.
	**************************************************/
	int updateJm(DataMap paramMap) throws SQLException;
}