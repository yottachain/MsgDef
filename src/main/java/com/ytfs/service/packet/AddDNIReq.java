package com.ytfs.service.packet;

import java.util.ArrayList;
import java.util.List;

public class AddDNIReq {

    private List<DNI> dnis = new ArrayList();

    public void addDNI(DNI dni) {
        this.dnis.add(dni);
    }

    /**
     * @return the dnis
     */
    public List<DNI> getDnis() {
        return dnis;
    }

    /**
     * @param dnis the dnis to set
     */
    public void setDnis(List<DNI> dnis) {
        this.dnis = dnis;
    }

    public class DNI {

        private byte[] VHF;
        private int nodeid;

        /**
         * @return the VHF
         */
        public byte[] getVHF() {
            return VHF;
        }

        /**
         * @param VHF the VHF to set
         */
        public void setVHF(byte[] VHF) {
            this.VHF = VHF;
        }

        /**
         * @return the nodeid
         */
        public int getNodeid() {
            return nodeid;
        }

        /**
         * @param nodeid the nodeid to set
         */
        public void setNodeid(int nodeid) {
            this.nodeid = nodeid;
        }

    }

}
