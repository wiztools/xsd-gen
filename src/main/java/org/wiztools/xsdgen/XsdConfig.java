package org.wiztools.xsdgen;

import java.util.Objects;

/**
 *
 * @author subwiz
 */
public class XsdConfig {
    private String xsdPrefix = "xsd";
    private boolean enableMaxOccursOnce = false;

    public String getXsdPrefix() {
        return xsdPrefix;
    }

    public void setXsdPrefix(String xsdPrefix) {
        this.xsdPrefix = xsdPrefix;
    }

    public boolean isEnableMaxOccursOnce() {
        return enableMaxOccursOnce;
    }

    public void setEnableMaxOccursOnce(boolean enableMaxOccursOnce) {
        this.enableMaxOccursOnce = enableMaxOccursOnce;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.xsdPrefix);
        hash = 29 * hash + (this.enableMaxOccursOnce ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final XsdConfig other = (XsdConfig) obj;
        if (!Objects.equals(this.xsdPrefix, other.xsdPrefix)) {
            return false;
        }
        if (this.enableMaxOccursOnce != other.enableMaxOccursOnce) {
            return false;
        }
        return true;
    }
}
