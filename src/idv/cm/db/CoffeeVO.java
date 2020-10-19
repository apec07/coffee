package idv.cm.db;

public class CoffeeVO implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private String cof_name;
	private int sup_id;
	private float price;
	private int sales;
	private int total;
	
	public CoffeeVO() {}

	public String getCof_name() {
		return cof_name;
	}

	public void setCof_name(String cof_name) {
		this.cof_name = cof_name;
	}

	public int getSup_id() {
		return sup_id;
	}

	public void setSup_id(int sup_id) {
		this.sup_id = sup_id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getSales() {
		return sales;
	}

	public void setSales(int sales) {
		this.sales = sales;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "CoffeeVO [cof_name=" + cof_name + ", price=" + price + "]";
	}


	
	
}
