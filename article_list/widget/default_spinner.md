# introduce 

This is a spinner widget which support default value before user select, it's similar to the html select



# how to use

layout file

```xml
    <com.gx.glib.custom.widget.spinner.DefaultableSpinner
                            android:id="@+id/spinner_voucher_teplate"
                            style="@style/Widget.AppCompat.Spinner.DropDown"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:hint="@string/please_select"
                            android:textColorHint="@color/gray_99"
                            android:maxLines="1"
                            android:paddingTop="@dimen/dp_5"
                            android:paddingStart="@dimen/dp_5"
                            android:paddingBottom="@dimen/dp_5"
                            android:paddingEnd="@dimen/dp_36"
                            app:layout_constraintBottom_toBottomOf="parent"
                            app:layout_constraintLeft_toLeftOf="parent"
                            app:layout_constraintRight_toRightOf="parent"
                            app:layout_constraintTop_toTopOf="parent"
                            app:layout_constraintVertical_bias="0.51" />
```

bind data and add listener

```kotlin
 mBinding.spinnerVoucherTeplate.setAdapter(ArrayAdapter<String>(this@CreateVoucherActivity,R.layout.spinner_list_item,dataList.map { it.name }))
        mBinding.spinnerVoucherTeplate.onItemClickListener = AdapterView.OnItemClickListener{ adapter, v, pos, id ->
           // Do the select things
        }
```

if you want change the popupview width, you can use

```kotlin
mBinding.spinnerVoucherTeplate.let{
 	it.dropDownWidth = PhoneUtils.getScreenWidthInPx(this@CreateVoucherActivity)
    it.setDropDownBackgroundResource(R.drawable.shape_corner_5_line_white_gray)
}
```

