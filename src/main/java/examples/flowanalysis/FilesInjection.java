package examples.flowanalysis;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public class FilesInjection {

    /**
     * @param req
     * @throws IOException
     */
    public void fileNameContentsInjection(HttpServletRequest req) throws IOException {
        String sStoryName = req.getParameter("story_name");
        String sStoryContents = req.getParameter("story");
        store(sStoryName, sStoryContents);
    }

    private void store(String storyName, String storyContents) throws IOException {
        FileOutputStream fOut = null;
        try {
            String sFileName = storyName;
            fOut = new FileOutputStream(sFileName); // Filename injection
            fOut.write(storyContents.getBytes()); // File contents injection
        } finally {
            fOut.close();
        }
    }
}
