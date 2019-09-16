package com.singbox;

import com.singbox.service.model.ContentService;
import com.singbox.service.model.MimeTypeService;
import com.singbox.service.model.StoreService;

public class Pruebas {

	public static void main(String[] args) {

		try {

			Populate populate = new Populate();

			String key = "box";

//			populate.populate(key);

			MimeTypeService mimeTypeService = new MimeTypeService(key);
			StoreService storeService = new StoreService(key);
			ContentService contentService = new ContentService(key);

			System.out.println("DATA BASE : " + key + "\tMime types: " + mimeTypeService.count());
//			System.out.println(mimeTypeService.find());
			System.out.println("DATA BASE : " + key + "\tStores: " + storeService.count());
			System.out.println("DATA BASE : " + key + "\tContents: " + contentService.count());

			// ----------------------------------------------------------------------

			key = "box2";

//			populate.populate(key);

			mimeTypeService = new MimeTypeService(key);
			storeService = new StoreService(key);
			contentService = new ContentService(key);

			System.out.println("DATA BASE : " + key + "\tMime types: " + mimeTypeService.count());
//			System.out.println(mimeTypeService.find());
			System.out.println("DATA BASE : " + key + "\tStores: " + storeService.count());
			System.out.println("DATA BASE : " + key + "\tContents: " + contentService.count());

			// ----------------------------------------------------------------------

			key = "box";

			mimeTypeService = new MimeTypeService(key);
			storeService = new StoreService(key);
			contentService = new ContentService(key);

			System.out.println("DATA BASE : " + key + "\tMime types: " + mimeTypeService.count());
			System.out.println("DATA BASE : " + key + "\tStores: " + storeService.count());
			System.out.println("DATA BASE : " + key + "\tContents: " + contentService.count());
			
			System.out.println(contentService.find());

		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

}
