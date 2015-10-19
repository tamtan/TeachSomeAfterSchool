package com.example.pc.teachsomeafterschool.Student;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pc.teachsomeafterschool.Infra.Const;
import com.example.pc.teachsomeafterschool.Infra.Util;
import com.example.pc.teachsomeafterschool.Model.ClassModel;
import com.example.pc.teachsomeafterschool.Model.Student;
import com.example.pc.teachsomeafterschool.Model.Tuition;
import com.example.pc.teachsomeafterschool.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by pc on 10/5/2015.
 */
public class StudentListAdapter extends ArrayAdapter<Student> {
    private com.nostra13.universalimageloader.core.ImageLoader imageLoader;
    DisplayImageOptions options;
    Context context;
    int resource;
    ArrayList<Student> objects;
    ClassModel classModel;
    ArrayList<Tuition> tuis;
    public StudentListAdapter(Context context, int resource, ArrayList<Student> objects, ClassModel classModel) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
        this.classModel = classModel;
        imageLoader = ImageLoader.getInstance();

        imageLoader.init(ImageLoaderConfiguration
                .createDefault(context));
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.avatar) // resource or drawable
                .showImageForEmptyUri(R.drawable.avatar) // resource or drawable
                .showImageOnFail(R.drawable.avatar)
                .bitmapConfig(Bitmap.Config.ARGB_8888).cacheInMemory(false)
                .considerExifParams(true).build();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, null);
            holder.tvstudent_name = (TextView) convertView.findViewById(R.id.tvstudent_name);
            holder.tvmonth_in_debt = (TextView) convertView.findViewById(R.id.tvmonth_in_debt);
            holder.tvphone = (TextView) convertView.findViewById(R.id.tvphone);
            holder.imgavatar = (ImageView) convertView.findViewById(R.id.imgavatar);
            holder.tvreal_class_name = (TextView) convertView.findViewById(R.id.tvreal_class_name);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvstudent_name.setText(objects.get(position).getFullName());
        holder.tvphone.setText(objects.get(position).getPhone());
        holder.tvreal_class_name.setText(objects.get(position).getOfficialClass());
        ImageSize targetSize = new ImageSize(70, 70);
        imageLoader.loadImage("file://" + objects.get(position).getImageUrl(), targetSize, options,
                new SimpleImageLoadingListener() {
                    ProgressDialog dialog = new ProgressDialog(context);

                    @Override
                    public void onLoadingStarted(String imageUri,
                                                 View view) {

                        dialog.show();

                    }

                    @Override
                    public void onLoadingFailed(String imageUri,
                                                View view, FailReason failReason) {
//                             TODO Auto-generated method stub
                        super.onLoadingFailed(imageUri, view,
                                failReason);
                        dialog.dismiss();


                    }

                    @Override
                    public void onLoadingComplete(String imageUri,
                                                  View view1, Bitmap loadedImage) {
                        dialog.dismiss();
                        holder.imgavatar.setImageBitmap(loadedImage);

                    }
                });
//        holder.imgavatar.setImageBitmap(Util.getBitmapFromGallery(this.context, objects.get(position).getImageUrl()));
//        tuis= calculateMonthlyPaymentToTuitionArray(Integer.toString(classModel.getTuition()));
//        holder.tvmonth_in_debt.setText(Integer.toString(Util.calculateTotalDebt(tuis, classModel.getStartingTime(), Util.getCurrentMonth())));
        return convertView;
    }
    public ArrayList<Tuition> calculateMonthlyPaymentToTuitionArray(String monthlyPayment, String startingMonth, String currentMonth) {
        ArrayList<Tuition> result = new ArrayList<Tuition>();
        Tuition tui;
        String s;
        String[] monthlyPaymentStr = monthlyPayment.split("_");
        int intStartingMonth = Integer.valueOf(startingMonth);
        int intCurrentMonth = Integer.valueOf(currentMonth);
        for (int i =  0; i < 12; i++) {
            tui = new Tuition();
            s = monthlyPaymentStr[i];
            tui.setMonth(s.split(":")[0]);

            if ((Integer.valueOf(s.split(":")[1]) == -1)&& (intStartingMonth-2<i)) {
                tui.setIsPay(Const.NO);
            } else {
                tui.setIsPay(Integer.valueOf(s.split(":")[1]));
            }
            result.add(tui);
        }
        return result;
    }

    class ViewHolder {
        ImageView imgavatar;
        TextView tvstudent_name, tvreal_class_name, tvmonth_in_debt, tvphone;
    }

    public Bitmap getBitmapFromGallery(final Context context, String imageUrl) {

        ImageSize targetSize = new ImageSize(200, 200);
        final Bitmap[] result = {null};
        imageLoader.loadImage("file://"+imageUrl, targetSize, options,
                new SimpleImageLoadingListener() {
                    ProgressDialog dialog = new ProgressDialog(context);
                    @Override
                    public void onLoadingStarted(String imageUri,
                                                 View view) {

                        dialog.show();

                    }

                    @Override
                    public void onLoadingFailed(String imageUri,
                                                View view, FailReason failReason) {
//                             TODO Auto-generated method stub
                        super.onLoadingFailed(imageUri, view,
                                failReason);
                        dialog.dismiss();


                    }

                    @Override
                    public void onLoadingComplete(String imageUri,
                                                  View view1, Bitmap loadedImage) {
                        dialog.dismiss();
                        result[0] = loadedImage;
//                        image.setImageBitmap(loadedImage);

                    }
                });
        return result[0];
    }

}
