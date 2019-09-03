/*OOPM Project to implement a Text Editor
Names: Nikhil Joshi, Srivatsan Iyengar, Sahil Rajpal
Roll no. 25,24,42
Class: D7A
*/
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*; 
import javax.swing.text.*;
import javax.swing.*;

class OOPM_Project_1 extends JFrame implements ActionListener,AdjustmentListener
{
	//Text Area
	JTextArea t;
	
        //Frame
	JFrame f;
        
        //creation for scroll bar
        JScrollPane scroll ;
        
        
        OOPM_Project_1()
	{
		t=new JTextArea();
		f=new JFrame("OOPM Project Text Editor");
		try
		{
			//Set look and feel 
                        
			//Metal Theme
                       // UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                        
                        //Motif theme
                        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"); 
                        
                        //Changed default theme to SystemLookAndFeel ..differs from OS to OS
                        //Window will appear like default text editor in Windows/Linux/Mac OS.. 
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        			
                        //Set Ocean theme (default for cross platform metal theme...)
			//MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
		}
		catch(Exception e)
		{}
		
		//Creating menu bar
		JMenuBar mb=new JMenuBar();
		
		//Creating file menu
		JMenu m1=new JMenu("File");
		
		//Adding menu items
		JMenuItem m1a=new JMenuItem("New");
		JMenuItem m1b=new JMenuItem("Open");
		JMenuItem m1c=new JMenuItem("Save");
		JMenuItem m1d=new JMenuItem("Print");
		
		//Add Action Listener to each menu items
		m1a.addActionListener(this);
		m1b.addActionListener(this);
		m1c.addActionListener(this);
		m1d.addActionListener(this);
		m1.add(m1a);
		m1.add(m1b);
		m1.add(m1c);
		m1.add(m1d);
                
		//Creating edit menu
		JMenu m2=new JMenu("Edit");
		
		//Adding menu items
		JMenuItem m2a=new JMenuItem("Cut");
		JMenuItem m2b=new JMenuItem("Copy");
		JMenuItem m2c=new JMenuItem("Paste");
		
                //Add Action Listener to each menu items
		m2a.addActionListener(this);
		m2b.addActionListener(this);
		m2c.addActionListener(this);
		m2.add(m2a);
		m2.add(m2b);
		m2.add(m2c);
                
                //Fonts
                JMenu m3 = new JMenu("Fonts");
                
                //Adding Fonts
		JMenuItem m3a=new JMenuItem("Agency FB");
		JMenuItem m3b=new JMenuItem("SansSerif");
		JMenuItem m3c=new JMenuItem("Arial");
		
                //Add Action Listener to each menu items
		m3a.addActionListener(this);
		m3b.addActionListener(this);
		m3c.addActionListener(this);
		m3.add(m3a);
		m3.add(m3b);
		m3.add(m3c);
                
                //About
                JMenu m4=new JMenu("Help");
                
                //Adding about
                JMenuItem m4a=new JMenuItem("About");
                m4a.addActionListener(this);
                m4.add(m4a);
                
		JMenuItem mc=new JMenuItem("Close");
		//Adding Action Listener
		mc.addActionListener(this);
		//Adding menu items to menu bar
		mb.add(m1);
		mb.add(m2);
                mb.add(m3);
                mb.add(m4);
		mb.add(mc);
		//Setting menubar on the frame
		f.setJMenuBar(mb);
		
		//Adding text area
		f.add(t);
		
		//Setting visibility
		f.setVisible(true);
                
                //Adding scroll bar     
                 scroll = new JScrollPane(t);
                 scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                 scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
                 f.add(scroll);
                 
                 //Setting size of scroll bar
                 scroll.setSize(20,500);
                 
                 //Setting size of frame
                 f.setSize(500,500);
                 
                 //Setting size of Text
                 t.setSize(400,500);
		
                 
            }
    
	public void adjustmentValueChanged(AdjustmentEvent e){}
	//If a button is pressed
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		//Getting to know which button is pressed
		String s=ae.getActionCommand();
		//If copy is pressed
		if(s.equals("Copy"))
		{
			t.copy();
		}
		
		//If cut is pressed
		else if(s.equals("Cut"))
		{
			t.cut();
		}
		
		//If paste is pressed
		else if(s.equals("Paste"))
		{
			try
			{
				t.paste();
			}
			catch (Exception evt)
			{ 
                JOptionPane.showMessageDialog(f, evt.getMessage()); 
            } 
		}
		
		//If print is pressed
		else if(s.equals("Print"))
		{
			try{t.print();}//was throwing Excetion so put it in try catch block
			catch(Exception e){}
		}
		
		//If open is pressed
		else if(s.equals("Open"))
		{
			// Create an object of JFileChooser class 
            JFileChooser fileopener = new JFileChooser("f:"); 
			//open dialog to make user connect with the drives
            int r = fileopener.showOpenDialog(null); 
            if (r == JFileChooser.APPROVE_OPTION){ //to get the option constant from filechooser
                File fileo = new File(fileopener.getSelectedFile().getAbsolutePath()); 
                try{ 
                    //string 
                    String s1 = "",s2="";
					//creating the File reader object
                    FileReader fr = new FileReader(fileo); 
                    BufferedReader br = new BufferedReader(fr); 
                    s2=br.readLine(); 
                    //it reads line by line from the file and appends  the s2 string one line by line
                    while ((s1 = br.readLine()) != null){ 
                        s2 = s2 + "\n" + s1; 
                    } 
  
                    // Set the text 
                    t.setText(s2); 
                } 
                catch (Exception evt){ 
                    JOptionPane.showMessageDialog(f, evt.getMessage()); 
                }
			}
		}
		
		//If save is pressed
		else if(s.equals("Save"))
		{
			//Add Content to Save a File!!!!!!!!!!!
			// Create an object of JFileChooser class 
            JFileChooser filesaver = new JFileChooser("f:"); 
            //opened dialog box to select directory by the user
            int r = filesaver.showSaveDialog(null); //getting thee option value in methods
            if (r == JFileChooser.APPROVE_OPTION) { 
                // Set the label to the path of the selected directory 
                File files = new File(filesaver.getSelectedFile().getAbsolutePath()); 
  
                try { 
                    // Create a file writer 
                    FileWriter wr=new FileWriter(files, false); 
                    // Create buffered writer to write 
                    BufferedWriter w=new BufferedWriter(wr);  
                    w.write(t.getText()); 
                    w.flush(); //to clear buffer in stdin
                    w.close(); //closing the buffered writerr class
                }
                catch (Exception evt) { 
                    JOptionPane.showMessageDialog(f,evt.getMessage()); 
                } 
            }
        } 
        else if (s.equals("Print")) { 
            try { 
                // print the file 
                t.print(); 
            } 
            catch (Exception evt) { 
                JOptionPane.showMessageDialog(f,evt.getMessage()); 
            } 
		}
		
		//If new is pressed
		else if(s.equals("New"))
		{
			t.setText("");
		}
		else if(s.equals("Close"))
		{
			f.setVisible(false);
			System.exit(0);
		}
                
                else if(s.equals("Agency FB"))
                {
                    Font fo = new Font("Agency FB",Font.PLAIN,16);
                    t.setFont(fo);
                }
                 else if(s.equals("Arial"))
                {
                    Font fo = new Font("Arial",Font.PLAIN,16);
                    t.setFont(fo);
                }
                 else if(s.equals("SansSerif"))
                {
                    Font fo = new Font("SansSerif",Font.PLAIN,16);
                    t.setFont(fo);
                }
                 else if(s.equals("About"))
                 {
                      String newLine = System.getProperty("line.separator");//This will retrieve line separator dependent on OS.
                      
                      //The following message would be displayed when "About" button is clicked
                     JOptionPane.showMessageDialog(f, "Srivatsan : Worked on database using JFileChooser plus minimal changes in Nikhil's code " + newLine + " Nikhil : Added File,Edit,Close button,Frame,Panel " + newLine + " Sahil : Added themes, fonts,scroll bar and the box in which you're reading this message...Thank You !");
                    
                 }
                
	}
        
	//Main Method
	public static void main(String args[])
	{
		OOPM_Project_1 o=new OOPM_Project_1();
	}
}