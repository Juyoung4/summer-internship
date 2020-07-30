package ETS.index.mapper;

import java.sql.SQLException;
import java.util.List;

import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;

/**************************************************
* @FileName   : IndexMapper.java
* @Description: 메인 인터페이스
* @Author     : Seung-Jun. Kim
* @Version    : 2020. 2. 4.
* @Copyright  : ⓒADUP. All Right Reserved
**************************************************/
public interface IndexMapper {
	
	/**************************************************
	* @MethodName : getBannerList
	* @Description: 배너 리스트
	* @param paramMap
	* @return List<CamelMap>
	* @throws SQLException
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 2. 4.
	**************************************************/
	List<CamelMap> getBannerList(DataMap paramMap) throws SQLException;
	
	/**************************************************
	* @MethodName : getPopupList
	* @Description: 팝업 리스트
	* @param paramMap
	* @return
	* @throws SQLException List<CamelMap>
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 2. 4.
	**************************************************/
	List<CamelMap> getPopupList(DataMap paramMap) throws SQLException;
	
	/**************************************************
	* @MethodName : getNewsList
	* @Description: 언론기사 리스트
	* @param paramMap
	* @return List<CamelMap>
	* @throws SQLException
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 2. 4.
	**************************************************/
	List<CamelMap> getNewsList(DataMap paramMap) throws SQLException;
}