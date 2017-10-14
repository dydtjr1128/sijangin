package org.androidtown.sijang;


import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MenuInfo extends Activity implements OnMapReadyCallback{
  //kkh
    GoogleMap gmap;
    MarkerOptions markerOptions3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.menu_info);
        markerOptions3 = new MarkerOptions();
        Intent intent = getIntent();
        String data = intent.getStringExtra("SubMenuSelect");

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //TextView tv = (TextView)findViewById(R.id.textview3);
        //tv.setText("위치나오는곳" + data);

    }

    public void onMapReady(final GoogleMap map){

        gmap = map;

        double a = 37.57;
        double b = 126.97;
        LatLng SEOUL = new LatLng(37.56,126.97);
        LatLng SEOUL2 = new LatLng(a,b);
        double c = SEOUL2.latitude ;

        final MarkerOptions markerOptions = new MarkerOptions();
        MarkerOptions marketOptions2 = new MarkerOptions();

        markerOptions.position(SEOUL);
        marketOptions2.position(SEOUL2);


        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");

        marketOptions2.title("서울2");
        marketOptions2.snippet("한국의 수도2");


        map.addMarker(markerOptions).showInfoWindow();
        map.addMarker(marketOptions2).showInfoWindow();

        Log.d("aaa", "aaa");

        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                map.clear();

                //markerOptions3.icon(Bit);
                markerOptions3.position(latLng);

                map.addMarker(markerOptions3);
                System.out.println("레티튜드 : >>>> " + latLng.latitude);
                System.out.println("롱티튜드 : >>>> " + latLng.longitude);

            }

            });
        map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL2));
        map.animateCamera(CameraUpdateFactory.zoomTo(17));

        // 맵 스크롤(드래그) 안되게 막는거
        //map.getUiSettings().setScrollGesturesEnabled(false);
        map.getUiSettings().setAllGesturesEnabled(false);
    }


    //확인 버튼 클릭 , 액티비티 닫기
    public void mOnClose(View v){
        finish();
    }

    //액티비티 이벤트
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return false;
    }

    //백버튼 막기
    public void onBackPressed(){
        return;
    }
}