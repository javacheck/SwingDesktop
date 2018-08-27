package com.JComPz;

import com.dao.Dao1;
import com.model.BookType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapPz {
	static Map map = new HashMap();

	public static Map getMap() {
		List list = Dao1.selectBookCategory();
		for (int i = 0; i < list.size(); i++) {
			BookType booktype = (BookType) list.get(i);

			Item item = new Item();
			item.setId(booktype.getId());
			item.setName(booktype.getTypeName());
			map.put(item.getId(), item);

		}
		return map;
	}
}
