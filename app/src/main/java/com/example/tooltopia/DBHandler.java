package com.example.tooltopia;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "tooltopia";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our item table name.
    private static final String ITEM_TABLE = "items";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String NAME_COL = "name";

    // below variable id for our course duration column.
    private static final String PRICE_COL = "price";

    // below variable for our course description column.
    private static final String DESCRIPTION_COL = "description";

    // below variable for our order table name.
    private static final String ORDER_TABLE = "orders";

    // below variable is for our number column.
    private static final String NUMBER_COL = "number";

    // below variable is for our date column.
    private static final String DATE_COL = "date";

    // below variable is for our total column.
    private static final String TOTAL_COL = "total";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
}

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + ITEM_TABLE + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + PRICE_COL + " DOUBLE,"
                + DESCRIPTION_COL + " TEXT)";
        db.execSQL(query);

        // Create the orders table (you missed executing this query before)
        String query2 = "CREATE TABLE " + ORDER_TABLE + " ("
                + NUMBER_COL + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DATE_COL + " TEXT,"
                + TOTAL_COL + " DOUBLE)";
        db.execSQL(query2);

        // Hard-coded items
        Items item1 = new Items(1, "Hammer", "Good for hitting nails", 1200.00);
        Items item2 = new Items(2, "Nails", "Good for getting hammered", 699.99);
        Items item3 = new Items(3, "Tool Belt", "Good for holding hammer and nails", 249.99);
        Items item4 = new Items(4, "Saw", "Good for cutting wood", 999.99);
        Items item5 = new Items(5, "Wood", "Good for getting sawed", 499.99);
        Items item6 = new Items(6, "Screwdriver", "Good for screwing screws", 299.99);

        // Insert items into database
        insertItemIntoDatabase(item1, db);
        insertItemIntoDatabase(item2, db);
        insertItemIntoDatabase(item3, db);
        insertItemIntoDatabase(item4, db);
        insertItemIntoDatabase(item5, db);
        insertItemIntoDatabase(item6, db);

    }

    private void insertItemIntoDatabase(Items item, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(ID_COL, item.getId());
        values.put(NAME_COL, item.getName());
        values.put(DESCRIPTION_COL, item.getDescription());
        values.put(PRICE_COL, item.getPrice());

        db.insert(ITEM_TABLE, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_TABLE);
        onCreate(db);
    }

    // Method to add an order to the database
    public void addOrder(String date, double total) {
        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();
        values.put(DATE_COL, date);
        values.put(TOTAL_COL, total);
        db.insert(ORDER_TABLE, null, values);
        db.close();
    }

    public List<Items> getAllItems() {
        List<Items> itemList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(ITEM_TABLE, null, null, null, null, null, null);

        int idIndex = cursor.getColumnIndex(ID_COL);
        int nameIndex = cursor.getColumnIndex(NAME_COL);
        int priceIndex = cursor.getColumnIndex(PRICE_COL);
        int descriptionIndex = cursor.getColumnIndex(DESCRIPTION_COL);

        if (idIndex != -1 && nameIndex != -1 && priceIndex != -1 && descriptionIndex != -1) {
            do {
                int id = cursor.getInt(idIndex);
                String name = cursor.getString(nameIndex);
                double price = cursor.getDouble(priceIndex);
                String description = cursor.getString(descriptionIndex);

                Items item = new Items(id, name, description, price);
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return itemList;
    }
}


