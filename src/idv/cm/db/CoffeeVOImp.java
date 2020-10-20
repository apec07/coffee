package idv.cm.db;

import java.sql.Connection;
import java.util.HashSet;

public interface CoffeeVOImp {
	
	int insert(Connection con, CoffeeVO coffee);
	int updateAll(Connection con, HashSet<CoffeeVO> set);
	int delete(Connection con, String cof_name);
	CoffeeVO findByIndex(Connection con,int id);
	HashSet<CoffeeVO> findAll(Connection con);

}
