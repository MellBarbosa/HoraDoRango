package com.projeto.horadorango.util;

import io.realm.Realm;
import io.realm.RealmObject;
/**
 * Created by Mell on 16/11/2016.
 */

public class RealmUtil {

    public static <T extends RealmObject> int getNextPrimaryKey(Realm realm, Class<T> clazz) {
        Number lastId = realm.where(clazz).max("id");
        return lastId == null ? 1 : lastId.intValue() + 1;
    }
}
