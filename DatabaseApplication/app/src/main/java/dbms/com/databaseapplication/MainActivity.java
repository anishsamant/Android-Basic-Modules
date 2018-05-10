package dbms.com.databaseapplication;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mName;
    EditText mAge;
    EditText mAddress;
    EditText mId;
    AppCompatButton mClear;
    AppCompatButton mSubmit;
    AppCompatButton mSearch;

    AddressDb mAddressDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName=(EditText)findViewById(R.id.enterName);
        mAge=(EditText)findViewById(R.id.enterAge);
        mAddress=(EditText)findViewById(R.id.enterAddress);
        mId=(EditText)findViewById(R.id.enterId);

        mClear=(AppCompatButton)findViewById(R.id.clear);
        mSubmit=(AppCompatButton)findViewById(R.id.submit);
        mSearch=(AppCompatButton)findViewById(R.id.search);

        mAddressDb=new AddressDb(getApplicationContext(),"Address Book",null,2);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mName.getText().toString().equals("") || mAge.getText().toString().equals("") || mAddress.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please fill all the fields", Toast.LENGTH_LONG).show();
                }
                else
                {
                    SQLiteDatabase mSqlDb=mAddressDb.getWritableDatabase();
                    ContentValues mContentValues=new ContentValues();
                    mContentValues.put("Name",mName.getText().toString());
                    mContentValues.put("Age",Integer.parseInt(mAge.getText().toString()));
                    mContentValues.put("Address",mAddress.getText().toString());
                    mSqlDb.insert("ADDRESSBOOK","Name",mContentValues);

                    Cursor mCursor=mSqlDb.query("ADDRESSBOOK",null,null,null,null,null,null);
                    mCursor.moveToLast();
                    mSqlDb.close();

                    Toast.makeText(getApplicationContext(), "Record Inserted, your ID is:- "+mCursor.getString(0), Toast.LENGTH_LONG).show();


                }
            }
        });

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mId.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter ID", Toast.LENGTH_LONG).show();
                }
                else
                {
                    SQLiteDatabase mSqlDb=mAddressDb.getWritableDatabase();
                    Cursor mCursor=mSqlDb.query("ADDRESSBOOK",null,null,null,null,null,null);
                    while (mCursor.moveToNext())
                    {
                        if(Integer.parseInt(mCursor.getString(0))==Integer.parseInt(mId.getText().toString()))
                        {
                            mName.setText(mCursor.getString(1));
                            mAge.setText(mCursor.getString(2));
                            mAddress.setText(mCursor.getString(3));
                            return;
                        }
                    }
                    Toast.makeText(getApplicationContext(), "ID not found", Toast.LENGTH_LONG).show();
                }
            }
        });

        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mName.setText("");
                mAge.setText("");
                mAddress.setText("");
                mId.setText("");
            }
        });
    }
}
