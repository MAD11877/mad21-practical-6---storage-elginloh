    package sg.edu.np.mad.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper {
    public static final String TABLE_USERS = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_FOLLOWED = "followed";
    public DBHandler(@Nullable Context context) {
        super(context, TABLE_USERS, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_FOLLOWED + " INTEGER" + ")";
        db.execSQL(CREATE_USERS_TABLE);

        for(int i = 0; i < 20; i ++){
            int r = new Random().nextInt();
            int z = new Random().nextInt();
            int y = new Random().nextInt();
            int followed = 0;
            if(y%2 == 0)
            {
                followed = 0;
            }
            else
            {
                followed = 1;
            }
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, "Elgin"+r);
            values.put(COLUMN_DESCRIPTION, "Description"+z);
            values.put(COLUMN_FOLLOWED, followed);
            db.insert(TABLE_USERS, null, values);
        }
    }

    public ArrayList<User> getUser()
    {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS, null);
        ArrayList<User> wowsie = new ArrayList<>();
        while (cursor.moveToNext()) {
            User idkman = new User();
            idkman.setName(cursor.getString(1));
            idkman.setDescription(cursor.getString(2));
            if(cursor.getInt(3) == 0)
            {
                idkman.setFollowed(false);
            }
            else
            {
                idkman.setFollowed(true);
            }
            wowsie.add(idkman);
        }
        cursor.close();
        db.close();
        return wowsie;
    }

    public void updateUser(User user)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        if (user.isFollowed())
        {
            cv.put(COLUMN_FOLLOWED, 1);
        }
        else
        {
            cv.put(COLUMN_FOLLOWED, 0);
        }
        cv.put(COLUMN_NAME, user.name);
        cv.put(COLUMN_DESCRIPTION, user.description);
        db.update(TABLE_USERS, cv,  COLUMN_NAME + "=?",new String[]{user.name});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

}
