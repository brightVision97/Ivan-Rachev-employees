import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.List;

public class MainFrame extends JFrame {

    private JPanel panel;

    public MainFrame() {
        initialize();
    }

    private void initialize() {
        setBounds(0, 0, 580, 515);
        setResizable(false);
        setTitle("");
        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        textArea.setBackground(Color.lightGray);
        textArea.setEditable(false);
        textArea.setVisible(true);
        textArea.setBounds(0, 0, 590, 505);
        JScrollPane sc = new JScrollPane(textArea);
        sc.setPreferredSize(new Dimension(520, 490));
        sc.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sc.setVisible(true);
        add(sc);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void populateGrid(List<Combination> combinations) {
        this.panel = new JPanel(new GridLayout(combinations.size(), 3));
        getContentPane().setLayout(new GridLayout(combinations.size(), 3));
        combinations.forEach(combination -> {
            this.panel.add(new JTextArea(String.valueOf(combination.getEmployeeId1())));
            this.panel.add(new JTextArea(String.valueOf(combination.getEmployeeId2())));
            this.panel.add(new JTextArea(String.valueOf(combination.getDays())));
        });
        this.add(this.panel);
        this.setVisible(true);
    }

    public File pickFile() {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Document", "csv");
        fc.setFileFilter(filter);
        fc.setCurrentDirectory(new File(""));
        fc.setDialogTitle("Csv file chooser");
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.showOpenDialog(null);
        setVisible(true);
        return fc.getSelectedFile();
    }
}
