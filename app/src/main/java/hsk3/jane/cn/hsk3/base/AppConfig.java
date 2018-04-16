package hsk3.jane.cn.hsk3.base;

import android.os.Environment;

import java.io.File;

/**
 * Created by Jane on 2018/3/9.
 */

public class AppConfig {
    public static final String BASE_PACKAGE = "hsk3.jane.cn.hsk3";
    public static final String APP_PACKAGE_NAME = "hsk";
    public static final String APP_SHORT_NAME = "hsk3";

    public static final String HOME_CACHE = "cache";
    public static final String HOME_DB = "db";
    public static final String HOME_DOWNLOAD = "download";

    /**
     * SDCard 初始目录
     */
    public static final String SDCARD_APP_ROOT = Environment
            .getExternalStorageDirectory().getPath()
            + File.separator
            + APP_PACKAGE_NAME
            + File.separator
            + APP_SHORT_NAME;
    // 初始化SDCard应用数据主目录
    public static File buildAppHome() {
        File base = new File(SDCARD_APP_ROOT);
        if (!base.exists()) {
            base.mkdir();
        }
        if (base.exists() && base.isFile()) {
            base.mkdir();
        }
        return base;
    }

    /**
     * 初始化SDCard应用数据所有目录
     * @param homeName
     *  {@link #HOME_CACHE} or {@link #HOME_DB} or
     *  {@link #HOME_DOWNLOAD}
     */
    public static File buildPath(String homeName) {
        File base = buildAppHome();
        String path = base.getPath() + File.separator + homeName;
        File build = new File(path);
        if (!build.exists()) {
            build.mkdirs();
        }
        if (build.exists() && build.isFile()) {
            build.mkdirs();
        }
        return build;
    }
}
