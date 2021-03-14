
/**
	Assignment 1 - Created using Java GUI (Swing)
	Notepad UI
	@author Artem Fetissov
	@version 1.1
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
	static DialogClose cd, cd2;
	

	static class MenuActionListener implements ActionListener {
		public void actionPerformed (ActionEvent actionEvent) {
			String s = actionEvent.getActionCommand();
			System.out.println ("Selected: " + actionEvent.getActionCommand());
			
			switch (s) {
				//										//
				// Action commands for the File menu:	//
				//										//
				case "New":
					System.out.println("Create new file");
					
					// Set the text area to be blank 
                    editorAreaText.setText("");
                    break;
				case "Open":
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
		   
		                     // Initialize sl 
		                     sl = br.readLine(); 
		   
		                     // Take the input from the file 
		                     while ((s1 = br.readLine()) != null) { 
		                         sl = sl + "\n" + s1; 
		                     } 
		   
		                     // Set the text 
		                     editorAreaText.setText(sl); 
		                 } 
		                 catch (Exception evt) { 
		                     System.out.println(evt.getMessage()); 
		                 } 
		            } 
		            // if the user cancelled the operation 
		            else {
		            	System.out.println("The user cancelled operation");
		            }
		            break;
				case "Save":
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
		            else {
		            	System.out.println("The user cancelled operation");
		            }
		            
				case "Edit":
					break;
				case "Exit":
					cd = new DialogClose();
					cd.display();				
					System.out.println("Exiting...");

			        
			    //										//
			    // Action commands for the Format menu:	//
			    //										//

		}
		}
	}
	


	public static void main(String args[]) { // Construct the frame
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            Notepad frame = new Notepad();
            JMenu fileMenu, formatMenu, sizeSubmenu;
            
            String imgURL = "./note.png";
			//Set the frame icon to an image loaded from a file.
            frame.setIconImage(new ImageIcon(imgURL).getImage());
            
            // new DialogClose().display();
            
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

			JMenuItem editMenuItem = new JMenuItem("Edit", KeyEvent.VK_E);
			editMenuItem.addActionListener(menuListener);
			fileMenu.add(editMenuItem);
			
			fileMenu.addSeparator();
			
			JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
			exitMenuItem.addActionListener(menuListener);
			fileMenu.add(exitMenuItem);

			// TODO: create menus
			
			// Build the file menu.
			formatMenu = new JMenu("Format");
			formatMenu.setMnemonic(KeyEvent.VK_F);
			menuBar.add(fileMenu);
			
			JCheckBoxMenuItem boldMenuItem = new JCheckBoxMenuItem("Bold");
			boldMenuItem.setMnemonic(KeyEvent.VK_B);
			formatMenu.add(boldMenuItem);
			
			boldMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

	                AbstractButton aButton = (AbstractButton) event.getSource();
	                boolean selected = aButton.getModel().isSelected();

	                if (selected) {
	              	  System.out.println("Bold activated");
	              	// set bold font 
			            Font f = new Font("Serif", Font.BOLD, 12); 
			            editorAreaText.setFont(f);
	                } else {
	                	// set plain font 
	                    Font f = new Font("Serif", Font.PLAIN, 12); 
	                    editorAreaText.setFont(f); 
	              	  	System.out.println("Bold deactivated");
	                }
				
				}
			});
			
			JCheckBoxMenuItem italicMenuItem = new JCheckBoxMenuItem("Italic");
			italicMenuItem.setMnemonic(KeyEvent.VK_I);
			formatMenu.add(italicMenuItem);
			
			italicMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

	                AbstractButton aButton = (AbstractButton) event.getSource();
	                boolean selected = aButton.getModel().isSelected();

	                if (selected) {
	              	  System.out.println("Italic activated");
	              	// set bold font 
			            Font f = new Font("Serif", Font.ITALIC, 12); 
			            editorAreaText.setFont(f);
	                } else {
	                	// set plain font 
	                    Font f = new Font("Serif", Font.PLAIN, 12); 
	                    editorAreaText.setFont(f); 
	              	  	System.out.println("Italic deactivated");
	                }
				
				}
			});
            
			sizeSubmenu = new JMenu("Size");
			sizeSubmenu.setMnemonic(KeyEvent.VK_Z);
			
			// Create a group for radio buttons
			ButtonGroup group = new ButtonGroup();
			
			JRadioButtonMenuItem rbMenuItem1 = new JRadioButtonMenuItem("Small");
			rbMenuItem1.setMnemonic(KeyEvent.VK_R);
			group.add(rbMenuItem1);
			sizeSubmenu.add(rbMenuItem1);
			
			JRadioButtonMenuItem rbMenuItem2 = new JRadioButtonMenuItem("Medium");
			rbMenuItem2.setSelected(true);
			rbMenuItem2.setMnemonic(KeyEvent.VK_R);
			group.add(rbMenuItem2);
			sizeSubmenu.add(rbMenuItem2);
			
			JRadioButtonMenuItem rbMenuItem3 = new JRadioButtonMenuItem("Large");
			rbMenuItem3.setMnemonic(KeyEvent.VK_R);
			group.add(rbMenuItem3);
			sizeSubmenu.add(rbMenuItem3);
			
			rbMenuItem1.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	Font f = new Font("Serif", Font.PLAIN, 10); 
		            editorAreaText.setFont(f);

		        }
		    });
			
			rbMenuItem2.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	Font f = new Font("Serif", Font.PLAIN, 12); 
		            editorAreaText.setFont(f);

		        }
		    });
			
			rbMenuItem3.addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	Font f = new Font("Serif", Font.PLAIN, 14); 
		            editorAreaText.setFont(f);

		        }
		    });
			
			formatMenu.add(sizeSubmenu);
			menuBar.add(formatMenu);
			
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
		}

		@Override
			public void actionPerformed(ActionEvent e) {
					
		}
		
		@Override
		public void windowClosing(WindowEvent we) {
			cd2 = new DialogClose();
			cd2.display();				
			System.out.println("Exiting...");
			System.exit(0);
			
		}

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			cd2 = new DialogClose();
			cd2.display();				
			System.out.println("Exiting...");
			System.exit(0);
			
			
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


