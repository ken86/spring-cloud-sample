package org.kd.ms.integration.inotify;

import org.springframework.beans.factory.InitializingBean;

import java.io.File;

public class DirMonitorImpl implements DirMonitor, InitializingBean {

    static {
        System.load("/usr/lib/kdev_inotify.so");
        //System.loadLibrary("kdev_inotify");
    }

    @Override
    public void monitor(File file, FileAddedListener fileAddedListener) {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    native void monitor(String path);
}
