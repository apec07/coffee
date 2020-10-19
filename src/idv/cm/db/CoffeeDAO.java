package idv.cm.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class CoffeeDAO implements CoffeeVOImp {

	private static final String INSERT_STMT = "insert into COFFEES (COF_NAME,SUP_ID,PRICE,SALES,TOTAL) values (?,?,?,0,0)";
	private static final String GET_ALL_STMT = "select * from COFFEES order by COF_NAME";
	private static final String GET_ONE_STMT = "select * from COFFEES where COF_NAME = ?";
	private static final String DELETE_STMT = "delete from COFFEES where COF_NAME =?";
	private static final String UPDATE_STMT = "update COFFEES set SALES=?, TOTAL=TOTAL+SALES where COF_NAME=?";

	@Override
	public int insert(Connection con, CoffeeVO user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAll(Connection con, HashSet<CoffeeVO> set) {
		CoffeeVO coffee = null;
		Iterator<CoffeeVO> it = set.iterator();
		int count = 0;
		while (it.hasNext()) {
			coffee = it.next();
			int sales = coffee.getSales();
			String cof_name = coffee.getCof_name();
			PreparedStatement update;
			try {

				update = con.prepareStatement(UPDATE_STMT);
				update.setInt(1, sales);
				update.setString(2, cof_name);
				int result = update.executeUpdate();
				count += result;
			} catch (SQLException e) {
				System.err.println(e);
			}

		}
		return count;
	}

	@Override
	public int delete(Connection con, String cof_name) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CoffeeVO findByIndex(Connection con, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashSet<CoffeeVO> findAll(Connection con) {
		HashSet<CoffeeVO> set = new LinkedHashSet<>();

		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(GET_ALL_STMT);
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnLength = rsmd.getColumnCount();
			String columnNames[] = new String[columnLength];
			String columnValues[] = new String[columnLength];
			for (int i = 1; i <= columnLength; i++) {
				columnNames[i - 1] = rsmd.getColumnName(i);
			}
			while (rs.next()) {
				for (int i = 0; i < columnLength; i++) {
					columnValues[i] = rs.getString(columnNames[i]);
				}
				CoffeeVO coffee = new CoffeeVO();
				coffee.setCof_name(columnValues[0]);
				coffee.setSup_id(Integer.parseInt(columnValues[1]));
				coffee.setPrice(Float.parseFloat(columnValues[2]));
				coffee.setSales(Integer.parseInt(columnValues[3]));
				coffee.setTotal(Integer.parseInt(columnValues[4]));

				set.add(coffee);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}

		return set;
	}

}
