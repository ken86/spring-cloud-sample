package org.kd.ms.integration.inotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DirMonitorImpl implements DirMonitor, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(DirMonitorImpl.class);

    static {
        System.load("/home/hk/clang/kdev_inotify.so");
        //System.loadLibrary("kdev_inotify");
    }

    private volatile Executor executor;
    private Map<String, File> mapOfDirectoriesUnderMonitor =
            new ConcurrentHashMap<>();
    private volatile ConcurrentHashMap<File, FileAddedListener> monitors =
            new ConcurrentHashMap<>();
    private boolean autoCreateDirectory = true;

    protected boolean exists(File dir) {
        boolean goodDirToMonitor = (dir.isDirectory() && dir.exists());
        Assert.notNull(dir, "the 'dir' parameter must not be null");
        if (!goodDirToMonitor) {
            if (!dir.exists()) {
                if (this.autoCreateDirectory) {
                    if (!dir.mkdirs()) {
                        logger.info("couldn't create directory {}",
                                dir.getAbsolutePath());
                    }
                }
            }
        }
        Assert.state(dir.exists(), "the directory " + dir.getAbsolutePath()
                + " doesn't exist");
        return dir.exists();
    }

    public void fileReceived(String dir, String fileName) {
        File dirFile = mapOfDirectoriesUnderMonitor.get(dir);
        this.monitors.get(dirFile).fileAdded(dirFile, fileName);
    }

    @Override
    public void monitor(final File dir, final FileAddedListener fal) {
        if (exists(dir)) {
            mapOfDirectoriesUnderMonitor.put(dir.getAbsolutePath(), dir);
            monitors.putIfAbsent(dir, fal);
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    monitor(dir.getAbsolutePath());
                }
            });
        }
    }

    native void monitor(String path);

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (this.executor == null) {
            this.executor = Executors.newFixedThreadPool(10);
        }
    }
}
