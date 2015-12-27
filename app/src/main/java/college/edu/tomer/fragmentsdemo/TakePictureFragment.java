package college.edu.tomer.fragmentsdemo;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

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
        Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //camIntent.putExtra(MediaStore.EXTRA_OUTPUT, )
        MainActivity a = (MainActivity) getActivity();
         a.setListener(this);

        getActivity().startActivityForResult(camIntent, ACTION_TAKEPICTURE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void pictureTaken(Bitmap image) {
        ivTakePicture.setImageBitmap(image);
    }
}
