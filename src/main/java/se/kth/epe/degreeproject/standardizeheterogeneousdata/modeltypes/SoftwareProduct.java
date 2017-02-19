package se.kth.epe.degreeproject.standardizeheterogeneousdata.modeltypes;

/**
 * Created by Rafi on 2017-02-18.
 */
public class SoftwareProduct extends AbstractModel {
    private boolean patched;
    private boolean staticCodeAnalysis;
    private boolean scrutinized;
    private boolean noUnpatchableVulnerability;
    private boolean noPatchableVulnerability;
    private boolean secretBinary;
    private boolean secretSource;
    private boolean hasVendorSupport;
    private boolean safeLanguages;

    public boolean isPatched() {
        return patched;
    }

    public void setPatched(boolean patched) {
        this.patched = patched;
    }

    public boolean isStaticCodeAnalysis() {
        return staticCodeAnalysis;
    }

    public void setStaticCodeAnalysis(boolean staticCodeAnalysis) {
        this.staticCodeAnalysis = staticCodeAnalysis;
    }

    public boolean isScrutinized() {
        return scrutinized;
    }

    public void setScrutinized(boolean scrutinized) {
        this.scrutinized = scrutinized;
    }

    public boolean isNoUnpatchableVulnerability() {
        return noUnpatchableVulnerability;
    }

    public void setNoUnpatchableVulnerability(boolean noUnpatchableVulnerability) {
        this.noUnpatchableVulnerability = noUnpatchableVulnerability;
    }

    public boolean isNoPatchableVulnerability() {
        return noPatchableVulnerability;
    }

    public void setNoPatchableVulnerability(boolean noPatchableVulnerability) {
        this.noPatchableVulnerability = noPatchableVulnerability;
    }

    public boolean isSecretBinary() {
        return secretBinary;
    }

    public void setSecretBinary(boolean secretBinary) {
        this.secretBinary = secretBinary;
    }

    public boolean isSecretSource() {
        return secretSource;
    }

    public void setSecretSource(boolean secretSource) {
        this.secretSource = secretSource;
    }

    public boolean isHasVendorSupport() {
        return hasVendorSupport;
    }

    public void setHasVendorSupport(boolean hasVendorSupport) {
        this.hasVendorSupport = hasVendorSupport;
    }

    public boolean isSafeLanguages() {
        return safeLanguages;
    }

    public void setSafeLanguages(boolean safeLanguages) {
        this.safeLanguages = safeLanguages;
    }
}
