package com.sandy.boilerplate_ui_app;

import static com.sandy.boilerplate_ui_app.app.util.ObjectRepository.getWkspManager ;
import static com.sandy.boilerplate_ui_app.app.util.ObjectRepository.setApp ;
import static com.sandy.boilerplate_ui_app.app.util.ObjectRepository.setAppConfig ;
import static com.sandy.boilerplate_ui_app.app.util.ObjectRepository.setBus ;
import static com.sandy.boilerplate_ui_app.app.util.ObjectRepository.setMainFrame ;
import static com.sandy.boilerplate_ui_app.app.util.ObjectRepository.setObjectFactory ;
import static com.sandy.boilerplate_ui_app.app.util.ObjectRepository.setStateMgr ;
import static com.sandy.boilerplate_ui_app.app.util.ObjectRepository.setUiActions ;
import static com.sandy.boilerplate_ui_app.app.util.ObjectRepository.setWkspManager ;

import java.awt.SplashScreen ;

import javax.swing.SwingUtilities ;

import org.apache.log4j.Logger ;

import com.sandy.boilerplate_ui_app.app.ui.MainFrame ;
import com.sandy.boilerplate_ui_app.app.ui.actions.Actions ;
import com.sandy.boilerplate_ui_app.app.util.AppConfig ;
import com.sandy.boilerplate_ui_app.app.util.CapitalystCmdLine ;
import com.sandy.boilerplate_ui_app.app.util.ConfiguratorBuilder ;
import com.sandy.common.bus.EventBus ;
import com.sandy.common.objfactory.SpringObjectFactory ;
import com.sandy.common.util.Configurator ;
import com.sandy.common.util.StateManager ;
import com.sandy.common.util.WorkspaceManager ;

public class BoilerplateUIApp {

    private static final Logger logger = Logger.getLogger( BoilerplateUIApp.class ) ;
    
    // TODO: Change the APP_ID value to the application id
    public static final String APP_ID = "boilerplate" ;
    
    public void launch( String[] args ) throws Exception {
        
        preInitialize( args ) ;
        SwingUtilities.invokeLater( new Runnable() {
            @Override
            public void run() {
                try {
                    setUpAndShowMainFrame() ;
                    postInitialize() ;
                }
                catch( Exception e ) {
                    e.printStackTrace();
                }
            }
        } );
    }
    
    private void preInitialize( String[] args ) throws Exception {
        
        // Process command line
        CapitalystCmdLine cmdLine = new CapitalystCmdLine() ;
        cmdLine.parse( args ) ;
        
        // Initialize the object factory
        SpringObjectFactory objFactory = new SpringObjectFactory() ;
        objFactory.addResourcePath( "classpath:com/sandy/" + APP_ID + "/objfactory.xml" ) ;
        objFactory.initialize() ;
        
        setObjectFactory( objFactory ) ;
        setBus( new EventBus() ) ;
        setWkspManager( new WorkspaceManager( APP_ID ) ) ;
        setAppConfig( new AppConfig() ) ;
        setUiActions( new Actions() ) ;
        
        // Configure the system components
        ConfiguratorBuilder builder = new ConfiguratorBuilder( APP_ID, cmdLine ) ;
        Configurator configurator = builder.createConfigurator() ;
        configurator.initialize() ;
    }
    
    private void postInitialize() throws Exception {
        initializeStateManager() ;
    }
    
    private void initializeStateManager() throws Exception {
        
        StateManager stateManager = new StateManager( this, getWkspManager() ) ;
        setStateMgr( stateManager ) ;
        
        //stateManager.registerObject( "ProjectManager", getProjectManager() ) ;
        stateManager.initialize() ;
        stateManager.loadState() ;
        
    }
    
    private void setUpAndShowMainFrame() throws Exception {
        
        MainFrame mainFrame = new MainFrame() ;
        mainFrame.setUp() ;
        setMainFrame( mainFrame ) ;
        mainFrame.setVisible( true ) ;
    }
    
    public static void main( String[] args ) {
        
        logger.info( "Starting Capitalyst application" ) ;
        BoilerplateUIApp app = new BoilerplateUIApp() ;
        try {
            showSplashScreen() ;
            setApp( app ) ;
            app.launch( args ) ;
        }
        catch( Exception e ) {
            logger.error( "Capitalyst exitted with an exception", e ) ;
        }
    }
    
    private static void showSplashScreen() throws Exception {
        final SplashScreen splash = SplashScreen.getSplashScreen() ;
        if( splash != null ) {
            Thread.sleep( 2000 ) ;
            splash.close() ;
        }
    }
}
