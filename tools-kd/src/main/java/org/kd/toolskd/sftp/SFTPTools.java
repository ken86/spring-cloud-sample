package org.kd.toolskd.sftp;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SFTPTools {


    public static void main(String[] args) throws JSchException {
        JSch jSch = new JSch();
        Session session = null;
        try {
            session = jSch.getSession("hk", "192.168.50.96", 22);
            session.setConfig("StrictHostKeyChecking", "no");
            //String knownHostFile = "/home/hk/";
            //jSch.setKnownHosts(knownHostFile);
            session.setPassword("k123");
            session.connect();
        } finally {
            session.disconnect();

        }



    }
    
}
