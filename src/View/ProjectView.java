package View;

import javax.smartcardio.Card;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ProjectView extends JFrame {
    private JTextArea resultTextArea;
    private Component rigidArea;

    public ProjectView() {
        setTitle("Database Query GUI");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);

        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.PAGE_AXIS));

        for (int i = 0; i < 5; i++) {
            Button button = new Button("Abfrage " + i);
            buttons.add(button);
        }

        // Add components to the frame
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.PAGE_AXIS));
        sidePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JLabel selectQueryLabel = new JLabel("Select Query:");
        selectQueryLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label
        sidePanel.add(selectQueryLabel);

        rigidArea = Box.createRigidArea(new Dimension(0, getHeight() / 5)); // Initial height is 20% of the frame height
        sidePanel.add(rigidArea);
        sidePanel.add(buttons);
        add(sidePanel, BorderLayout.WEST);
        add(new JScrollPane(resultTextArea), BorderLayout.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                rigidArea.setMaximumSize(new Dimension(0, getHeight() / 5));
                rigidArea.setPreferredSize(new Dimension(0, getHeight() / 5));
                sidePanel.revalidate();
            }
        });
    }

    private String[] getQueries() {
        // This method should return an array of query strings
        return new String[]{};
    }

    private String executeQuery(String query) {
        // This method should execute the query and return the result as a string
        return "";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ProjectView().setVisible(true);
            }
        });
    }
}