import javax.swing.SwingUtilities;

public class Forca {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ForcaUI ui = new ForcaUI();
                ui.setVisible(true);
            }
        });
    }
}
