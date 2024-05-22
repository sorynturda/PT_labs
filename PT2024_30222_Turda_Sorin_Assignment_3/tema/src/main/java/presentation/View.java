package presentation;

public class View {

    private ClientWindow clientWindow;
    private ProductWindow productWindow;
    private OrderrWindow orderrWindow;
    public View(){
        clientWindow = new ClientWindow();
        productWindow = new ProductWindow();
        orderrWindow = new OrderrWindow();
    }
    public void setVisible(boolean value){
        clientWindow.setVisible(value);
        productWindow.setVisible(value);
        orderrWindow.setVisible(value);
    }
}
