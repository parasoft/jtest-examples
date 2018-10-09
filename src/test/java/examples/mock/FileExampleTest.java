package examples.mock;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import java.io.File;

import org.junit.Test;
import org.mockito.ArgumentMatcher;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.anyLong;

import examples.mock.FileExample;

public class FileExampleTest 
{
	@SuppressWarnings("unchecked")
	@Test
    public void testAnalyze()
    {
        File file = mock(File.class);

        when(file.getAbsolutePath()).thenReturn("Z:\\tmp\\X001.txt");
        when(file.setLastModified(anyLong())).thenReturn(true);
        when(file.compareTo((File)argThat(new IsFileNameCorrect("X001.txt")))).thenReturn(10);
        when(file.compareTo((File)argThat(new IsFileNameCorrect("XXX")))).thenReturn(0);

        String result = FileExample.analyze(file);
        assertEquals("Z:\\tmp\\X001.txt:true:10:0:", result);
    }

	class IsFileNameCorrect implements ArgumentMatcher<File> 
	{
		private final String nameToCompare;

		public IsFileNameCorrect(String fileName) 
		{
			nameToCompare = fileName;
		}
		@Override
		public boolean matches(File file) {
            return (file == null) ? false : nameToCompare.equals(((File)file).getName());

		}
    }
}
