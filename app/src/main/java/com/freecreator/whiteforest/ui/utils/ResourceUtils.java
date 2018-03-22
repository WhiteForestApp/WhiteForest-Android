package com.freecreator.whiteforest.ui.utils;

import com.freecreator.whiteforest.R;

import java.lang.reflect.Field;

/**
 * Created by niko on 2018/3/22.
 */

public class ResourceUtils {

    public static int  getImageId(String imageName){
        Class mipmap = R.mipmap.class;
        Class drawable = R.drawable.class;

        try {
            Field field = mipmap.getField(imageName);
            int resId = field.getInt(imageName);
            return resId;
        } catch (Exception e) {
            e.printStackTrace();

            try{
                Field idField = drawable.getDeclaredField(imageName);
                return idField.getInt(idField);
            }catch (Exception e2){
                e2.printStackTrace();
                return 0;
            }
        }
    }
}
