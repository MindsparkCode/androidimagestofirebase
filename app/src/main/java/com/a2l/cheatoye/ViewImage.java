package com.a2l.cheatoye;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewImage extends AppCompatActivity {

    //    ImageView imageView;
//    StorageReference storageReference;
    GridView gv;
    ArrayAdapter<String> adapter;
    DatabaseReference db;
    FirebaseHelper helper;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        gv = (GridView) findViewById(R.id.gv);
        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, helper.retrieve());
        gv.setAdapter(adapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ViewImage.this, helper.retrieve().get(position), Toast.LENGTH_SHORT).show();
            }
        });
        //imageView = (ImageView) findViewById(R.id.ImageView);
//        storageReference = FirebaseStorage.getInstance().getReference().child("photos/107");
//        Glide.with(this)
//                .using(new FirebaseImageLoader())
//                .load(storageReference)
//                .into(imageView);
//    }
//
//    public class FirebaseImageLoader implements StreamModelLoader<StorageReference> {
//
//        @Override
//        public DataFetcher<InputStream> getResourceFetcher(StorageReference model, int width, int height) {
//            return new FirebaseStorageFetcher(model);
//        }
//
//        private class FirebaseStorageFetcher implements DataFetcher<InputStream> {
//
//            private StorageReference mRef;
//
//            FirebaseStorageFetcher(StorageReference ref) {
//                mRef = ref;
//            }
//
//            @Override
//            public InputStream loadData(Priority priority) throws Exception {
//                return Tasks.await(mRef.getStream()).getStream();
//            }
//
//            @Override
//            public void cleanup() {
//                // No cleanup possible, Task does not expose cancellation
//            }
//
//            @Override
//            public String getId() {
//                return mRef.getPath();
//            }
//
//            @Override
//            public void cancel() {
//                // No cancellation possible, Task does not expose cancellation
//            }
//        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ViewImage Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}