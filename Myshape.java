/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * author: Wenting Wang & Jingying Tang
 */
package myshape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.Applet;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Myshape extends JFrame implements ActionListener,ChangeListener, TextListener, ItemListener{
    
    String SHAPE="Rect";
    int X=200,Y=100,ftsize=35;
    String MESSAGE="SHAPE";
    String font="Regular";

    
    JPanel drawPanel,parameterPanel,shapebuttonPanel,messagePanel;
    JPanel sizePanel,colorPanel;
    
    JButton rectButton,ovalButton,roundRectButton;

    JSlider shapeSizeX, shapeSizeY,fontSize;
    
    TextField message;
    
    JCheckBox plain,bold,italic;
     
    private class RectDraw extends JPanel {
      @Override
      protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        
        
        if (SHAPE.equals("RECT")){
            g.drawRect((getSize().width-X)/2, 
                    (getSize().height-Y)/2,
                    X, Y);
        } else if (SHAPE.equals("OVAL")){
             g.drawOval((getSize().width-X)/2, (getSize().height-Y)/2,
                    X, Y);
        } else{
             g.drawRoundRect((getSize().width-X)/2, (getSize().height-Y)/2,
                    X, Y, 50, 50);
        }
        
        FontMetrics fm = g.getFontMetrics();
        int fontWidth = fm.stringWidth(MESSAGE);
        int fontHeight = fm.getHeight();      
        int fontAscent = fm.getAscent();
        
        if (font.equals("Plain")){
            drawPanel.setFont(new Font("TimesRoman",valBold,ftsize));
            g.drawString(MESSAGE,(getSize().width-fontWidth)/2,
                (getSize().height-fontHeight)/2 + fontAscent);
        } else if (font.equals("Bold")){
            drawPanel.setFont(new Font("TimesRoman",valBold,ftsize));
            g.drawString(MESSAGE,(getSize().width-fontWidth)/2,
                (getSize().height-fontHeight)/2 + fontAscent);
        } else if(font.equals("Italic")){
            drawPanel.setFont(new Font("TimesRoman",valBold,ftsize));
            g.drawString(MESSAGE,(getSize().width-fontWidth)/2,
                (getSize().height-fontHeight)/2 + fontAscent);
        } else {
            setFont(new Font("TimesRoman",valBold+valItalic,ftsize));
            g.drawString(MESSAGE,(getSize().width-fontWidth)/2,
                (getSize().height-fontHeight)/2 + fontAscent);
        }
       
      }
    }
    
    JList colorList1,colorList2;
    
    private String colorNames[] = { "Black", "Blue", "Cyan","Dark Gray", "Gray", "Green",
        "Light Gray", "Magenta","Orange", "Pink", "Red","White", "Yellow" };
    
    
    private Color colors[] ={ Color.black, Color.blue, Color.cyan,Color.darkGray,
        Color.gray, Color.green,Color.lightGray, Color.magenta, Color.orange, Color.pink, Color.red,
        Color.white, Color.yellow };
    
    private Font plainFont,boldFont,italicFont,boldItalicFont;
    
    Font df;
    
    //private class CheckBoxHandler implements ItemListener{
    private int valBold=Font.PLAIN;
    private int valItalic=Font.PLAIN;   
        
        
        
    public void itemStateChanged(ItemEvent e){
        
        /*if(e.getSource()==fontSize){
            this.ftsize = fontSize.getValue();
            drawPanel.repaint();
        }*/
                   
            if(e.getSource()==bold){
                
                if(e.getStateChange()==ItemEvent.SELECTED){
                    valBold=Font.BOLD; 
                    
                    drawPanel.setFont(new Font("TimesRoman",valBold,ftsize));
                    
                    df=new Font(MESSAGE,Font.BOLD,14);
                    
                    messagePanel.setFont(df);
                    
                }
                else{
                    valBold=Font.PLAIN;
                    
                    drawPanel.setFont(new Font("TimesRoman",valBold,ftsize));
                    
                    df=new Font(MESSAGE,Font.PLAIN,14);    
                    
                    messagePanel.setFont(df);
                }
            }
            
            else if(e.getSource()==italic){
                
                if(e.getStateChange()==ItemEvent.SELECTED){
                    valItalic=Font.ITALIC;
                    
                    drawPanel.setFont(new Font("TimesRoman",valItalic,ftsize));
                               
                    df=new Font(MESSAGE,Font.ITALIC,14);
                    
                    messagePanel.setFont(df);
                }
                else{
                    valItalic=Font.PLAIN;
                    
                    drawPanel.setFont(new Font("TimesRoman",valItalic,ftsize));
                    
                    df=new Font(MESSAGE,Font.PLAIN,14);
                    
                    messagePanel.setFont(df);
                }
            }
            
            drawPanel.setFont(new Font("TimesRoman",valBold+valItalic,ftsize));
            
            drawPanel.repaint();
            
            messagePanel.setFont(
                new Font("TimesRoman",valBold+valItalic,14));
       
            messagePanel.repaint();
        }
    
    //}
    
    public Myshape (){
        
        setTitle( "Draw my shape" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        
        // add main JPanel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        add(mainPanel);
        
        //draw shape here
        drawPanel = new RectDraw();
        drawPanel.setLayout(null);
        drawPanel.setLocation(0, 0);
        drawPanel.setSize(1200, 500);
        mainPanel.add(drawPanel);
          
        //set parameters here
        parameterPanel = new JPanel();
        parameterPanel.setLayout(null);
        parameterPanel.setLocation(0, 500);
        parameterPanel.setSize(1200, 300);
        mainPanel.add(parameterPanel);
        
        // set the shape: 3 types
        shapebuttonPanel = new JPanel();
        shapebuttonPanel.setLayout(null);
        shapebuttonPanel.setLocation(0, 0);
        shapebuttonPanel.setSize(400, 50);
        parameterPanel.add(shapebuttonPanel);

        rectButton = new JButton("Rectangle");
        rectButton.setLocation(5, 0);
        rectButton.setSize(100, 30);
        rectButton.addActionListener(this);
        shapebuttonPanel.add(rectButton);

        ovalButton = new JButton("Oval");
        ovalButton.setLocation(125, 0);
        ovalButton.setSize(100, 30);
        ovalButton.addActionListener(this);
        shapebuttonPanel.add(ovalButton);

        roundRectButton = new JButton("Round Rectangle");
        roundRectButton.setLocation(235, 0);
        roundRectButton.setSize(150, 30);
        roundRectButton.addActionListener(this);
        shapebuttonPanel.add(roundRectButton);
        
        //create panel for shape size(x and y)
        sizePanel = new JPanel();
        sizePanel.setLayout(null);
        sizePanel.setLocation(0, 50);
        sizePanel.setSize(400, 250);
        parameterPanel.add(sizePanel);
        
        shapeSizeX = new JSlider(JSlider.HORIZONTAL, 0, 1000, 200);
        shapeSizeX.setLocation(0, 0);
        shapeSizeX.setSize(400, 60);
        shapeSizeX.setMajorTickSpacing(100);
        shapeSizeX.setMinorTickSpacing(50);
        shapeSizeX.setPaintTicks (true);
        shapeSizeX.setPaintLabels(true);
        shapeSizeX.addChangeListener(this);
        sizePanel.add(shapeSizeX);
        
        
        shapeSizeY = new JSlider(JSlider.HORIZONTAL, 0, 500, 100);
        shapeSizeY.setLocation(0, 50);
        shapeSizeY.setSize(400, 60);
        shapeSizeY.setMajorTickSpacing(50);
        shapeSizeY.setMinorTickSpacing(20);
        shapeSizeY.setPaintTicks (true);
        shapeSizeY.setPaintLabels(true);
        shapeSizeY.addChangeListener(this);
        sizePanel.add(shapeSizeY);
        
        //create color panel
        colorPanel = new JPanel();
        colorPanel.setLayout(null);
        colorPanel.setLocation(450,0);
        colorPanel.setSize(200, 300);
        parameterPanel.add(colorPanel);
        
        
        colorList1=new JList(colorNames);
        colorList1.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION );
        
        JScrollPane colorListbg=new JScrollPane(colorList1);
        colorListbg.setLocation(0,5); 
        colorListbg.setSize(80,150);
        colorPanel.add( colorListbg );
      
        
        colorList1.addListSelectionListener(
            new ListSelectionListener() {
                public void valueChanged( ListSelectionEvent e ) {
                    drawPanel.setBackground(colors[ colorList1.getSelectedIndex() ] );
                }
            }
        );
        
        colorList2=new JList(colorNames);
        colorList2.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION );
        
        JScrollPane colorListf=new JScrollPane(colorList2);
        colorListf.setLocation(120,5); 
        colorListf.setSize(80,150);
        colorPanel.add( colorListf );
        
        colorList2.addListSelectionListener(
            new ListSelectionListener() {
                public void valueChanged( ListSelectionEvent e ) {
                    drawPanel.setForeground(colors[ colorList2.getSelectedIndex() ] );
                }
            }
        );
        
        // message control
        messagePanel = new JPanel();
        messagePanel.setLayout(null);
        messagePanel.setLocation(700, 0);
        messagePanel.setSize(500, 300);
        parameterPanel.add(messagePanel);
        
        message = new TextField("SHAPE");
        message.setLocation(0, 10);
        message.setSize(150, 30);
        message.addTextListener(this);
        messagePanel.add(message);
        
        
        plain=new JCheckBox("Plain"); 
        plain.setLocation(160,0);
        plain.setSize(70,40);
        messagePanel.add(plain);
        
        bold=new JCheckBox("Bold");
        bold.setLocation(240,0);
        bold.setSize(60,40);
        messagePanel.add(bold);
        
        
        italic=new JCheckBox("Italic"); 
        italic.setLocation(310,0);
        italic.setSize(70,40);
        messagePanel.add(italic);
        
        //CheckBoxHandler handler=new CheckBoxHandler();
        plain.addItemListener(this);
        bold.addItemListener(this);
        italic.addItemListener(this);
                
        fontSize = new JSlider(JSlider.HORIZONTAL, 0, 50, 20);
        fontSize.setLocation(0, 50);
        fontSize.setSize(250, 60);
        fontSize.setMajorTickSpacing(10);
        fontSize.setMinorTickSpacing(2);
        fontSize.setPaintTicks (true);
        fontSize.setPaintLabels(true);
        fontSize.addChangeListener(this);
        messagePanel.add(fontSize);
     
    }
    
    
    
     public void actionPerformed(ActionEvent e) {
        if(e.getSource() == rectButton) {
            System.out.println(this.SHAPE);  
            this.SHAPE = "RECT";
            drawPanel.repaint();  
        } else if (e.getSource() == ovalButton) { 
            System.out.println(this.SHAPE);  
            this.SHAPE = "OVAL";
            drawPanel.repaint();
        } else if(e.getSource() == roundRectButton) {
            System.out.println(this.SHAPE);  
            this.SHAPE = "ROUNDRECT";
            drawPanel.repaint();
        }
        /*else if(e.getSource() == plain){
            this.font = plain.getText();
            drawPanel.repaint(); 
        }else if(e.getSource() == bold){
           this.font = bold.getText();
            drawPanel.repaint(); 
        }else if(e.getSource() == italic){
            this.font = italic.getText();
            drawPanel.repaint(); 
 
        }*/
     }
     
     public void textValueChanged(TextEvent e){
         if(e.getSource() == message){
            this.MESSAGE = message.getText();         
            drawPanel.repaint();    
        }
     }
     
     public void stateChanged( ChangeEvent e ){
        if(e.getSource() == shapeSizeX){
            this.X = shapeSizeX.getValue();
            drawPanel.repaint();
        } 
        else if(e.getSource() == shapeSizeY){
            this.Y = shapeSizeY.getValue();
            drawPanel.repaint();
        } 
        else if(e.getSource() == fontSize){
            this.ftsize = fontSize.getValue();
            drawPanel.repaint();
        }
     
     }
     
     public static void main(String[] args) {
        Myshape app = new Myshape();
        app.setSize(1200, 800);
        app.setVisible(true);
    }
}
        