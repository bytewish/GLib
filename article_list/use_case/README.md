# introduce

This is a use case of a commonly used effect.

# Bottom Navigation

## page effect

![BottomNavagation](/imgs/bottom_navigation.png "BottomNavagation")

## code

XML Layout file

```xml
   <com.google.android.material.bottomnavigation.BottomNavigationView
        android:layout_width="match_parent"
        android:id="@+id/bottomView"
        android:background="@color/color_white"
        app:menu="@menu/home_navigation"
        android:theme="@style/SellerBottomViewTheme"
        app:labelVisibilityMode="labeled"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        android:layout_height="@dimen/dp_60"/>

    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toTopOf="@id/bottomView"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        />
```

menu file(home_navigation.xml)

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto">
    <item
        android:id="@+id/navigation_home"
        android:icon="@drawable/menu_home"
        app:showAsAction="always|withText"
        android:title="@string/seller_home" />
    <item
        android:id="@+id/navigation_order"
        android:icon="@drawable/menu_order"
        app:showAsAction="always|withText"
        android:title="@string/seller_order" />
    <item
        android:id="@+id/navigation_product"
        android:icon="@drawable/menu_product"
        app:showAsAction="always|withText"
        android:title="@string/seller_product" />
    <item
        android:id="@+id/navigation_message"
        android:icon="@drawable/menu_message"
        app:showAsAction="always|withText"
        android:title="@string/seller_message" />
    <item
        android:id="@+id/navigation_account"
        android:icon="@drawable/menu_account"
        app:showAsAction="always|withText"
        android:title="@string/seller_account" />
</menu>
```

control the BottomView with your code

```kotlin
 		mBinding.bottomView.itemIconTintList = null
        mBinding.bottomView.setOnItemSelectedListener {
            currentMenu = it
            when (it.itemId) {
                R.id.navigation_home -> {
                    mBinding.viewPager.currentItem = 0
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_order -> {
                    mBinding.viewPager.currentItem = 1
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_product -> {
                    mBinding.viewPager.currentItem = 2
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_message -> {
                    mBinding.viewPager.currentItem = 3
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_account -> {
                    mBinding.viewPager.currentItem = 4
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
```

If you want show the red circle above of menu, you can use the  [BadgeView](https://github.com/qstumn/BadgeView) in control code.

```kotlin
 val menuView: BottomNavigationMenuView =
            mBinding.bottomView.getChildAt(0) as BottomNavigationMenuView
        val badgeMenuItemView: BottomNavigationItemView =
            menuView.getChildAt(3) as BottomNavigationItemView
        // remove all red circle
        removeMenuChild(badgeMenuItemView)
        // check whether badgeView exsist
        if (badgeView == null) {
            badgeView = LayoutInflater.from(this).inflate(R.layout.menu_notice_badge, menuView, false)
            badgeMenuItemView.addView(badgeView)
        }
        // set badgeView text
        val tvNotic: TextView = badgeView!!.findViewById(R.id.tv_badge)
        if (noticeCount <= 0) {
            tvNotic.visibility = View.GONE
        } else if (noticeCount > 99) {
            tvNotic.visibility = View.VISIBLE
            tvNotic.text = "99+"
        } else {
            tvNotic.visibility = View.VISIBLE
            tvNotic.text = noticeCount.toString()
        }
```