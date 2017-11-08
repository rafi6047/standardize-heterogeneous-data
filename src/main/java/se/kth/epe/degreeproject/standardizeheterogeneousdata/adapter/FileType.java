package se.kth.epe.degreeproject.standardizeheterogeneousdata.adapter;

/**
 * Created by Rafi on 2017-04-17.
 */
public enum FileType {
    MSPOWERSHELL, NETSTAT, INSTALLED;

    public static FileType contains(final String fileType) {
        for (FileType fileType1 : FileType.values()) {
            if (fileType1.name().equalsIgnoreCase(fileType)) {
                return fileType1;
            }
        }
        return null;
    }
}
