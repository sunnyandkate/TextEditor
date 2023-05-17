package textEditor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextEditor extends JFrame{

	private JTextArea textArea;
	private File file;
	
	class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("new")) {
				newFile();
			}
			if(e.getActionCommand().equals("open")) {
				openFile();
			}
			if(e.getActionCommand().equals("save")) {
				saveFile();
			}
			if(e.getActionCommand().equals("exit")) {
				exit();
			}
			if(e.getActionCommand().equals("blue")) {
				textArea.setForeground(Color.blue);
			}
			if(e.getActionCommand().equals("green")) {
				textArea.setForeground(Color.green);
			}
			if(e.getActionCommand().equals("black")) {
				textArea.setForeground(Color.black);
			}
			if(e.getActionCommand().equals("bgBlue")) {
				textArea.setBackground(Color.blue);
			}
			if(e.getActionCommand().equals("bgGreen")) {
				textArea.setBackground(Color.green);
			}
			if(e.getActionCommand().equals("bgBlack")) {
				textArea.setBackground(Color.black);
			}
			
		}
		
	}
	
	public TextEditor(String title) {
		super(title);
		setLayout(new BorderLayout());	
		initMenu();
		add(toolBar(), BorderLayout.NORTH);
		setSize(500, 400);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void initMenu() {
		
		textArea = new JTextArea();
				
		JMenuBar menu = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		
		Listener listener = new Listener();
		
		JMenuItem newFile = new JMenuItem();
		newFile.setText("New");
		newFile.setActionCommand("new");
		newFile.addActionListener(listener);
		fileMenu.add(newFile);
		
		JMenuItem openFile = new JMenuItem();
		openFile.setText("Open");
		openFile.setActionCommand("open");
		openFile.addActionListener(listener);
		fileMenu.add(openFile);
		
		JMenuItem saveFile = new JMenuItem();
		saveFile.setText("Save");
		saveFile.setActionCommand("save");
		saveFile.addActionListener(listener);
		fileMenu.add(saveFile);
		
		fileMenu.addSeparator();
		
		JMenuItem exit = new JMenuItem();
		exit.setText("Exit");
		exit.setActionCommand("exit");
		exit.addActionListener(listener);
		fileMenu.add(exit);
		
		JMenu colorChange = new JMenu("Color");
		
		JMenuItem blue = new JMenuItem();
		blue.setText("Blue");
		blue.setActionCommand("blue");
		blue.addActionListener(listener);
		colorChange.add(blue);
		
		JMenuItem green = new JMenuItem();
		green.setText("Green");
		green.setActionCommand("green");
		green.addActionListener(listener);
		colorChange.add(green);
		
		JMenuItem black = new JMenuItem();
		black.setText("Black");
		black.setActionCommand("black");
		black.addActionListener(listener);
		colorChange.add(black);
		
		JMenu background = new JMenu("BG-Color");
		
		JMenuItem bgBlue = new JMenuItem();
		bgBlue.setText("Blue");
		bgBlue.setActionCommand("bgBlue");
		bgBlue.addActionListener(listener);
		background.add(bgBlue);
		
		JMenuItem bgGreen = new JMenuItem();
		bgGreen.setText("Green");
		bgGreen.setActionCommand("bgGreen");
		bgGreen.addActionListener(listener);
		background.add(bgGreen);
		
		JMenuItem bgBlack = new JMenuItem();
		bgBlack.setText("Black");
		bgBlack.setActionCommand("bgBlack");
		bgBlack.addActionListener(listener);
		background.add(bgBlack);
		
		add(textArea, BorderLayout.CENTER);
		menu.add(fileMenu);
		menu.add(colorChange);
		menu.add(background);
		this.setJMenuBar(menu);
				
	}
	private JToolBar toolBar() {
		
		JToolBar toolBar = new JToolBar();
		
		Listener listener = new Listener();
		
		JButton newFileBtn = new JButton("new");
		newFileBtn.setActionCommand("new");
		newFileBtn.addActionListener(listener);
		
		JButton openFileBtn = new JButton("open");
		openFileBtn.setActionCommand("open");
		openFileBtn.addActionListener(listener);
		
		JButton saveFileBtn = new JButton("save");
		saveFileBtn.setActionCommand("save");
		saveFileBtn.addActionListener(listener);
		
		toolBar.add(newFileBtn);
		toolBar.add(openFileBtn);
		toolBar.add(saveFileBtn);
		
		return toolBar;
	}
	private void newFile() {
		textArea.setText("");
	}
	private void openFile() {
		
		JFileChooser chooseFile = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(null,".txt");
	    chooseFile.setFileFilter(filter);
	    int returnVal = chooseFile.showOpenDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	      
	         file = chooseFile.getSelectedFile();
		    }
		
		
		
		if(file != null) {
			try {
				textArea.read(new FileReader(file), null);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Could not open the file", "error", JOptionPane.ERROR_MESSAGE);
				
			}
		}
	}
	private void saveFile() {

		JFileChooser chooseFile = new JFileChooser();
	    FileNameExtensionFilter filter = new FileNameExtensionFilter(null,".txt");
	    chooseFile.setFileFilter(filter);
	    int returnVal = chooseFile.showSaveDialog(null);
	    if(returnVal == JFileChooser.APPROVE_OPTION) {
	      
	         file = chooseFile.getSelectedFile();
		    }
		if(file != null) {
			try {
				textArea.write(new FileWriter(file));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, "Could not write the file", "error", JOptionPane.ERROR_MESSAGE);
				
			}
		}
	}
	private void exit() {
		System.exit(0);
	}
}
