package ETS.common.mapper;

import java.sql.SQLException;

import ETS.common.util.CamelMap;
import ETS.common.util.DataMap;


/**************************************************
* @FileName   : CommonMapper.java
* @Description: 공통 인터페이스
* @Author     : Jong-Hoon. Jung
* @Version    : 2019. 1. 30.
* @Copyright  : ⓒADUP. All Right Reserved
**************************************************/
public interface CommonMapper {
	
	/**************************************************
	* @MethodName : selectUserLoginInfo
	* @Description: 로그인 회원정보
	* @param paramMap
	* @return CamelMap
	* @throws SQLException
	* @Author     : Seung-Jun. Kim
	* @Version    : 2020. 1. 29.
	**************************************************/
	CamelMap selectUserLoginInfo(DataMap paramMap) throws SQLException;
}