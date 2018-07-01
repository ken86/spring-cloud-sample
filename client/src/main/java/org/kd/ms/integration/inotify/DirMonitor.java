package org.kd.ms.integration.inotify;

import java.io.File;

public interface DirMonitor {

    void monitor(File file, FileAddedListener fileAddedListener);

    interface FileAddedListener {
        void fileAdded(File dir, String fn);
    }
}
