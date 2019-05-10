package note_pad_project;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class Java_notepad extends JFrame {

    JTextArea mainarea;
    JMenuBar mbar;
    JMenu mnuFile, mnuEdir, mnuFormate, mnuHelp;
    JMenuItem itnNew, itmOpen, itemSave, itmSaveas, itmExit;
    String filename;
    JFileChooser jc;
    String fileContent;

    public Java_notepad() {

        initComponent();
        itemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                save();

            }
        });
        // asve as Evect/action
        itmSaveas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                saveAs();

            }
        });

        // open
        itmOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                open();

            }
        });
        
        //new 
        
        itnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                open_new();

            }
        });
        // Exid
        
        itmExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        });
        
        

    }

    private void initComponent() {
        //Java_notepad jn = new Java_notepad();
        jc = new JFileChooser(".");
        mainarea = new JTextArea();
        getContentPane().add(mainarea);
        getContentPane().add(new JScrollPane(mainarea), BorderLayout.CENTER);
        setTitle("untital notepade");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        //Stert Work
        mbar = new JMenuBar();
        mnuFile = new JMenu("File");
        mnuEdir = new JMenu("Edit");
        mnuFormate = new JMenu("Formate");
        mnuHelp = new JMenu("Help");

        //add icon to manue item        
        ImageIcon newIcon = new ImageIcon(getClass().getResource("download (1).png"));
        ImageIcon newOpen = new ImageIcon(getClass().getResource("download (1).png"));
        ImageIcon newSave = new ImageIcon(getClass().getResource("download (1).png"));
        ImageIcon newSaveas = new ImageIcon(getClass().getResource("download (1).png"));
        ImageIcon newExit = new ImageIcon(getClass().getResource("download (1).png"));

        //manue item
        itnNew = new JMenuItem("New", newIcon);
        itmOpen = new JMenuItem("Open", newOpen);
        itemSave = new JMenuItem("Save", newSave);
        itmSaveas = new JMenuItem("Saveas", newSaveas);
        itmExit = new JMenuItem("Exit", newExit);

        //adding shotcut
        itnNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        itmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        //add manue item
        mnuFile.add(itnNew);
        mnuFile.add(itmOpen);
        mnuFile.add(itemSave);
        mnuFile.add(itmSaveas);
        mnuFile.add(itmExit);

        // add manue bar
        mbar.add(mnuFile);
        mbar.add(mnuEdir);
        mbar.add(mnuFormate);
        mbar.add(mnuHelp);
        // add manu bar to frame
        setJMenuBar(mbar);

        setVisible(true);
    }

    private void save() {
        PrintWriter fout=null ;
        //int retval = -1;

        try {
            if (filename == null) {
                saveAs();

            } else {

                fout = new PrintWriter(new FileWriter(filename));
                String s = mainarea.getText();
                StringTokenizer st = new StringTokenizer(s, System.getProperty("line.separator"));
                while (st.hasMoreElements()) {

                    fout.println(st.nextToken());

                }
                JOptionPane.showMessageDialog(rootPane, "File Save");
                fileContent = mainarea.getText();
            }

        } catch (IOException e) {

        } finally {
            if(fout != null){
                fout.close();
            
            }
            
        }

    }

    private void saveAs() {

        PrintWriter fout = null;
        int retval = -1;

        try {
            retval = jc.showSaveDialog(this);

            if (retval == JFileChooser.APPROVE_OPTION) {
                fout = new PrintWriter(new FileWriter(jc.getSelectedFile()));
            }
            String s = mainarea.getText();
            StringTokenizer st = new StringTokenizer(s, System.getProperty("line.separator"));
            while (st.hasMoreElements()) {

                fout.println(st.nextToken());

            }

            JOptionPane.showMessageDialog(rootPane, "File Save");
            fileContent = mainarea.getText();
            filename = jc.getSelectedFile().getName();
            setTitle(filename = jc.getSelectedFile().getName());

        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            fout.close();
        }

    }

    private void open() {
        try {

            int retval = jc.showOpenDialog(this);
            if (retval == JFileChooser.APPROVE_OPTION) {

                mainarea.setText(null);
                Reader in = new FileReader(jc.getSelectedFile());
                char[] buff = new char[1000000];
                int nch;
                while ((nch = in.read(buff, 0, buff.length)) != -1) {

                    mainarea.append(new String(buff, 0, nch));

                }

            }
            filename = jc.getSelectedFile().getName();
            setTitle(filename = jc.getSelectedFile().getName());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void open_new() {

        if (mainarea.getText().equals("") && !mainarea.getText().equals(fileContent)) {

            if (filename == null) {
                int option = JOptionPane.showConfirmDialog(rootPane, "do u wont save the change ?");

                if (option == 0) {
                    saveAs();
                    clear();
                } else {
                    clear();
                }

            } else {
                
             int option = JOptionPane.showConfirmDialog(rootPane, "do u wont save the change ?");
             if (option == 0) {
                    save();
                    clear();
                } else {
                   clear();
                }
                

            }

        }else{
        
            clear();
        
        }

    }

    private void clear() {

        mainarea.setText(null);
        setTitle("untitle notepad");
        filename = null;
        fileContent = null;

    }

    public static void main(String[] args) {

        Java_notepad jn = new Java_notepad();

    }

}
