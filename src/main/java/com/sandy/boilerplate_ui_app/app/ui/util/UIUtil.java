package com.sandy.boilerplate_ui_app.app.ui.util ;

import static com.sandy.boilerplate_ui_app.app.util.ObjectRepository.getApp ;
import static com.sandy.boilerplate_ui_app.app.util.ObjectRepository.getMainFrame ;

import java.awt.Dimension ;
import java.awt.Insets ;
import java.awt.event.ActionListener ;

import javax.swing.ImageIcon ;
import javax.swing.JButton ;

import com.sandy.boilerplate_ui_app.app.ui.actions.AbstractBaseAction ;
import com.sandy.common.ui.SwingUtils ;

public class UIUtil {

    public static ImageIcon getIcon( String iconName ) {
        return SwingUtils.getIcon( getApp().getClass(), iconName ) ;
    }
    
    public static JButton getActionBtn( AbstractBaseAction action ) {
        
        JButton button = new JButton() ;
        
        button.setIcon( action.getSmallIcon() ) ;
        button.setMargin( new Insets( 0, 0, 0, 0 ) ) ;
        button.setBorderPainted( false ) ;
        button.setFocusPainted( true ) ;
        button.setIconTextGap( 0 ) ;
        button.setPreferredSize( new Dimension( 30, 30 ) );
        button.setAction( action ) ;
        button.setText( null ) ;
        
        return button ;
    }
    
    public static JButton getActionBtn( String iconName, String actionCmd, 
                                        ActionListener listener ) {
        
        JButton button = new JButton() ;
        
        button.setIcon( getIcon( iconName ) ) ;
        button.setMargin( new Insets( 0, 0, 0, 0 ) ) ;
        button.setBorderPainted( false ) ;
        button.setFocusPainted( true ) ;
        button.setIconTextGap( 0 ) ;
        button.setPreferredSize( new Dimension( 30, 30 ) );
        button.setActionCommand( actionCmd ) ;
        button.addActionListener( listener ) ;
        
        return button ;
    }
    
    public static void logStatus( String msg ) {
        getMainFrame().logStatus( msg ) ;
    }
}
