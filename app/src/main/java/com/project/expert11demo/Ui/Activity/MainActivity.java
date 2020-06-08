/**
 * Created by Maniraj Agarwal on 8/6/20.
 */
package com.project.expert11demo.Ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.project.expert11demo.Database.AppDatabase;
import com.project.expert11demo.Database.dbClient;
import com.project.expert11demo.Interface.BooksApi;
import com.project.expert11demo.Model.Book;
import com.project.expert11demo.R;
import com.project.expert11demo.Service.RetrofitClient;
import com.project.expert11demo.Ui.CommonAndroid.CommonFunction;
import com.project.expert11demo.Ui.Fragment.ListingFragment;


import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ArrayList<Book> listOfBook;
    private String TAG = MainActivity.class.getName();
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(isDeviceOnline(this)) {
            CommonFunction.showProgressDialog(this);
            settingUpElements();
        }
        else{
            getList();
        }
    }

    private void getList() {
        class GetList extends AsyncTask<Void, Void, ArrayList<Book>> {

            @Override
            protected ArrayList<Book> doInBackground(Void... voids) {
                List<Book> lob = dbClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .bookDao()
                        .getAll();
                ArrayList<Book> list = new ArrayList<>();
                list.addAll(lob);
                return list;
            }

            @Override
            protected void onPostExecute(ArrayList<Book> lob) {
                super.onPostExecute(lob);
                if(lob.size()>0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("listOfBook", lob);
                    startFragmentTransaction(new ListingFragment(), bundle);
                }
                else{
            TextView tv = findViewById(R.id.text_view1);
            tv.setVisibility(View.INVISIBLE);
            TextView tv1 = findViewById(R.id.tv2);
            tv1.setVisibility(View.VISIBLE);
            tv1.setText("No internet");
                }
            }
        }

        GetList gl = new GetList();
        gl.execute();
    }

    private void settingUpElements() {
        BooksApi booksApi = RetrofitClient.getRetrofitInstance().create(BooksApi.class);
        Call<ArrayList<Book>> call = booksApi.getBooksList();
        Log.v(TAG, call.request().url().toString());
        call.enqueue(new Callback<ArrayList<Book>>() {
            @Override
            public void onResponse(Call<ArrayList<Book>> call, Response<ArrayList<Book>> response) {
                listOfBook = response.body();
                if(listOfBook.size()>0){
                    CommonFunction.hideProgressDialog();
                    saveList(listOfBook);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("listOfBook", listOfBook);
                    startFragmentTransaction(new ListingFragment(),bundle);
                }
                else{
                    CommonFunction.hideProgressDialog();
                    Toast.makeText(MainActivity.this, "No data received", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Book>> call, Throwable t) {
                CommonFunction.hideProgressDialog();
                Toast.makeText(MainActivity.this, "Network Called Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void saveList(final ArrayList<Book> book) {
        class SaveList extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                dbClient.getInstance(getApplicationContext()).getAppDatabase().bookDao().insertMultipleListRecord(book);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveList sl = new SaveList();
        sl.execute();
        }

    public boolean isDeviceOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    public void startFragmentTransaction(Fragment fragment, Bundle bundle) {
        fragment.setArguments(bundle);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

}