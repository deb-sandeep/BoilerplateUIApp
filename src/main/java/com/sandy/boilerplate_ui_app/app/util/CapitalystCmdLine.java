package com.sandy.boilerplate_ui_app.app.util;

import com.sandy.common.util.AbstractCLParser ;

public class CapitalystCmdLine extends AbstractCLParser {
    
    @Override
    protected void prepareOptions( OptionCfgCollection options ) {
    }

    @Override
    protected String getUsageString() {
        return "Capitalyst [options]" ;
    }
}
