#使用方法

    <com.why2.navigationanim.view.NavigationAnimLayout
        android:id="@+id/nal"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@color/white"
        android:orientation="horizontal"
        android:paddingTop="10dp"
        android:paddingBottom="5dp"
        app:layout_constraintBottom_toBottomOf="parent">

        <com.why2.navigationanim.view.NavigationAnimView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            app:nav_content="菜单1"
            app:nav_selected="true"
            app:nav_selectedImgRes="@array/menu1"
            app:nav_selectedTxtColor="#333333"
            app:nav_txt_size="12sp"
            app:nav_unSelectedImgRes="@drawable/ic_menu4_unselected"
            app:nav_unselectedTxtColor="#999999" />

        <com.why2.navigationanim.view.NavigationAnimView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            app:nav_content="菜单2"
            app:nav_selectedImgRes="@array/menu2"
            app:nav_selectedTxtColor="#333333"
            app:nav_txt_size="12sp"
            app:nav_unSelectedImgRes="@drawable/ic_menu2_unselected"
            app:nav_unselectedTxtColor="#999999" />

        <com.why2.navigationanim.view.NavigationAnimView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            app:nav_content="菜单3"
            app:nav_selectedImgRes="@array/menu3"
            app:nav_selectedTxtColor="#333333"
            app:nav_txt_size="12sp"
            app:nav_unSelectedImgRes="@drawable/ic_menu3_unselected"
            app:nav_unselectedTxtColor="#999999" />

        <com.why2.navigationanim.view.NavigationAnimView
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_weight="1"
            app:nav_content="菜单4"
            app:nav_selectedImgRes="@array/menu4"
            app:nav_selectedTxtColor="#333333"
            app:nav_txt_size="12sp"
            app:nav_unSelectedImgRes="@drawable/ic_menu4_unselected"
            app:nav_unselectedTxtColor="#999999" />
    </com.why2.navigationanim.view.NavigationAnimLayout>