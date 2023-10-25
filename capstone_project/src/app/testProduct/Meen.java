package app.testProduct;

public class Meen {

	public static void main(String[] args) {
		Transaction transact = new Transaction();
		Database db = new Database();
		
		Category categ = new Category(1);
		categ.setSomething(db, transact);
		categ.setProducts();
		categ.testPrint();
		
		
		

	}

}
