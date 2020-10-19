package idv.cm.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;

public class MainTest {

	public static void main(String[] args) {
		Connection con = null;
		try {
			con = ConnectionFactory.getInstance().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(con==null) {
			System.err.println("no connection");
			return;
		}
		
		CoffeeDAO dao = new CoffeeDAO();
		HashSet<CoffeeVO> set = dao.findAll(con);
		System.out.println(set.size());
		System.out.println("-----------------");
		set.forEach(p->System.out.println(p));
		System.out.println("-----------------");
		int[] newSales = new int[]{20,30,40,50,60};
		Iterator<CoffeeVO> it = set.iterator();
		int index=0;
		while(it.hasNext()) {
		
			CoffeeVO coffee = it.next();
			coffee.setSales(newSales[index]);
			System.out.println(coffee.getCof_name()+" = "+coffee.getSales());
			index++;
		}
		
		int i = dao.updateAll(con, set);
		System.out.println("update = "+i);
	}

}
