package org.tutorial.samplefragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ListFragment.ImageSelectionCallback{
    int[] images = {R.drawable.profile1 , R.drawable.image , R.drawable.image2};

    ListFragment listFragment;
    ViewFragment viewFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager manager= (FragmentManager)getSupportFragmentManager();
        listFragment = (ListFragment)manager.findFragmentById(R.id.listFragment);
        viewFragment = (ViewFragment)manager.findFragmentById(R.id.viewFragment);

    }

    @Override
    public void onImageSelected(int position) {
        viewFragment.setImage(images[position]);
    }
}