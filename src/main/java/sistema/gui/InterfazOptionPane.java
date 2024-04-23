package sistema.gui;

import java.awt.*;

public interface InterfazOptionPane {
    void showMessageDialog(Component parentComponent, Object message, String title, int messageType);
    void ShowMessage(Component parent, String mensaje);
}
