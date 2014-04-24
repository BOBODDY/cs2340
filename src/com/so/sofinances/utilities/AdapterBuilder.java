package com.so.sofinances.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.so.sofinances.model.Listable;

import android.content.Context;
import android.widget.SimpleAdapter;

public class AdapterBuilder {
    /**
     * first key for mapping.
     */
    private static final String TEXT1 = "text1";
    /**
     * second key for mapping.
     */
    private static final String TEXT2 = "text2";
    /**
     * mapping for first to second keys.
     */
    private static final String[] fromMapKey = new String[] {TEXT1, TEXT2};
    /**
     * array of layout IDs.
     */
    private static final int[] toLayoutId = new int[] {android.R.id.text1, android.R.id.text2};
    
    public static Map<String, String> toListMap(String item1, String item2) {
    	Map<String, String> textMap = new HashMap<String, String>();
    	if (item1 != null && item2 != null) {
    		textMap.put(TEXT1, item1);
    		textMap.put(TEXT2, item2);
    	}
    	return textMap;
    }
    
    public static <E extends Listable> List<Map<String, String>> toMapList(List<E> list) {
    	List<Map<String, String>> mapList = new ArrayList<Map<String, String>>(list.size());
    	for (Listable l : list) {
    		Map<String, String> textMap = toListMap(l.getMainTitle(), l.getSubTitle());
    		mapList.add(textMap);
    	}
    	return mapList;
    }
    
    public static <E extends Listable> SimpleAdapter buildAdapter(List<E> list, Context c) {
    	List<Map<String, String>> mapList = toMapList(list);
    	return new SimpleAdapter(c, mapList, android.R.layout.simple_list_item_2, fromMapKey, toLayoutId);
    }
}
