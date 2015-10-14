package com.example.pc.teachsomeafterschool.Student;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pc.teachsomeafterschool.Infra.Const;
import com.example.pc.teachsomeafterschool.Model.Student;
import com.example.pc.teachsomeafterschool.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.IOException;

/**
 * Created by TAM on 06-Oct-15.
 */
@EActivity(R.layout.student_info)
public class StudentInfoActivity extends Activity {
    private static final int REQUEST_LOAD_IMAGE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 2;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 3;
    @ViewById
    EditText edtStudent_class, edtStudent_full_name, edtStudent_phone_number, edtStudent_real_class, edtStudent_school, edtStudent_address;
    @ViewById
    CheckBox cbMale, cbFemale;
    @ViewById
    TextView tvAvatar;
    @ViewById
    ImageView imgBack, imgOk, imgAvatar;

    Student studentModel;
    String avatarURL;
    ImageRecommendDialog imageDig;
    Uri imageUri = null;
    DisplayImageOptions options;
    private ImageLoader imageLoader;

    public static String convertImageUriToFile(Uri imageUri, Activity activity) {
        Cursor cursor = null;
        int imageID = 0;
        try {
            /*********** Which columns values want to get *******/
            String[] proj = {
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.Thumbnails._ID,
                    MediaStore.Images.ImageColumns.ORIENTATION
            };

            cursor = activity.managedQuery(

                    imageUri,         //  Get data for specific image URI
                    proj,             //  Which columns to return
                    null,             //  WHERE clause; which rows to return (all rows)
                    null,             //  WHERE clause selection arguments (none)
                    null              //  Order-by clause (ascending by name)

            );
            int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
            int columnIndexThumb = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);
            int file_ColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            //int orientation_ColumnIndex = cursor.
            //    getColumnIndexOrThrow(MediaStore.Images.ImageColumns.ORIENTATION);

            int size = cursor.getCount();

            /*******  If size is 0, there are no images on the SD Card. *****/

            if (size == 0) {
            } else {

                int thumbID = 0;
                if (cursor.moveToFirst()) {

                    /**************** Captured image details ************/

                    /*****  Used to show image on view in LoadImagesFromSDCard class ******/
                    imageID = cursor.getInt(columnIndex);

                    thumbID = cursor.getInt(columnIndexThumb);

                    String Path = cursor.getString(file_ColumnIndex);

                    //String orientation =  cursor.getString(orientation_ColumnIndex);

                    String CapturedImageDetails = " CapturedImageDetails : \n\n"
                            + " ImageID :" + imageID + "\n"
                            + " ThumbID :" + thumbID + "\n"
                            + " Path :" + Path + "\n";

                    // Show Captured Image detail on activity
                    //tvAvatar.setText( CapturedImageDetails );

                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        // Return Captured Image ImageID ( By this ImageID Image will load from sdcard )

        return "" + imageID;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        imageLoader.getInstance().init(config);
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.avatar) // resource or drawable
                .showImageForEmptyUri(R.drawable.avatar) // resource or drawable
                .showImageOnFail(R.drawable.avatar)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        studentModel = new Student();
        cbMale.setChecked(true);
        imageDig = new ImageRecommendDialog(this);
    }

    @Click({R.id.cbFemale, R.id.cbMale, R.id.tvAvatar, R.id.imgBack, R.id.imgOk})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.cbMale:
                if (!cbMale.isChecked()) {
                    cbMale.setChecked(true);
                }
                cbFemale.setChecked(false);
                studentModel.setSex(Const.MALE);
                break;

            case R.id.cbFemale:
                if (!cbFemale.isChecked()) {
                    cbFemale.setChecked(true);
                }
                cbMale.setChecked(false);
                studentModel.setSex(Const.FEMALE);
                break;
            case R.id.imgBack:
                onBackPressed();
                break;
            case R.id.imgOk:
                if (isInputDataOk()) {
                    SaveData();
                } else {
                    Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.tvAvatar:

                imageDig.show();
        }
    }

    private boolean isInputDataOk() {
        boolean result = true;
        if (edtStudent_full_name.getText().toString().isEmpty()) {
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
            edtStudent_full_name.setFocusable(true);
            result = false;
        } else if (edtStudent_real_class.getText().toString().isEmpty()) {
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
            edtStudent_real_class.setFocusable(true);
            result = false;
        } else if (edtStudent_school.getText().toString().isEmpty()) {
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
            edtStudent_school.setFocusable(true);
            result = false;
        } else if (edtStudent_phone_number.getText().toString().split(" ").length == 0) {
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
            edtStudent_phone_number.setFocusable(true);
            result = false;
        } else if (edtStudent_address.getText().toString().split(" ").length == 0) {
            Toast.makeText(this, "error", Toast.LENGTH_LONG).show();
            edtStudent_address.setFocusable(true);
            result = false;
        }

        return result;
    }

    private void SaveData() {
        studentModel.setFull_name(edtStudent_full_name.getText().toString());
        studentModel.setAdd(edtStudent_address.getText().toString());
        studentModel.setOfficial_class(edtStudent_real_class.getText().toString());
        studentModel.setPhone(edtStudent_phone_number.getText().toString());
        studentModel.setSchool(edtStudent_school.getText().toString());

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            imageLoader.displayImage(selectedImage.getEncodedPath(), imgAvatar);
//            String[] filePathColumn = {MediaStore.Images.Media.DATA};
//
//            Cursor cursor = getContentResolver().query(selectedImage,
//                    filePathColumn, null, null, null);
//            cursor.moveToFirst();
//
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String picturePath = cursor.getString(columnIndex);
//            cursor.close();
//            imgAvatar.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//            imgAvatar.setBackgroundColor(getResources().getColor(R.color.gray));
//            studentModel.setImage_url(picturePath);
        }

        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            /*********** Load Captured Image And Data Start ****************/

            String imageId = convertImageUriToFile(imageUri, StudentInfoActivity.this);


            //  Create and excecute AsyncTask to load capture image

            new LoadImagesFromSDCard().execute("" + imageId);

            /*********** Load Captured Image And Data End ****************/


        } else if (resultCode == RESULT_CANCELED) {

            Toast.makeText(this, " Picture was not taken ", Toast.LENGTH_SHORT).show();
        } else {

            Toast.makeText(this, " Picture was not taken ", Toast.LENGTH_SHORT).show();
        }
    }

    class ImageRecommendDialog extends Dialog implements View.OnClickListener {
        Context context;
        TextView tvGallery, tvCamera;

        public ImageRecommendDialog(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.image_chooser_dialog);
            tvGallery = (TextView) findViewById(R.id.tvGallery);
            tvCamera = (TextView) findViewById(R.id.tvCamera);
            tvGallery.setOnClickListener(this);
            tvCamera.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.tvGallery) {
                imageDig.dismiss();
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, REQUEST_LOAD_IMAGE);
            } else if (v.getId() == R.id.tvCamera) {
                imageDig.dismiss();
                String fileName = "Camera_Example.jpg";
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, fileName);
                values.put(MediaStore.Images.Media.DESCRIPTION, "Image capture by camera");
                imageUri = getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                /**** EXTERNAL_CONTENT_URI : style URI for the "primary" external storage volume. ****/
                // Standard Intent action that can be sent to have the camera
                // application capture an image and return it.

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        }

    }

    public class LoadImagesFromSDCard extends AsyncTask<String, Void, Void> {
        Bitmap mBitmap;
        private ProgressDialog Dialog = new ProgressDialog(StudentInfoActivity.this);

        protected void onPreExecute() {
            Dialog.setMessage(" Loading image from Sdcard..");
            Dialog.show();
        }

        // Call after onPreExecute method
        protected Void doInBackground(String... urls) {
            Bitmap bitmap = null;
            Bitmap newBitmap = null;
            Uri uri = null;


            try {

                /**  Uri.withAppendedPath Method Description
                 * Parameters
                 *    baseUri  Uri to append path segment to
                 *    pathSegment  encoded path segment to append
                 * Returns
                 *    a new Uri based on baseUri with the given segment appended to the path
                 */

                uri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "" + urls[0]);

                /**************  Decode an input stream into a bitmap. *********/
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));

                if (bitmap != null) {

                    /********* Creates a new bitmap, scaled from an existing bitmap. ***********/

                    newBitmap = Bitmap.createScaledBitmap(bitmap, 170, 170, true);

                    bitmap.recycle();

                    if (newBitmap != null) {
                        mBitmap = newBitmap;
                    }
                }
            } catch (IOException e) {
                // Error fetching image, try to recover

                /********* Cancel execution of this task. **********/
                cancel(true);
            }
//            ImageLoader imageLoader = ImageLoader.getInstance();
//            imageLoader.displayImage(url, imageView, options);
            return null;
        }


        protected void onPostExecute(Void unused) {
            Dialog.dismiss();
            if (mBitmap != null) {
                //imgAvatar.setImageBitmap(mBitmap);
            }

        }
    }

}


