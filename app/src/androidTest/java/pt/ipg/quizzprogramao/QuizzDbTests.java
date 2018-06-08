package pt.ipg.quizzprogramao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.security.AccessControlContext;

import static android.support.test.InstrumentationRegistry.getContext;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class QuizzDbTests {
    
    @Before
    public void setUp(){
        getContext().deleteDatabase(DbQuizzOpenHelper.DATABASE_NAME);
    }

    @Test
    public  void openQuizzDbTests() {
        
         Context appContext = getContext();

        DbQuizzOpenHelper dbQuizzOpenHelper = new DbQuizzOpenHelper(appContext);

        SQLiteDatabase db = dbQuizzOpenHelper.getReadableDatabase();
        assertTrue("Could not open/create books database", db.isOpen());
        db.close();
    }

    private Context getContext() {
        return InstrumentationRegistry.getTargetContext();
    }
}
