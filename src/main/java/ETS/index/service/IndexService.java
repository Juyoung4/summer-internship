package ETS.index.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;
import ETS.index.mapper.IndexMapper;

/**************************************************
* @FileName   : IndexService.java
* @Description: 메인 비지니스 로직
* @Author     : Seung-Jun. Kim
* @Version    : 2020. 2. 4.
* @Copyright  : ⓒADUP. All Right Reserved
**************************************************/
@Service
public class IndexService {

	@Autowired
	private IndexMapper indexMapper;
	
	/**************************************************
	* @MethodName : getIndexList
	* @Description: 베너 리스트
	* @param paramMap
	* @return List<CamelMap>
	* @throws Exception
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 2. 4.
	**************************************************/
	public List<CamelMap> getBannerList(DataMap paramMap) throws Exception {
		return indexMapper.getBannerList(paramMap);		
	}
	
	/**************************************************
	* @MethodName : getPopupList
	* @Description: 팝업 리스트
	* @param paramMap
	* @return List<CamelMap>
	* @throws SQLException
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 2. 4.
	**************************************************/
	public List<CamelMap> getPopupList(DataMap paramMap) throws SQLException {
		return indexMapper.getPopupList(paramMap);
	}
	
	/**************************************************
	* @MethodName : getNewsList
	* @Description: 언론기사 리스트
	* @param paramMap
	* @return List<CamelMap>
	* @throws SQLException
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 2. 4.
	**************************************************/
	public List<CamelMap> getNewsList(DataMap paramMap) throws SQLException {
		return indexMapper.getNewsList(paramMap);
	}
}
