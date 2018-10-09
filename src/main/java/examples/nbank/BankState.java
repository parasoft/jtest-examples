package examples.nbank;

public class BankState {

    private static final String MAINTENANCE_MODE = "demo-bank-maintanance";

    public static Boolean isMaintenanceMode() {
        String value = System.getProperty(MAINTENANCE_MODE);
        if ((value == null) || (value.trim().length() == 0)) {
            return false;
        }
        return Boolean.valueOf(value);
    }

    public static void startMaintenance() {
        System.setProperty(MAINTENANCE_MODE, Boolean.TRUE.toString());
    }

    public static void endMaintenance() {
        System.setProperty(MAINTENANCE_MODE, Boolean.FALSE.toString());
    }
}
