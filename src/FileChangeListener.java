/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.nio.file.FileSystemException;
import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;

/**
 *
 * @author Ricardo
 */
public class FileChangeListener implements FileListener {

    DefaultFileMonitor fm;
    public File logFile;
    public FileChangeListener(String log){
        this.logFile = new File(log);
    }

    public void startListening() {
        try {
            final FileSystemManager fsManager = VFS.getManager();
            final FileObject listendir = fsManager.toFileObject(logFile);

            fm = new DefaultFileMonitor(this);
            fm.addFile(listendir);
            fm.start();
        } catch (Exception ex) {

        }
    }

    @Override
    public void fileCreated(FileChangeEvent fce) throws Exception {
        fileChanged(fce);
    }

    @Override
    public void fileDeleted(FileChangeEvent fce) throws Exception {
        //hmm..why deleted?
    }

    @Override
    public void fileChanged(FileChangeEvent fce) throws Exception {
        synchronized(this)
        {
            this.notifyAll();
        }
    }
}
