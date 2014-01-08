package com.antonyt.infiniteviewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class InfiniteViewPagerActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        PagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            int[] colours = new int[]{Color.CYAN, Color.GRAY, Color.MAGENTA, Color.LTGRAY, Color.GREEN, Color.WHITE,
                    Color.YELLOW};

            @Override
            public int getCount() {
                return colours.length;
            }

            @Override
            public Fragment getItem(int position) {
                Fragment fragment = new ColourFragment();
                Bundle args = new Bundle();
                args.putInt("colour", colours[position]);
                args.putInt("identifier", position);
                fragment.setArguments(args);
                return fragment;
            }
        };

        // wrap pager to provide infinite paging with wrap-around
        PagerAdapter wrappedAdapter = new InfinitePagerAdapter(new ImagePagerAdapter());

        // actually an InfiniteViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(wrappedAdapter);
        viewPager.setPageMargin(-200);
        viewPager.setOffscreenPageLimit(6);
//        viewPager.setCurrentItem(I)

    }
    
	private class ImagePagerAdapter extends PagerAdapter {

		int[] mImages = {
			R.drawable.icon,
			R.drawable.icon,
			R.drawable.icon,
			R.drawable.icon,
			R.drawable.icon,
			R.drawable.icon,
			R.drawable.icon,
			R.drawable.icon
		};
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public int getCount() {
			return mImages.length;
		}

		@Override
		public Object instantiateItem(ViewGroup view, int position) {
			View imageLayout = LayoutInflater.from(InfiniteViewPagerActivity.this).inflate(R.layout.screenshot_item, view, false);
			ImageView imageView = (ImageView) imageLayout.findViewById(R.id.si_image);
			
			imageView.setBackgroundResource(mImages[position]);
			
		   view.addView(imageLayout, 0);
			return imageLayout;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public void restoreState(Parcelable state, ClassLoader loader) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}
	}

    
}