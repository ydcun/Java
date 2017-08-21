package com.ydcun.java.Client;





/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetInfo;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NfsFileSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.SysInfo;

/**
 *
 * @author k02
 */
public class LocalInfoCollection {

    Sigar sigar;

    /**
     * Construction Method
     */
    public LocalInfoCollection() {
        sigar = new Sigar();
    }

    public String getIPAddress() throws SigarException {
        NetInterfaceConfig nic = sigar.getNetInterfaceConfig();
        return nic.getAddress();
    }

    public String getMacAddress() throws SigarException {
        NetInterfaceConfig nic = sigar.getNetInterfaceConfig();
        return nic.getHwaddr();
    }

    public String getHostname() throws SigarException {
        NetInfo ni = sigar.getNetInfo();
        return ni.getHostName();
    }

    public String getOSInfo() throws SigarException {
        SysInfo si = new SysInfo();
        si.gather(sigar);
        return si.getDescription() + " " + si.getPatchLevel() + " " + si.getArch();
    }

    public int getProcessCount() throws SigarException {
        long[] proList = sigar.getProcList();
        return proList.length;
    }

    public String getCPUModels() throws SigarException {
        CpuInfo[] infos = sigar.getCpuInfoList();
        CpuInfo info = infos[0];
        return info.getVendor() + " " + info.getModel() + " " + info.getMhz() / 1000.0 + "Mhz";
    }

    public String getCPUUsage() throws SigarException {
        CpuPerc usage = sigar.getCpuPerc();
        return CpuPerc.format(usage.getCombined());
    }

    public String getMemSize() throws SigarException {
        Mem m = sigar.getMem();
        String mStr = new Long(m.getTotal() / 1024).toString();
        return mStr + "K";
    }

    public String getMemUsage() throws SigarException {
        Mem m = sigar.getMem();
        String mStr = new Double(m.getUsedPercent()).toString();
        return mStr.substring(0, 5) + "%";
    }

    public String getDiskTotalSize() throws SigarException {
        String diskSize="";
        long diskTotalSizeL = 0;
        FileSystem[] fslist = null;
        try {
            fslist = sigar.getFileSystemList();
        } catch (SigarException se) {
            se.printStackTrace();
        }
        for (int i = 0; i < fslist.length; i++) {
            diskTotalSizeL+=getEachPartSize(fslist[i]);
        }
        diskSize=Sigar.formatSize(diskTotalSizeL).trim();
        return diskSize;
    }

    private long getEachPartSize(FileSystem fs) {
        long total;
        try {
            FileSystemUsage usage;
            if (fs instanceof NfsFileSystem) {
                NfsFileSystem nfs = (NfsFileSystem) fs;
                if (!nfs.ping()) {
                    return 0;
                }
            }
            usage = sigar.getFileSystemUsage(fs.getDirName());
            total = usage.getTotal() * 1024;
        } catch (SigarException e) {
            //if there is no cd in the drive.
            total = 0;
        }
        return total;
    }

   
    public String toString(){
        String localDataStr="";
        //Wait modify
        return "";
    }
}
