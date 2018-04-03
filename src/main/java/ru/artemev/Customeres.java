package ru.artemev;

class Customeres implements ru.artemev.interfaces.Customer{
    private static int statCustNumber = 0;
    private int custNumber;
    private int prodTaken;
    private int purchNumber;

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    private Stock stock;

    Customeres(){
        statCustNumber++;
        this.custNumber = statCustNumber;
    }

    public int getProdTaken() {
        return prodTaken;
    }

    public int getPurchNumber() {
        return purchNumber;
    }

    public void takeProduct(int count){
        int prodForOnePurch = stock.takeProduct(count, custNumber);
        prodTaken += prodForOnePurch;
        if (!stock.stockIsEmpty()) {
            purchNumber++;
        }
    }
}
