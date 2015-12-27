package college.edu.tomer.fragmentsdemo;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class TakePictureFragment extends Fragment implements OnPictureTaken {
    public static final int ACTION_TAKEPICTURE = 0;
    @Bind(R.id.ivTakePicture)
    ImageView ivTakePicture;
    @Bind(R.id.btnTakePicture)
    Button btnTakePicture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_take_picture, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.btnTakePicture)
    void takePicture(View btn) {
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            doTakePicture();
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    ACTION_TAKEPICTURE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == ACTION_TAKEPICTURE) {
            if (permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED){
                doTakePicture();
            }
            else {

            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void doTakePicture() {
        Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        System.out.println(path);

        try {
            File myFile = File.createTempFile("123", ".jpg", path);

            camIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(myFile));
            MainActivity a = (MainActivity) getActivity();
            a.setListener(this);

            getActivity().startActivityForResult(camIntent, ACTION_TAKEPICTURE);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void pictureTaken() {
        File path = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        System.out.println(path);

        try {
            File myFile = File.createTempFile("123", ".jpg", path);
            Picasso.with(getActivity()).load(myFile).into(ivTakePicture);
        }
        catch (Exception e){

        }
    }
}
