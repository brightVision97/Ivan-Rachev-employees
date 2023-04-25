import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.List;

public class MainFrame extends JFrame {
    private JTextArea textArea;

    private JPanel panel;

    private JScrollPane sc;

    private JScrollBar jScrollBar;

    private JFileChooser fc;

    public MainFrame() {
        initialize();
    }

    private void initialize() {
        setBounds(0, 0, 580, 515);
//        getContentPane().setLayout(new GridLayout());
        setResizable(false);
        setTitle("");
        this.fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Document", "csv");
        this.fc.setFileFilter(filter);
        this.textArea = new JTextArea();
        this.textArea.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        this.textArea.setBackground(Color.lightGray);
        this.textArea.setEditable(false);
        this.textArea.setVisible(true);
        this.textArea.setBounds(0, 0, 590, 505);
        this.sc = new JScrollPane(this.textArea);
        this.sc.setPreferredSize(new Dimension(520, 490));
        this.sc.setHorizontalScrollBarPolicy(31);
        this.sc.setVerticalScrollBarPolicy(21);
        this.sc.setVisible(true);
        add(this.sc);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void populateGrid(List<Combination> combinations) {
        this.panel = new JPanel(new GridLayout(combinations.size(), 3));
        combinations.forEach(combination -> {
            this.panel.add(new JTextArea(String.valueOf(combination.getEmployeeId1())));
            this.panel.add(new JTextArea(String.valueOf(combination.getEmployeeId2())));
            this.panel.add(new JTextArea(String.valueOf(combination.getDays())));
        });
        this.add(this.panel);
        this.setVisible(true);
    }

    public File pickFile() {
        this.fc.setCurrentDirectory(new File(""));
        this.fc.setDialogTitle("Csv file chooser");
        this.fc.setFileSelectionMode(2);
        this.fc.showOpenDialog(null);
        setVisible(true);
        return this.fc.getSelectedFile();
    }
}
