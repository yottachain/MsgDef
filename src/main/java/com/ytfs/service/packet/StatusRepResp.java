package com.ytfs.service.packet;

public class StatusRepResp {

    private long productiveSpace;
    private String relayUrl;

    /**
     * @return the productiveSpace
     */
    public long getProductiveSpace() {
        return productiveSpace;
    }

    /**
     * @param productiveSpace the productiveSpace to set
     */
    public void setProductiveSpace(long productiveSpace) {
        this.productiveSpace = productiveSpace;
    }

    /**
     * @return the relayUrl
     */
    public String getRelayUrl() {
        return relayUrl;
    }

    /**
     * @param relayUrl the relayUrl to set
     */
    public void setRelayUrl(String relayUrl) {
        this.relayUrl = relayUrl;
    }

}
