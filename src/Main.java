import javax.swing.*;
public class Main {
    public static void main (){
        SwingUtilities.invokeLater(() -> new CalculadoraGUI().setVisible(true));
    }
}

