package com.example.keepmoneyv3.database;

/**
 * This class wraps some static classes,
 * which are used to refer to database's table's names and fields
 *
 * @author Giacomo Detomaso
 * */

public class DbStrings {

    public static class TableUsersField {
        public static final String TABLE_NAME = "users";
        static final String USERS_ID = "username";
        static final String USERS_PWD = "password";
        static final String USERS_NAME = "name";
        static final String USERS_SURNAME = "surname";
        static final String USERS_EMAIL = "email";
        public static final String USERS_TOT = "total";

        private static final String CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (\n" +
                        USERS_ID + " VARCHAR(100) PRIMARY KEY,\n" +
                        USERS_PWD + " VARCHAR(100) NOT NULL,\n" +
                        USERS_EMAIL + " VARCHAR(255) DEFAULT NULL, \n" +
                        USERS_NAME + " VARCHAR(100) NOT NULL,\n" +
                        USERS_SURNAME + " VARCHAR(100) NOT NULL,\n" +
                        USERS_TOT + " DECIMAL NOT NULL); ";

        static String getCreateTable(){
            return CREATE_TABLE;
        }
    }

    public static class TableCategoriesFields{
        public static final String TABLE_NAME = "categories";
        public static final String CATEGORIES_ID = "id";
        public static final String CATEGORIES_DESC = "description";
        public static final String CATEGORIES_PIC_ID = "picId";

        private static final String CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (\n" +
                        CATEGORIES_ID + " VARCHAR(8) PRIMARY KEY,\n" +
                        CATEGORIES_DESC + " VARCHAR(255),\n" +
                        CATEGORIES_PIC_ID + " INT NOT NULL);";

        static String getCreateTable(){
            return CREATE_TABLE;
        }

    }

    public static class TableItemsFields{
        public static final String TABLE_NAME = "items";
        public static final String ITEMS_ID = "id";
        public static final String ITEMS_PRICE = "price";
        public static final String ITEMS_AMOUNT = "amount";
        public static final String ITEMS_NAME = "name";
        public static final String ITEMS_VALID = "isValid";
        public static final String ITEMS_ID_CAT = "idCat";

        private static final String CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (\n" +
                        ITEMS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        ITEMS_PRICE + " DECIMAL NOT NULL,\n" +
                        ITEMS_NAME + " VARCHAR(255),\n" +
                        ITEMS_AMOUNT + " INT DEFAULT 0,\n" + // the parameter can be null
                        ITEMS_VALID + " INT,\n" +
                        ITEMS_ID_CAT + " VARCHAR(8) NOT NULL,\n" +
                        "FOREIGN KEY (" + ITEMS_ID_CAT + ") REFERENCES " +  //foreign key declaration
                        TableCategoriesFields.TABLE_NAME + " (" + TableCategoriesFields.CATEGORIES_ID + "));";

        static String getCreateTable() {
            return CREATE_TABLE;
        }
    }

    public static class TableEntriesFields{
        public static final String TABLE_NAME = "entries";
        public static final String ENTRIES_ID = "id";
        public static final String ENTRIES_VAL = "value";
        public static final String ENTRIES_DATE = "dateEntr";
        public static final String ENTRIES_ID_CAT = "idCat";
        public static final String ENTRIES_ID_USER = "userId";

        private static final String CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (\n" +
                        ENTRIES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,\n" +
                        ENTRIES_VAL + " DECIMAL NOT NULL,\n" +
                        ENTRIES_DATE + " DATE,\n" +
                        ENTRIES_ID_CAT + " VARCHAR(8) NOT NULL,\n" +
                        ENTRIES_ID_USER + " VARCHAR(100) NOT NULL,\n" +

                        "FOREIGN KEY (" + ENTRIES_ID_CAT + ") REFERENCES " +  //foreign key declaration
                        TableCategoriesFields.TABLE_NAME + " (" + TableCategoriesFields.CATEGORIES_ID + "),\n" +

                        "FOREIGN KEY (" + ENTRIES_ID_USER + ") REFERENCES " +
                        TableUsersField.TABLE_NAME + " (" + TableUsersField.USERS_ID + "));";


        static String getCreateTable() {
            return CREATE_TABLE;
        }
    }


    public static class TableWishListsFields{
        public static final String TABLE_NAME = "wishLists";
        public static final String WL_ID = "id";
        public static final String WL_NAME = "name";
        public static final String WL_DESC = "description";
        public static final String WL_VALID = "isValid";

        static final String CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (\n" +
                        WL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        WL_NAME + " VARCHAR(80) NOT NULL,\n" +
                        WL_DESC + " VARCHAR(255),\n" +
                        WL_VALID + " INT);";

        static String getCreateTable() {
            return CREATE_TABLE;
        }
    }

    public static class TablePurchasesFields{
        public static final String TABLE_NAME = "purchases";
        public static final String PURCH_ID = "id";
        public static final String PURCH_DATE = "dateP";
        public static final String PURCH_TIME = "timeP";
        public static final String PURCH_ID_USER = "userId";
        public static final String PURCH_ITEM_ID = "itemId";
        public static final String PURCH_WL_ID = "listId";

        static final String CREATE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (\n" +
                        PURCH_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                        PURCH_DATE + " DATE,\n" +
                        PURCH_TIME + " TIME,\n" +
                        PURCH_ID_USER + " VARCHAR(100) NOT NULL,\n" +
                        PURCH_ITEM_ID + " INTEGER NOT NULL,\n" +
                        PURCH_WL_ID + " INTEGER DEFAULT NULL,\n" +

                        "FOREIGN KEY (" + PURCH_ID_USER + ") REFERENCES " +
                        TableUsersField.TABLE_NAME + " (" + TableUsersField.USERS_ID + "),\n" +

                        "FOREIGN KEY (" + PURCH_ITEM_ID + ") REFERENCES " +
                        TableItemsFields.TABLE_NAME + " (" + TableItemsFields.ITEMS_ID + "),\n" +

                        "FOREIGN KEY (" + PURCH_WL_ID + ") REFERENCES " +
                        TableWishListsFields.TABLE_NAME + " (" + TableWishListsFields.WL_ID + ") " +
                        "ON UPDATE CASCADE ON DELETE SET DEFAULT);";

        static String getCreateTable() {
            return CREATE_TABLE;
        }
    }

}