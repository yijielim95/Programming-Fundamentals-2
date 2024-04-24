/**
 * A scaled down version of a Product class.
 *
 * @author Mairead Meagher, Siobhan Drohan
 */
public class Product {
    private String productName = "";
    private int productCode = 5000 ;
    private double unitCost = 1;
    private boolean inCurrentProductLine;
    public Product(String productName, int productCode, double unitCost, boolean inCurrentProductLine) {
        if (productName != null){
            if (productName.length() >20){
                this.productName = productName.substring(0,20);
            }
            else{
                this.productName = productName;
            }
        }
       setProductCode(productCode);
       setUnitCost(unitCost);
       setInCurrentProductLine(inCurrentProductLine);
    }
    public String getProductName(){
        return productName;
    }
    public double getUnitCost(){
        return unitCost;
    }
    public int getProductCode() {
        return productCode;
    }
    public boolean isInCurrentProductLine() {
        return inCurrentProductLine;
    }
    public void setProductCode(int productCode) {
        if((productCode >= 1000) && (productCode<=5000)){
            this.productCode = productCode;
        }
    }
    public void setProductName(String productName) {
        if (productName != null){
            if (productName.length() <= 20) {
                this.productName = productName;
            }
        }
    }
    public void setUnitCost(double unitCost) {
        if ((unitCost > 0) && (unitCost <= 99999)) {
            this.unitCost = unitCost;
        }
    }
    public void setInCurrentProductLine(boolean inCurrentProductLine) {
        this.inCurrentProductLine = inCurrentProductLine;
    }
    public String toString()
    {
        String inCurrentProductLine = isInCurrentProductLine() ? "Y" : "N";
        return "Product description: " + productName +
               ", product code: " + productCode +
               ", unit cost: " + unitCost +
               ", currently in product line: " + inCurrentProductLine;
    }

}
