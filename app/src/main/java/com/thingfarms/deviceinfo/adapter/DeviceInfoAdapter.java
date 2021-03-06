package com.thingfarms.deviceinfo.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.thingfarms.deviceinfo.R;
import com.thingfarms.deviceinfo.model.DeviceInfoData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DeviceInfoAdapter extends RecyclerView.Adapter<DeviceInfoAdapter.ViewHolder> {
    private List<DeviceInfoData> deviceInfoDataList = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(deviceInfoDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return deviceInfoDataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail)
        TextView thumbnail;
        @BindView(R.id.viewSwitcher)
        ViewSwitcher viewSwitcher;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.dev_detail)
        TextView detailInfo;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(DeviceInfoData user) {
            thumbnail.setTextColor(randomColor());
            thumbnail.setText(String.valueOf(user.getInfoName().toUpperCase().charAt(0)));
            name.setText(user.getInfoName());
            detailInfo.setText(user.getInfoDetails());
            viewSwitcher.setDisplayedChild(0);
        }
    }

    public void setData(List<DeviceInfoData> data) {
        deviceInfoDataList.addAll(data);
        notifyDataSetChanged();
    }

    public void updateData(DeviceInfoData user) {
        deviceInfoDataList.set(deviceInfoDataList.indexOf(user), user);
        notifyItemChanged(deviceInfoDataList.indexOf(user));
    }

    private int randomColor() {
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
