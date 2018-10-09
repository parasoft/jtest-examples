package examples.metrics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashSet;
import java.util.Set;

public class MetricsExample {

    public Set<Integer> collectErrorCodesFromFiles(File[] aFile) throws IOException {
        Set<Integer> hsErrorCode = new HashSet<Integer>();
        if (aFile != null) {
            for (File file : aFile) {
                if (file.isFile()) {
                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new FileReader(file));
                        String read = null;
                        while ((read = reader.readLine()) != null) {
                            String sLineType = read.substring(0, 3);
                            if ("ERR".equals(sLineType)) {
                                int codeIndex = read.indexOf("code:");
                                if (codeIndex > 0) {
                                    String sCode = read.substring(codeIndex + 5, 3);
                                    try {
                                        int code = Integer.parseInt(sCode);
                                        hsErrorCode.add(code);
                                    } catch (NumberFormatException nfe) {
                                        nfe.printStackTrace();
                                    }
                                }
                            }
                        }
                    } finally {
                        if (reader != null) {
                            reader.close();
                        }
                    }
                } else if (file.isDirectory()) {
                    // TODO process directory content
                    return hsErrorCode;
                } else {
                    throw new IllegalArgumentException("Unsupported file type!");
                }
            }
        }
        return hsErrorCode;
    }
}
