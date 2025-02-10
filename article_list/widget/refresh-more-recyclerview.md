# introduce 

This is a composite widget that includes recyclerview and [SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout), making it convenient to use when you need to refresh and load more items in a Recyclerview.

# how to use

Declare component

```xml
       <com.gx.glib.custom.widget.recyclerview.RefreshMoreRecyclerview
            android:id="@+id/refresh_more_recyclerview"
            android:layout_width="0dp"
            android:layout_height="0dp"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            app:layout_constraintBottom_toTopOf="@id/bt_create_voucher"
            app:layout_constraintEnd_toEndOf="parent"
            />
```

Component initialization

```kotlin
// It is better to call the init funcition in onViewCreated 
ovverride fun onViewCreated(view:View,saveInstanceState:Bundle?){
    // ...
    initRefreshAndMoreRecyclerview()
}

fun initRefreshAndMoreRecyclerview(){
    // if you like the divider you should use that
    val decoration = DividerItemDecoration(mActivity, LinearLayoutManager.VERTICAL).also {
            it.setDrawable(
                ResourcesCompat.getDrawable(
                    resources, R.drawable.list_divider_10, null
                )!!
            )
        }

        mDataBinding.refreshMoreRecyclerview
            .addItemDecoration(decoration)
            .bindListener({
                // Callback triggered on refresh
                onRefresh()
            },{
                // Callback triggered on load more
                cPage++
                mViewModel.getVoucherList(voucherType,cPage)
            })
            .bindAdapter(genVoucherAdapter)
}
```

Stop load animation

```kotlin
mDataBinding.refreshMoreRecyclerview.finishLoad()
```

