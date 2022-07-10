package com.easyandroid.widgets.imageview.preview.previewlibrary.view;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyandroid.widgets.R;
import com.easyandroid.widgets.imageview.preview.photoview.PhotoViewAttacher;
import com.easyandroid.widgets.imageview.preview.previewlibrary.EasyPreviewActivity;
import com.easyandroid.widgets.imageview.preview.previewlibrary.EasyPreviewVideoPlayerActivity;
import com.easyandroid.widgets.imageview.preview.previewlibrary.ZoomMediaLoader;
import com.easyandroid.widgets.imageview.preview.previewlibrary.entity.IThumbViewInfo;
import com.easyandroid.widgets.imageview.preview.previewlibrary.loader.MySimpleTarget;
import com.easyandroid.widgets.imageview.preview.previewlibrary.loader.VideoClickListener;
import com.easyandroid.widgets.imageview.preview.previewlibrary.widget.SmoothImageView;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;

/**
 * package: com.easyandroid.widgets.imageview.preview.previewlibrary.view.BasePhotoFragment
 * author: gyc
 * description:
 * time: create at 2022/7/10 1:51
 */
public class BasePhotoFragment extends Fragment {
    /**
     * 预览图片 类型
     */
    private static final String KEY_TRANS_PHOTO = "is_trans_photo";
    private static final String KEY_SING_FILING = "isSingleFling";
    private static final String KEY_PATH = "key_item";
    private static final String KEY_DRAG = "isDrag";
    private static final String KEY_SEN = "sensitivity";
    private IThumbViewInfo beanViewInfo;
    private boolean isTransPhoto = false;
    protected SmoothImageView imageView;
    protected View rootView;
    protected View loading;
    protected MySimpleTarget mySimpleTarget;
    protected View btnVideo;
    public static VideoClickListener listener;

    public static BasePhotoFragment getInstance(Class<? extends BasePhotoFragment> fragmentClass,
                                                IThumbViewInfo item,
                                                boolean currentIndex,
                                                boolean isSingleFling,
                                                boolean isDrag,
                                                float sensitivity) {
        BasePhotoFragment fragment;
        try {
            fragment = fragmentClass.newInstance();
        } catch (Exception e) {
            fragment = new BasePhotoFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable(BasePhotoFragment.KEY_PATH, item);
        bundle.putBoolean(BasePhotoFragment.KEY_TRANS_PHOTO, currentIndex);
        bundle.putBoolean(BasePhotoFragment.KEY_SING_FILING, isSingleFling);
        bundle.putBoolean(BasePhotoFragment.KEY_DRAG, isDrag);
        bundle.putFloat(BasePhotoFragment.KEY_SEN, sensitivity);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.easypreview_fragment_image_photo_layout, container, false);
    }

    @CallSuper
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    @CallSuper
    @Override
    public void onStop() {
        ZoomMediaLoader.getInstance().getLoader().onStop(this);
        super.onStop();
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        release();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ZoomMediaLoader.getInstance().getLoader().clearMemory(getActivity());
        if (getActivity() != null && getActivity().isFinishing()) {
            listener = null;
        }
    }

    public void release() {
        isTransPhoto = false;
    }

    /**
     * 初始化控件
     */
    private void initView(View view) {
        loading = view.findViewById(R.id.loading);
        imageView = view.findViewById(R.id.photoView);
        btnVideo = view.findViewById(R.id.btnVideo);
        rootView = view.findViewById(R.id.rootView);
        rootView.setDrawingCacheEnabled(false);
        imageView.setDrawingCacheEnabled(false);
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String video = beanViewInfo.getVideoUrl();
                if (video != null && !video.isEmpty()) {
                    if (listener != null) {
                        listener.onPlayerVideo(video);
                    } else {
                        EasyPreviewVideoPlayerActivity.startActivity(getContext(), video);
                    }
                }

            }
        });
        mySimpleTarget = new MySimpleTarget() {

            @Override
            public void onResourceReady() {
                loading.setVisibility(View.GONE);
                String video = beanViewInfo.getVideoUrl();
                if (video != null && !video.isEmpty()) {
                    btnVideo.setVisibility(View.VISIBLE);
                    ViewCompat.animate(btnVideo).alpha(1).setDuration(1000).start();
                } else {
                    btnVideo.setVisibility(View.GONE);
                }
            }

            @Override
            public void onLoadFailed(Drawable errorDrawable) {
                loading.setVisibility(View.GONE);
                btnVideo.setVisibility(View.GONE);
                if (errorDrawable != null) {
                    imageView.setImageDrawable(errorDrawable);
                }
            }
        };
    }

    /**
     * 初始化数据
     */
    private void initData() {
        Bundle bundle = getArguments();
        boolean isSingleFling = true;
        // 非动画进入的Fragment，默认背景为黑色
        if (bundle != null) {
            isSingleFling = bundle.getBoolean(KEY_SING_FILING);
            //地址
            beanViewInfo = bundle.getParcelable(KEY_PATH);
            //位置
            assert beanViewInfo != null;
            imageView.setDrag(bundle.getBoolean(KEY_DRAG), bundle.getFloat(KEY_SEN));
            imageView.setThumbRect(beanViewInfo.getBounds());
            rootView.setTag(beanViewInfo.getUrl());
            //是否展示动画
            isTransPhoto = bundle.getBoolean(KEY_TRANS_PHOTO, false);
            if (beanViewInfo.getUrl().toLowerCase().contains(".gif")) {
                imageView.setZoomable(false);
                //加载图
                ZoomMediaLoader.getInstance().getLoader().displayGifImage(this, beanViewInfo.getUrl(), imageView, mySimpleTarget);
            } else {
                //加载图
                ZoomMediaLoader.getInstance().getLoader().displayImage(this, beanViewInfo.getUrl(), imageView, mySimpleTarget);
            }

        }
        if (!isTransPhoto) {
            rootView.setBackgroundColor(Color.BLACK);
        } else {
            imageView.setMinimumScale(0.7f);
        }
        if (isSingleFling) {
            imageView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                @Override
                public void onViewTap(View view, float x, float y) {

                }
            });
            imageView.setOnViewTapListener(new PhotoViewAttacher.OnViewTapListener() {
                @Override
                public void onViewTap(View view, float x, float y) {
                    if (imageView.checkMinScale()) {
                        ((EasyPreviewActivity) getActivity()).transformOut();
                    }
                }
            });
        } else {
            imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    if (imageView.checkMinScale()) {
                        ((EasyPreviewActivity) getActivity()).transformOut();
                    }
                }

                @Override
                public void onOutsidePhotoTap() {

                }

            });
        }
        imageView.setAlphaChangeListener(new SmoothImageView.OnAlphaChangeListener() {
            @Override
            public void onAlphaChange(int alpha) {
                if (alpha == 255) {
                    String video = beanViewInfo.getVideoUrl();
                    if (video != null && !video.isEmpty()) {
                        btnVideo.setVisibility(View.VISIBLE);
                    } else {
                        btnVideo.setVisibility(View.GONE);
                    }
                } else {
                    btnVideo.setVisibility(View.GONE);
                }
                rootView.setBackgroundColor(getColorWithAlpha(alpha / 255f, Color.BLACK));

            }
        });
        imageView.setTransformOutListener(new SmoothImageView.OnTransformOutListener() {
            @Override
            public void onTransformOut() {
                ((EasyPreviewActivity) getActivity()).transformOut();
            }
        });
    }

    public static int getColorWithAlpha(float alpha, int baseColor) {
        int a = Math.min(255, Math.max(0, (int) (alpha * 255))) << 24;
        int rgb = 0x00ffffff & baseColor;
        return a + rgb;
    }

    public void transformIn() {
        assert imageView != null;
        imageView.transformIn(new SmoothImageView.onTransformListener() {
            @Override
            public void onTransformCompleted(SmoothImageView.Status status) {
                rootView.setBackgroundColor(Color.BLACK);
            }
        });
    }

    public void transformOut(SmoothImageView.onTransformListener listener) {
        assert imageView != null;
        imageView.transformOut(listener);
    }


    public void changeBg(int color) {
        ViewCompat.animate(btnVideo).alpha(0).setDuration(SmoothImageView.getDuration()).start();
        rootView.setBackgroundColor(color);
    }

    public IThumbViewInfo getBeanViewInfo() {
        return beanViewInfo;
    }
}