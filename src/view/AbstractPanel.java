package view;

import javax.swing.*;
import java.awt.*;

public class AbstractPanel extends JPanel {
    protected GridBagLayout layout;
    protected GridBagConstraints constraints;

    public AbstractPanel() {
        constraints = new GridBagConstraints();
        layout = new GridBagLayout();
        setLayout(layout);
        setBorder(BorderFactory.createEtchedBorder());
    }

    public void adicionarComponente(JComponent component, int y, int x, int pos, int cols, int lins, int preenche){
        constraints.gridy = y;
        constraints.gridx = x;

        constraints.insets = new Insets(10,10,10,10);
        constraints.anchor = pos;

        constraints.gridwidth = cols;
        constraints.gridheight = lins;

        constraints.fill = preenche;

        component.setFont(new Font("tahoma", Font.PLAIN, 20));

        layout.setConstraints(component, constraints);
        add(component);
    }
}
