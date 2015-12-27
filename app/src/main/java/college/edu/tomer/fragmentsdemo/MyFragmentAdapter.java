package college.edu.tomer.fragmentsdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by master on 27/12/15.
 */
public class MyFragmentAdapter extends FragmentStatePagerAdapter {
    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TakePictureFragment();
            case 1:
                return new AboutFragment();
            case 2:
                return new RatingFragment();
            case 3:
                return new AboutFragment();
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int getCount() {
        return 4;
    }
}
