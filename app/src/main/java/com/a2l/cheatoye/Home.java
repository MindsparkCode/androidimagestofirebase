package com.a2l.cheatoye;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;

//import com.google.firebase.database.DatabaseReference;

public class Home extends AppCompatActivity implements OnClickListener {

    private static final int CAMERA_REQUEST_CODE = 1;
    Button cameraButton, ViewButton, firebaseImage;
    StorageReference mstorage;
    ProgressDialog mprogress;
    Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Firebase.setAndroidContext(this);
        ref = new Firebase("https://androidfirebase-724d9.firebaseio.com/");
        mstorage = FirebaseStorage.getInstance().getReference();
        cameraButton = (Button) findViewById(R.id.CameraButton);
        ViewButton = (Button) findViewById(R.id.RetrieveImage);
        firebaseImage = (Button) findViewById(R.id.firebaseimage);
        cameraButton.setOnClickListener(this);
        ViewButton.setOnClickListener(this);
        firebaseImage.setOnClickListener(this);
        mprogress = new ProgressDialog(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.CameraButton:
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, CAMERA_REQUEST_CODE);
                break;

            case R.id.RetrieveImage:
                String s = ViewButton.getText().toString();
                Data d = new Data();
                d.setImag(s);
                startActivity(new Intent(this, ViewImage.class));
                break;

            case R.id.firebaseimage:
                startActivity(new Intent(this, FireBaseImage.class));
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            mprogress.setMessage("Uploading");
            mprogress.show();
//            Uri uri = data.getData();
//            StorageReference filepath = mstorage.child("photos/").child(uri.getLastPathSegment());
//            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                    mprogress.dismiss();
//                    Toast.makeText(getApplicationContext(), "Uploading finished", Toast.LENGTH_SHORT).show();
//                }
//            });
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            byte[] b = stream.toByteArray();
            String b64Image = Base64.encodeToString(b, Base64.DEFAULT);
            //imageView.setImageBitmap(photo);
            ref.child("Photo").push().setValue(b64Image, new Firebase.CompletionListener() {

                @Override
                public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                    if (firebaseError != null) {
                        Toast.makeText(getApplicationContext(), firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Image Saved", Toast.LENGTH_SHORT).show();
                        mprogress.dismiss();
                    }
                }
            });
            // startActivity(new Intent(this, ViewImage.class));
        }
    }
}
