package com.example.tunguyen.manga.view.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.tunguyen.manga.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Database helper which creates and upgrades the database and provides the DAOs for the app.
 *
 *
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    /************************************************
     * Suggested Copy/Paste code. Everything from here to the done block.
     ************************************************/

    private static final String DATABASE_NAME = "advertmangas.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<AdvertMangas, Integer> advertMangasesDao;
    private Dao<ChapterMangas, Integer> chapterMangasesDao;
    private Dao<AdvertViewedMangas, Integer> advertViewedMangasesDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    /************************************************
     * Suggested Copy/Paste Done
     ************************************************/

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        try {

            // Create tables. This onCreate() method will be invoked only once of the application life time i.e. the first time when the application starts.
            TableUtils.createTable(connectionSource, AdvertMangas.class);
            TableUtils.createTable(connectionSource, ChapterMangas.class);
            TableUtils.createTable(connectionSource, AdvertViewedMangas.class);


        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create datbases", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {

            // In case of change in database of next version of application, please increase the value of DATABASE_VERSION variable, then this method will be invoked
            //automatically. Developer needs to handle the upgrade logic here, i.e. create a new table or a new column to an existing table, take the backups of the
            // existing database etc.

            TableUtils.dropTable(connectionSource, AdvertMangas.class, true);
            TableUtils.dropTable(connectionSource, ChapterMangas.class, true);
            TableUtils.dropTable(connectionSource, AdvertViewedMangas.class, true);

            onCreate(sqliteDatabase, connectionSource);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
                    + newVer, e);
        }
    }
    public Dao<AdvertMangas, Integer> getAdvertMangasDao() throws SQLException {
        if (advertMangasesDao == null) {
            advertMangasesDao = getDao(AdvertMangas.class);
        }
        return advertMangasesDao;
    }
    public Dao<ChapterMangas, Integer> getChapterMangasDao() throws SQLException {
        if (chapterMangasesDao == null) {
            chapterMangasesDao = getDao(ChapterMangas.class);
        }
        return chapterMangasesDao;
    }
    public Dao<AdvertViewedMangas, Integer> getAdvertViewedMangasDao() throws SQLException {
        if (advertViewedMangasesDao == null) {
            advertViewedMangasesDao = getDao(AdvertViewedMangas.class);
        }
        return advertViewedMangasesDao;
    }

}
