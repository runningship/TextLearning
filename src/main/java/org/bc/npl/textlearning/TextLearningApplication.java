package org.bc.npl.textlearning;

import org.bc.npl.textlearning.service.ScrawService;
import org.bc.npl.textlearning.service.SimpleDBRealm;

import com.jumore.dove.boot.core.Application;
import com.jumore.dove.boot.core.ApplicationSettingAdapter;
import com.jumore.dove.boot.core.SpringContextHolder;

public class TextLearningApplication implements Application{

    @Override
    public void init() {
    }

    @Override
    public void onStarted() {
        ScrawService scrawService = SpringContextHolder.getContext().getBean(ScrawService.class);
        scrawService.init();
        Thread t = new Thread(scrawService);
        t.start();
    }

    @Override
    public Setting getSetting() {
        Application.Setting setting = new ApplicationSettingAdapter() {
            @Override
            public String getPropertityFiles() {
                return "config/app.properties";
            }
            
            @Override
            public String getControllerScanPackage() {
                return "org.bc.npl.textlearning.controller";
            }
            
            @Override
            public String getComponentScanPackage() {
                return "org.bc.npl.textlearning.service";
            }
            
            @Override
            public Class<?> getAuthRealmClass() {
                return SimpleDBRealm.class;
            }
        };
        return setting;
    }

}
