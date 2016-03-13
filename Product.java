package com.example.mall_guru;

import android.provider.BaseColumns;

public class Product implements BaseColumns {
	
	public static final String TABLE_NAME = "product";
	
	public static final String NAME = "name";
	public static final String COLOR = "color";
	public static final String BRAND = "brand";
	
	public static final String DEFAULT_SORT = NAME + ", " + BRAND + ", " + COLOR+ " ASC";
}
