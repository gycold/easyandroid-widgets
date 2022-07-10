# EasyPreview

 ### 特点
   * 1.支持自定义图片加载框架。
   * 2.支持重写activity,完成切换切换效果。
   * 3.图片查看 缩放 拖拽下拉缩小退出。
   * 4.支持自定义activity,Fragment。
   * 5.支持视频和自定义视频播放控件。 
   * 6.支持类似微信朋友圈照片九宫格和微信聊天图片预览。
   * 7.指示器类型选择 [圆点模式(贝塞尔圆点指示器)](https://github.com/yanyiqun001/bannerDot)和数字模式。
   * 8.增加接口实体类。不在使用数据转化。
   * 8.支持GIF显示。

####效果如下：
![](gif/test.gif)


### 示例代码
```
    @Override
      public void onCreate() {
          super.onCreate();
          ZoomMediaLoader.getInstance().init(new TestImageLoader());
      }
```
 1.使用方式
```
     EasyPreviewBuilder.from(GridViewCustomActivity.this)//activity实例必须
                            .to(CustomActivity.class)//自定义Activity 使用默认的预览不需要
                            .setData(mThumbViewInfoList)//集合
                            .setUserFragment(UserFragment.class)//自定义Fragment 使用默认的预览不需要
                            .setCurrentIndex(position)
                            .setSingleFling(false)//是否在黑屏区域点击返回
                            .setDrag(false)//是否禁用图片拖拽返回  
                            .setType(EasyPreviewBuilder.IndicatorType.Dot)//指示器类型
                            .start();//启动            

```
2.列表控件item点击事件添加相应代码。
(RecyclerView为例，demo有(ListView和GridView和九宫格控件实例代码))
```
     mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
              //在你点击时，调用computeBoundsBackward（）方法
                computeBoundsBackward(mGridLayoutManager.findFirstVisibleItemPosition());
              EasyPreviewBuilder.from(RecycleViewActivity.this)
                                .setData(mThumbViewInfoList)
                                .setCurrentIndex(position)
                                .setType(EasyPreviewBuilder.IndicatorType.Number)
                                .start();
            }
        });
    /**
     ** 查找信息
     * 从第一个完整可见item逆序遍历，如果初始位置为0，则不执行方法内循环
     */
    private void computeBoundsBackward(int firstCompletelyVisiblePos) {
        for (int i = firstCompletelyVisiblePos;i < mThumbViewInfoList.size(); i++) {
            View itemView = mGridLayoutManager.findViewByPosition(i);
            Rect bounds = new Rect();
            if (itemView != null) {
                ImageView thumbView = (ImageView) itemView.findViewById(R.id.iv);
                thumbView.getGlobalVisibleRect(bounds);
            }
            mThumbViewInfoList.get(i).setBounds(bounds);
        }
    }
````
  2.构造实体类： 你的实体类实现IThumbViewInfo接口 
  >> **注意：IThumbViewInfo 实现 Parcelable 接口 注意序列化化**
 ```
 public class UserViewInfo implements IThumbViewInfo {
     private String url;  //图片地址
     private Rect mBounds; // 记录坐标
     private String user;//
      private String videoUrl;//视频链接 //不为空是视频
      
     public UserViewInfo(String url) {
         this.url = url;
     }
 
     @Override
     public String getUrl() {//将你的图片地址字段返回
         return url;
     }
     public void setUrl(String url) {
         this.url = url;
     }
     @Override
     public Rect getBounds() {//将你的图片显示坐标字段返回
         return mBounds;
     }
     
     public void setBounds(Rect bounds) {
         mBounds = bounds;
     }
    } 
 ```

### 3.使用自定义图片加载配置  **注意这个必须实现哦。不然加载失败**
   * 1在你项目工程，创建一个类 实现接口IZoomMediaLoader接口 如下代码
       demo 采用glide ，可以使用Picassor Imagloader 图片加载框架
````
public class TestImageLoader implements IZoomMediaLoader {
    @Override
    public void displayImage(Fragment context, String path,ImageView imageView, final MySimpleTarget<Bitmap> simpleTarget) {
            Glide.with(context).load(path)
                       .asBitmap()
                        .error(R.drawable.ic_default_image)
                        .listener(new RequestListener<String, Bitmap>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                                simpleTarget.onLoadFailed(null);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                simpleTarget.onResourceReady();
                                return false;
                            }
                        })
                        .into(imageView);
    }
        @Override
        public void displayGifImage(@NonNull Fragment context, @NonNull String path, ImageView imageView, @NonNull final MySimpleTarget simpleTarget) {
            Glide.with(context).load(path)
                   .asGif()
                    //可以解决gif比较几种时 ，加载过慢  //DiskCacheStrategy.NONE
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .error(R.drawable.ic_default_image)
                    .dontAnimate() //去掉显示动画
                    .listener(new RequestListener<String, GifDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GifDrawable> target, boolean isFirstResource) {
                            simpleTarget.onResourceReady();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GifDrawable resource, String model, Target<GifDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            simpleTarget.onLoadFailed(null);
                            return false;
                        }
                    })
                    .into(imageView);
        }
     @Override
     public void onStop(@NonNull Fragment context) {
           Glide.with(context).onStop();
     }
     @Override
     public void clearMemory(@NonNull Context c) {
              Glide.get(c).clearMemory();
     }

````
### 4.视频的支持
1.自定义播放视频控制
```
   EasyPreviewBuilder.from(VideoViewActivity.this)
                       .setData(mThumbViewInfoList)
                       .setCurrentIndex(position)
                       .setSingleFling(true)
                      .setOnVideoPlayerListener(new VideoClickListener(){
                           @Override
                           public void onPlayerVideo(String url) {
                               Log.d("onPlayerVideo",url);
                               Intent intent=new Intent(VideoViewActivity.this, VideoPlayerDetailedActivity.class);
                               intent.putExtra("url",url);
                               startActivity(intent);
                           }
                       })
                       .setType(EasyPreviewBuilder.IndicatorType.Number)
                       .start();
```

### 5.自定义Activity,Fragment
 1.实现自定义Activity，实现你业务操作例如加入标题栏，ViewPager切换动画等等
![自定义Activity](gif/2022-07-10_115213.png) 

 在你的布局中,引用类库核心布局
 
 >><include layout="@layout/easypreview_activity_image_preview_photo" />

 
2.实现自定义Fragment   实现自定义业务  例如 长按保存图片，编辑图片,对图片说明内容等等
图片缩放效果采用[PhotoView](https://github.com/chrisbanes/PhotoView)
![自定义Fragment](gif/自定义Fragment.png)

>>需要布局自定义重写onCreateView()。引用你自定义布局中添加
>><include layout="@layout/easypreview_fragment_image_photo_layout" />
 
3 使用细节注意：
  >>1  **Activity和Fragment可以单独使用,也可以组合一起使用**
  
  >>2. **自定义使用布局时，不在子类使用setContentView()方法**
  
  >>3. **你在Activity 重写 setContentLayout()，返回你的自定义布局**
  
  >>4. **在你布局内容 使用include layout="@layout/easypreview_activity_image_preview_photo" 预览布局添加你布局中**
 
  >>5. **EasyPreviewBuilder 调用 from()方法后，调用to();指向你.to(CustomActivity.class)自定义预览activity**
 
  >>6. **别忘了在AndroidManifest  activity 使用主题**
 
  >> 示例：
              <!--注册自定义activity-->
                <activity android:name=".custom.CustomActivity"
                    android:screenOrientation="portrait"
                    android:theme="@android:style/Theme.Translucent.NoTitleBar"
              /> 

#### [九宫格图片控件来自laobie](https://github.com/laobie/NineGridImageView)
