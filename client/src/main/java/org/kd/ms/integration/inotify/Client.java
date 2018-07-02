package org.kd.ms.integration.inotify;

import org.apache.commons.lang.SystemUtils;

import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Client {

    public static void main(String[] args) throws Exception {
        File[] files = {
                new File(new File(SystemUtils.getUserHome(), "Desktop"), "test2"),
                new File(new File(SystemUtils.getUserHome(), "Desktop"), "test1")};
        Executor ex = Executors.newFixedThreadPool(10);
        final DirMonitorImpl monitor = new DirMonitorImpl();
        monitor.setExecutor(ex);
        monitor.afterPropertiesSet();
        final DirMonitor.FileAddedListener fileAddedListener =
                new DirMonitor.FileAddedListener() {
                    @Override
                    public void fileAdded(File dir, String fn) {
                        System.out.println("A new file in " + dir.getAbsolutePath()
                                + " called " + fn + " has been noticed");
                    }
                };
        for (File f : files) {
            monitor.monitor(f, fileAddedListener);
        }
    }

}

