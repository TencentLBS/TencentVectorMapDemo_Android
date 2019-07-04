package com.example.tencentmap.tencentmapdemo.utils;

import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tencentmap.tencentmapdemo.R;
import com.example.tencentmap.tencentmapdemo.basic.SupportMapFragmentActivity;
import com.google.gson.Gson;
import com.tencent.tencentmap.mapsdk.maps.Projection;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.TencentMap.OnMapLongClickListener;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.model.Polyline;
import com.tencent.tencentmap.mapsdk.maps.model.VisibleRegion;

public class CoordinateActivity extends SupportMapFragmentActivity implements OnMapLongClickListener, TencentMap.OnMapClickListener {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = findViewById(R.id.tv_info);
        textView.setVisibility(View.VISIBLE);
        tencentMap.setOnMapClickListener(this);
        tencentMap.setOnMapLongClickListener(this);
    }

    /**
     * 点击地图，显示对应点击点的屏幕坐标
     * @param latLng
     */
    @Override
    public void onMapLongClick(LatLng latLng) {
        Projection projection = tencentMap.getProjection();
        Point screen = projection.toScreenLocation(latLng);
        textView.setText("屏幕坐标：" + new Gson().toJson(screen));
    }


    /**
     * 长点击地图， 显示当前地图的坐标范围
     * @param latLng
     */
    @Override
    public void onMapClick(LatLng latLng) {
        Projection projection = tencentMap.getProjection();
        VisibleRegion region = projection.getVisibleRegion();
        textView.setText("当前地图视野的经纬度：" + new Gson().toJson(region));
    }
}
