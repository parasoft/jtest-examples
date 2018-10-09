package examples.flowanalysis;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Developer007
 */
public abstract class SystemInjection {

    public HttpServletRequest _req = null;

    private String getData(String sParamName) {
        return _req.getParameter(sParamName);
    }

    void execInjection() throws Exception {
        // Command injection:
        // Providing " & shutdown -s -f -d p" as command_parameter value in the
        // request
        // will
        // cause the server to shutdown if the command is executed as following:
        String sCommand = "call process_data.exe -params " + getData("command_parameters");
        Runtime.getRuntime().exec(sCommand);
    }

    void environmentInjection() throws Exception {
        // In some cases, like when a non-analyzable method processes
        // user provided data, we can't gaurantee the data is safe.
        // So using this data to set system properties should be restricted
        // because it can cause damage to the system.
        String sSomeResultingString = processValue(getData("user_data"));
        System.setProperty("someProperty", sSomeResultingString);
    }

    void libraryInjection() throws Exception {
        // Library injection:
        // In the following example we can see that data,
        // provided by the user to load a library is processed in
        // an unkown manner which can lead to operations dangerous to the
        // system.
        String libName = processValue(getData("operation_needed"));
        System.loadLibrary(libName);
    }

    void reflectionInjection() throws Exception {
        // Reflection injection:
        // This situation is very simillar to library injection
        String sClassName = processValue(getData("operation_needed"));
        ClassLoader.getSystemClassLoader().loadClass(sClassName);
    }

    abstract String processValue(String sValue);

    abstract String chooseLibrary(String operationNeeded);
}
