package game.com.databasepractice;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.LoginFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText mName;
    EditText mEmail;
    EditText mPassword;

    AppCompatButton mSignUp;

    MemberDb mMem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mName=(EditText)findViewById(R.id.name);
        mEmail=(EditText)findViewById(R.id.email);
        mPassword=(EditText)findViewById(R.id.password);

        mSignUp=(AppCompatButton)findViewById(R.id.signup_button);
        mMem=new MemberDb(getApplicationContext(),"ACCOUNT DB",null,2);

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mName.getText().toString().equals("") || mEmail.getText().toString().equals("") || mPassword.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SQLiteDatabase db=mMem.getWritableDatabase();
                    ContentValues cv=new ContentValues();
                    Cursor c=db.query("ACCOUNT",null,null,null,null,null,null);
                    while (c.moveToNext())
                    {
                        if(c.getString(2).equals(mEmail.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(), "Account Already exists", Toast.LENGTH_LONG).show();
                            return;
                        }
                    }
                    cv.put("Name", mName.getText().toString());
                    cv.put("Email", mEmail.getText().toString());
                    cv.put("Password", mPassword.getText().toString());
                    db.insert("ACCOUNT", "Name", cv);

                    db.close();

                    Toast.makeText(getApplicationContext(), "Account Created", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(SignUpActivity.this, LogInActivity.class);
                    startActivity(intent);

                }
            }
        });

    }
}
