package se.kth.epe.degreeproject.modeltypes;

/**
 * Created by Rafi on 2017-02-18.
 */
public class WebApplication {
    private boolean noPublicXSSVulnerabilities;
    private boolean typeSafeAPI;
    private boolean securityAwareDevelopers;
    private boolean blackBoxTesting;
    private boolean noPublicCIVulnerabilities;
    private boolean noPublicRFIVulnerabilities;
    private boolean noPublicSQLIVulnerabilities;
    private boolean staticCodeAnalysis;

    public boolean isNoPublicXSSVulnerabilities() {
        return noPublicXSSVulnerabilities;
    }

    public void setNoPublicXSSVulnerabilities(boolean noPublicXSSVulnerabilities) {
        this.noPublicXSSVulnerabilities = noPublicXSSVulnerabilities;
    }

    public boolean isTypeSafeAPI() {
        return typeSafeAPI;
    }

    public void setTypeSafeAPI(boolean typeSafeAPI) {
        this.typeSafeAPI = typeSafeAPI;
    }

    public boolean isSecurityAwareDevelopers() {
        return securityAwareDevelopers;
    }

    public void setSecurityAwareDevelopers(boolean securityAwareDevelopers) {
        this.securityAwareDevelopers = securityAwareDevelopers;
    }

    public boolean isBlackBoxTesting() {
        return blackBoxTesting;
    }

    public void setBlackBoxTesting(boolean blackBoxTesting) {
        this.blackBoxTesting = blackBoxTesting;
    }

    public boolean isNoPublicCIVulnerabilities() {
        return noPublicCIVulnerabilities;
    }

    public void setNoPublicCIVulnerabilities(boolean noPublicCIVulnerabilities) {
        this.noPublicCIVulnerabilities = noPublicCIVulnerabilities;
    }

    public boolean isNoPublicRFIVulnerabilities() {
        return noPublicRFIVulnerabilities;
    }

    public void setNoPublicRFIVulnerabilities(boolean noPublicRFIVulnerabilities) {
        this.noPublicRFIVulnerabilities = noPublicRFIVulnerabilities;
    }

    public boolean isNoPublicSQLIVulnerabilities() {
        return noPublicSQLIVulnerabilities;
    }

    public void setNoPublicSQLIVulnerabilities(boolean noPublicSQLIVulnerabilities) {
        this.noPublicSQLIVulnerabilities = noPublicSQLIVulnerabilities;
    }

    public boolean isStaticCodeAnalysis() {
        return staticCodeAnalysis;
    }

    public void setStaticCodeAnalysis(boolean staticCodeAnalysis) {
        this.staticCodeAnalysis = staticCodeAnalysis;
    }
}
