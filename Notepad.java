
/**
	Assignment 1 - Created using Java GUI (Swing)
	Notepad UI
	@author Artem Fetissov
	@version 1.0
 */

import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.*;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;


public class Notepad extends JFrame implements ActionListener, WindowListener {
	private static final long serialVersionUID = 1L;
	
	// Declare class variables
	JPanel pane;
	GridBagConstraints c;
	int columns;
	
	static JTextArea editorAreaText;
	JScrollPane editorScrollPane;
	
	// Create the menu structure

	JRadioButtonMenuItem rbMenuItem;
	JCheckBoxMenuItem cbMenuItem;
	
	static class MenuActionListener implements ActionListener {
		public void actionPerformed (ActionEvent actionEvent) {
			String s = actionEvent.getActionCommand();
			System.out.println ("Selected: " + actionEvent.getActionCommand());
			
			switch (s) {
				case "New":
					System.out.println("Create new file");
					//TODO: write code to create a new file
				case "Open":
					//TODO: write code to open a file
					// create an object of JFileChooser class 
		            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
		            
		            // invoke the showsOpenDialog function to show the save dialog 
		            int readDialog = j.showOpenDialog(null); 
		  
		            // if the user selects a file 
		            if (readDialog == JFileChooser.APPROVE_OPTION) { 
		                // set the label to the path of the selected file 
		                //l.setText(j.getSelectedFile().getAbsolutePath());
		            	File fi = new File(j.getSelectedFile().getAbsolutePath());
		            	
		            	 try { 
		                     // String 
		                     String s1 = "", sl = ""; 
		   
		                     // File reader 
		                     FileReader fr = new FileReader(fi); 
		   
		                     // Buffered reader 
		                     BufferedReader br = new BufferedReader(fr); 
		   
		                     // Initilize sl 
		                     sl = br.readLine(); 
		   
		                     // Take the input from the file 
		                     while ((s1 = br.readLine()) != null) { 
		                         sl = sl + "\n" + s1; 
		                     } 
		   
		                     // Set the text 
		                     editorAreaText.setText(sl); 
		                 } 
		                 catch (Exception evt) { 
		                     System.out.println( evt.getMessage()); 
		                 } 
		            } 
		            // if the user cancelled the operation 
		            else break;
		                //l.setText("the user cancelled the operation"); 
				case "Save":
					//TODO: write code to save file

		            // Create an object of JFileChooser class 
		            JFileChooser k = new JFileChooser("f:"); 
		  
		            // Invoke the showsSaveDialog function to show the save dialog 
		            int writeDialog = k.showSaveDialog(null); 
		  
		            if (writeDialog == JFileChooser.APPROVE_OPTION) { 
		  
		                // Set the label to the path of the selected directory 
		                File fi = new File(k.getSelectedFile().getAbsolutePath()); 
		  
		                try { 
		                    // Create a file writer 
		                    FileWriter wr = new FileWriter(fi, false); 
		  
		                    // Create buffered writer to write 
		                    BufferedWriter w = new BufferedWriter(wr); 
		  
		                    // Write 
		                    w.write(editorAreaText.getText()); 
		  
		                    w.flush(); 
		                    w.close(); 
		                } 
		                catch (Exception evt) { 
		                	System.out.println( evt.getMessage()); 
		                } 
		            } 
		            // If the user cancelled the operation 
		            else
		            	System.out.println("The user cancelled operation");
				
		}
		}
	}

	public static void main(String args[]) { // Construct the frame
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            Notepad frame = new Notepad();
            JMenu fileMenu, formatMenu, sizeSubmenu;
            
            ActionListener menuListener = new MenuActionListener();
            JMenuBar menuBar = new JMenuBar();
             
			
			// Build the file menu.
			fileMenu = new JMenu("File");
			fileMenu.setMnemonic(KeyEvent.VK_F);
			menuBar.add(fileMenu);
			
			//a group of JMenuItems
			JMenuItem menuItem = new JMenuItem("New", KeyEvent.VK_N);
			menuItem.addActionListener(menuListener);
			menuItem.setAccelerator(KeyStroke.getKeyStroke(
			        KeyEvent.VK_1, ActionEvent.ALT_MASK));		// TODO: set the correct accelerators for menu items
			fileMenu.add(menuItem);
			
			JMenuItem openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
			openMenuItem.addActionListener(menuListener);
			fileMenu.add(openMenuItem);
			
			JMenuItem saveMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
			saveMenuItem.addActionListener(menuListener);
			fileMenu.add(saveMenuItem);

			fileMenu.addSeparator();

			JMenu editMenu = new JMenu("Edit");
			editMenu.setMnemonic(KeyEvent.VK_E);
			menuBar.add(editMenu);
			
			JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
			exitMenuItem.addActionListener(menuListener);
			fileMenu.add(exitMenuItem);

			// TODO: create menus
			
			// Build the file menu.
			fileMenu = new JMenu("Format");
			fileMenu.setMnemonic(KeyEvent.VK_F);
			menuBar.add(fileMenu);
            
            frame.setJMenuBar(menuBar);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(true);
            frame.setLocationRelativeTo(null);
            frame.pack();
            // make the GUI visible
            frame.setVisible(true);	
            }
         });

		
		} 
	
		public Notepad() { // Frame constructor
			setTitle("Notepad Editor");
			
			
			pane = new JPanel(new GridBagLayout());
			c = new GridBagConstraints();

			
			// Add scroll pane
			c.gridx = 0;
			c.gridy = 0;
			
			editorAreaText = new JTextArea(40, 60);
			editorScrollPane = new JScrollPane(editorAreaText);
			pane.add(editorScrollPane, c);
			
			
			
			this.setContentPane(pane);
			
			/*
			startButton.addActionListener(new ActionListener() {
		         public void actionPerformed(ActionEvent e) {
		        	 start_label.setText("Start.");
		        	 startTextField.setText("Start");
		        	 stopButton.setEnabled(true);
		        	 
		        	 // TODO: add code to start timer
		        	 if (timer != null && timer.isRunning()) {
		                 return;
		              }
		        	 elapsedTextField.setText("");
		             timer = new Timer(1000, new TimerListener());
		             timer.start();

		         }          
		      });
				*/
			
			
		}
			

		

		// Method for creating buttons
		private JButton createButton(String text, String ac, ActionListener handler) {
			JButton b = new JButton(text);
			b.setActionCommand(ac);
			b.addActionListener(handler);
			b.setVerticalTextPosition(AbstractButton.CENTER);
		    b.setHorizontalTextPosition(AbstractButton.CENTER);
		    b.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
			
			return b;
		}
		
		@Override
			public void actionPerformed(ActionEvent e) {
					
		}
		
		@Override
		public void windowClosing(WindowEvent we) {
			dispose();
	        System.exit(0);
		}

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}


