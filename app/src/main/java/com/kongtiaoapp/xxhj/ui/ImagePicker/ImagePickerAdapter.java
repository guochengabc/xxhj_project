package com.kongtiaoapp.xxhj.ui.ImagePicker;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.kongtiaoapp.xxhj.R;

import java.util.List;

/**
 * ================================================ 作 者：ikkong
 * （ikkong@163.com），修改 jeasonlzy（廖子尧） 版 本：1.0 创建日期：2016/5/19 描 述：
 * 修订历史：微信图片选择的Adapter, 感谢 ikkong 的提交
 * ================================================
 */
public class ImagePickerAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private int maxImgCount;
	private Context mContext;
	private List<ImageItem> mData;
	private OnMyItemClickListener listener;
	private LayoutInflater mInflater;
	private boolean isAdded; // 是否额外添加了最后一个图片
	private int clickPosition;

	public void setImages(List<ImageItem> data) {
//		mData = new ArrayList<>(data);
//		if (getCount() < maxImgCount) {
//			mData.add(new ImageItem());
//			isAdded = true;
//		} else {
//			isAdded = false;
//		}
//		this.notifyDataSetChanged();
	}

	public List<ImageItem> getImages() {
		// 由于图片未选满时，最后一张显示添加图片，因此这个方法返回真正的已选图片
//		if (isAdded)
//			return new ArrayList<>(mData.subList(0, mData.size() - 1));
//		else
			return mData;
	}

	public ImagePickerAdapter(Context mContext, List<ImageItem> data,
                              int maxImgCount, List<ImageItem> mData) {
		this.mContext = mContext;
		this.maxImgCount = maxImgCount;
		this.mInflater = LayoutInflater.from(mContext);
		this.mData =mData;
//		setImages(data);
	}

	public void update() {
	}

	public int getCount() {
		if (mData.size() < maxImgCount) {
			isAdded = true;
			return mData.size() + 1;
		} else {
			isAdded = false;
			return mData.size();
		}

//		return mData.size();
	}

	public Object getItem(int arg0) {
		return mData.get(arg0);
	}

	public long getItemId(int arg0) {
		return arg0;
	}

	public void setSelectedPosition(int position) {
	}

	public int getSelectedPosition() {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_published_grida,
					parent, false);
			holder = new ViewHolder();
			holder.image = (ImageView) convertView
					.findViewById(R.id.item_grida_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// 根据条目位置设置图片

		if (isAdded && position ==getCount()-1) {
			holder.image.setImageResource(R.drawable.selector_image_add);
			//clickPosition = PublishActivity.IMAGE_ITEM_ADD;
			// 设置条目的点击事件
			holder.image.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (listener != null){
						//listener.onItemClick(v, PublishActivity.IMAGE_ITEM_ADD);
					}

				}
			});
		} else {
			ImageItem item = mData.get(position);
			ImagePicker
					.getInstance()
					.getImageLoader()
					.displayImage((Activity) mContext, item.path, holder.image,
							0, 0);
			clickPosition = position;
			// 设置条目的点击事件
			holder.image.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if (listener != null)
						listener.onItemClick(v, position);
				}
			});
		}
//		if (isAdded && position == getCount() - 1&&getCount() == 2) {
//			holder.image.setImageResource(R.drawable.selector_image_add);
//			clickPosition = PublishActivity.IMAGE_ITEM_ADD;
//			// 设置条目的点击事件
//			holder.image.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					if (listener != null)
//						listener.onItemClick(v, PublishActivity.IMAGE_ITEM_ADD);
//				}
//			});
//		}

		return convertView;
	}

	public class ViewHolder {
		public ImageView image;
	}

	public interface OnMyItemClickListener {
		void onItemClick(View view, int position);
	}

	public void setOnItemClickListener(OnMyItemClickListener listener) {
		this.listener = listener;
	}

//	@Override
//	public void onClick(View v) {
//		if (listener != null)
//			listener.onItemClick(v, clickPosition);
//	}
}