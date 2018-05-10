package game.com.databasepractice;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    EditText mEmail;
    EditText mPassword;

    AppCompatButton mLogIn;
    AppCompatButton mNewAccount;

    MemberDb mMem;
    static int count=1;
    public void onBackPressed()
    {
        if(count>1)
        {
            Intent intent=new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Press back again to exit", Toast.LENGTH_LONG).show();
            count++;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        mEmail=(EditText)findViewById(R.id.email);
        mPassword=(EditText)findViewById(R.id.password);

        mLogIn=(AppCompatButton)findViewById(R.id.login_button);
        mNewAccount=(AppCompatButton)findViewById(R.id.not_a_member_signup_button);
        mMem=new MemberDb(getApplicationContext(),"ACCOUNT DB",null,2);

        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mEmail.getText().toString().equals("") || mPassword.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SQLiteDatabase db=mMem.getWritableDatabase();
                    Cursor mCursor=db.query("ACCOUNT",null,null,null,null,null,null);
                    while (mCursor.moveToNext())
                    {
                        if(mCursor.getString(2).equals(mEmail.getText().toString()) && mCursor.getString(3).equals(mPassword.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_LONG).show();
                            mEmail.setText("");
                            mPassword.setText("");
                            return;
                        }
                    }
                    Toast.makeText(getApplicationContext(), "Invalid Email Id or password", Toast.LENGTH_LONG).show();
                }
            }
        });

        mNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LogInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
